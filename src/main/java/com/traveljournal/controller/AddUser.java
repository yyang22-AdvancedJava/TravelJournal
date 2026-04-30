package com.traveljournal.controller;

import com.traveljournal.entity.User;
import com.traveljournal.persistence.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller servlet responsible for adding new users to the system.
 *
 * @author pwaite
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Handles POST requests to register a new user.
     *
     * @param req  the {@link HttpServletRequest} containing user registration parameters
     * @param resp the {@link HttpServletResponse} used to forward the user to a result page
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 폼 파라미터 가져오기
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        // Cognito 연동 시 클라이언트나 람다에서 전달해준 cognitoId가 있다면 가져옵니다.
        // 현재 폼에 없다면 임시로 null이나 userName을 넣을 수 있습니다.
        String cognitoId = req.getParameter("cognitoId");

        UserDao userDao = new UserDao();

        // 2. 중복 검사 (userName으로 정확히 일치하는 유저가 있는지 확인)
        User existingUser = userDao.getByUserName(userName);

        if (existingUser == null) {
            // 3. 새로운 User 객체 생성
            // 중요: 우리가 새로 만든 User 엔티티 생성자는 5개의 인자를 받습니다.
            // (firstName, lastName, userName, password, cognitoId)
            User userToInsert = new User(firstName, lastName, userName, password, cognitoId);

            try {
                userDao.insert(userToInsert);
                logger.info("새로운 유저 등록 성공: " + userName);
                req.setAttribute("message", "User added successfully!");
            } catch (Exception e) {
                logger.error("유저 등록 중 오류 발생", e);
                req.setAttribute("message", "Error occurred while adding user.");
            }
        } else {
            logger.warn("중복된 사용자 이름 시도: " + userName);
            req.setAttribute("message", "Username already exists. Please try again.");
        }

        // 4. 결과 페이지로 포워딩
        RequestDispatcher dispatcher = req.getRequestDispatcher("/addResult.jsp");
        dispatcher.forward(req, resp);
    }
}