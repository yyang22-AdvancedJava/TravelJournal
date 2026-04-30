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

/**
 * Controller servlet responsible for retrieving and displaying travel journals
 * associated with a specific username. This is typically used by administrators
 * to filter entries by a specific user or for public profile viewing.
 *
 * @author yyang22
 */
@WebServlet("/viewByUser")
public class ViewByUser extends HttpServlet {

    /**
     * Handles GET requests to search for journals by a specific username.
     *
     * @param req  the {@link HttpServletRequest} containing the "userName" search parameter
     * @param resp the {@link HttpServletResponse}
     * @throws ServletException if a servlet-specific error occurs during processing
     * @throws IOException      if an input or output error is detected during request forwarding
     */
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
