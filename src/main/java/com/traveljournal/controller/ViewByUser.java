package com.traveljournal.controller; // 패키지 경로는 네 프로젝트에 맞게 확인해라

import com.traveljournal.entity.Journal;
import com.traveljournal.persistence.JournalDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewByUser")
public class ViewByUser extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");

        if (userName != null && !userName.isEmpty()) {
            JournalDao journalDao = new JournalDao();
            List<Journal> userJournals = journalDao.getJournalsByUserName(userName);
            req.setAttribute("journals", userJournals);
        }

        // 검색 결과를 보여줄 jsp로 연결
        req.getRequestDispatcher("/viewByUser.jsp").forward(req, resp);
    }
}
