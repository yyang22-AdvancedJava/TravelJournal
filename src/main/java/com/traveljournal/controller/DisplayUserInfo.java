package com.traveljournal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller servlet responsible for retrieving and displaying detailed information
 * for a specific user.
 *
 * @author yyang22
 */
@WebServlet("/displayUserInfo")
public class DisplayUserInfo extends HttpServlet {

    private static final Logger log = LogManager.getLogger(DisplayUserInfo.class);

    /**
     * Handles GET requests to display specific user information.     *
     *
     * @param req  the {@link HttpServletRequest} containing the "id" parameter of the user
     * @param resp the {@link HttpServletResponse}
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected during forwarding
     * @throws NumberFormatException if the provided ID parameter is not a valid integer
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1. 전달된 ID 값을 받아오기
        int userId = Integer.parseInt(req.getParameter("id"));

        // 2. UserData 클래스를 이용해 해당 사용자 정보를 조회
        //UserData userData = new UserData();
        //User user = userData.getUserById(userId); // ID로 사용자 찾기

        // 3. 사용자 정보를 JSP로 전달
        //req.setAttribute("user", user);

        // 4. 사용자 정보를 보여주는 JSP로 포워딩
        req.getRequestDispatcher("/displayUserInfo.jsp").forward(req, resp);
    }
}
