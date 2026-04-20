package com.traveljournal.controller;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import com.traveljournal.persistence.JournalDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
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

        // 1. 세션에서 유저 및 관리자 여부 가져오기
        User currentUser = (User) req.getSession().getAttribute("user");
        Boolean isAdmin = (Boolean) req.getSession().getAttribute("isAdmin");

        // 2. 유저 검증
        if (currentUser == null) {
            resp.sendRedirect("index_cognito.jsp");
            return;
        }

        // 3. DAO를 통해 데이터 조회
        JournalDao journalDao = new JournalDao();
        List<Journal> journals;

        // 4. 관리자 여부에 따른 분기 처리
        if (isAdmin != null && isAdmin) {
            // 관리자는 전체 일기 조회 (JournalDao에 getAll() 메서드가 있어야 합니다)
            journals = journalDao.getAll();
        } else {
            // 일반 유저는 본인 일기만 조회
            journals = journalDao.getByPropertyEqual("user", currentUser);
        }

        // 5. 결과 전달
        req.setAttribute("journals", journals);
        req.setAttribute("userName", currentUser.getUserName());

        // 6. 메인 화면으로 이동
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}