package com.traveljournal.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller servlet responsible for terminating the user session.
 * It clears all session-bound data (such as user profiles and authentication status)
 * and redirects the user to the landing page.
 *
 * @author pwaite
 */
@WebServlet(urlPatterns = {"/logout"}) // header.jsp의 href="logout"과 일치해야 함
public class Logout extends HttpServlet {

    /**
     * Handles GET requests to log out the current user.
     *
     * @param req  the {@link HttpServletRequest} used to retrieve the current session
     * @param resp the {@link HttpServletResponse} used to redirect to the home page
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected during redirection
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 현재 세션을 가져옵니다. (없으면 새로 만들지 않도록 false 설정)
        HttpSession session = req.getSession(false);

        if (session != null) {
            // 2. 세션을 완전히 무효화(삭제)합니다.
            // 이렇게 하면 sessionScope.user 정보가 날아갑니다.
            session.invalidate();
        }

        // 3. 로그아웃 후 보여줄 페이지로 리다이렉트합니다.
        // 앱의 시작 페이지인 index_cognito.jsp로 보내는 것이 좋습니다.
        resp.sendRedirect("index.jsp");
    }
}