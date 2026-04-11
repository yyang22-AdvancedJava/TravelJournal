package com.traveljournal.controller;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import com.traveljournal.persistence.JournalDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = {"/displayJournalsByUser"}
)

public class DisplayJournalsByUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. req(HttpServletRequest)를 사용해서 바로 세션을 가져옵니다.
        // 별도의 HttpSession 변수를 선언하지 않고 직접 써도 됩니다.
        User currentUser = (User) req.getSession().getAttribute("user");

        // 2. 유저 검증 (로그인 안 되어 있으면 로그인 페이지로)
        if (currentUser == null) {
            resp.sendRedirect("index_cognito.jsp");
            return;
        }

        // 3. DAO를 통해 데이터 조회
        JournalDao journalDao = new JournalDao();

        // 강사님 포인트: "세션에 있는 유저 객체"를 조회 조건으로 사용!
        // Journal 엔티티에 정의된 User 객체 필드명이 "user"라고 가정합니다.
        List<Journal> userJournals = journalDao.getByPropertyEqual("user", currentUser);

        // 4. req(HttpServletRequest)에 데이터를 담아 JSP로 전달
        req.setAttribute("journals", userJournals);
        req.setAttribute("userName", currentUser.getUserName());

        // 5. main.jsp로 화면 전환 (forward)
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}
