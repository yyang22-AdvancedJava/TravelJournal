package com.traveljournal.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.traveljournal.entity.Journal;
import com.traveljournal.persistence.JournalDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Controller servlet that handles the editing of existing travel journal entries.
 *
 * @author yyang22
 */
@WebServlet("/editJournal")
public class EditJournal extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Handles GET requests to load the journal editing form.
     * Fetches the specific journal entry by its ID and provides it to the JSP
     * to populate the form fields.
     *
     * @param req  the {@link HttpServletRequest} containing the unique ID of the journal to edit
     * @param resp the {@link HttpServletResponse}
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during request forwarding
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 수정할 ID 가져오기
        int id = Integer.parseInt(req.getParameter("id"));

        // 2. 전용 Dao를 사용하여 데이터 조회
        JournalDao journalDao = new JournalDao();
        Journal journal = journalDao.getById(id); // 메서드명은 본인의 Dao에 맞춰 수정하세요

        // 3. JSP에 데이터 전달
        req.setAttribute("journal", journal);
        req.setAttribute("activePage", "main"); // 헤더 강조용

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editJournal.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Handles POST requests to update an existing journal entry.
     * Collects updated parameters from the form, modifies the journal through a database update.
     * It then redirects the user back to the appropriate journal list based on their administrative role.
     *
     * @param req  the {@link HttpServletRequest} containing the updated journal form data
     * @param resp the {@link HttpServletResponse} used for redirection after the update
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // 1. 파라미터 수집
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String cityName = req.getParameter("city");
        String content = req.getParameter("content");
        String weather = req.getParameter("weather");
        String dateStr = req.getParameter("date");

        // 2. 기존 데이터 불러오기
        JournalDao journalDao = new JournalDao();
        Journal journal = journalDao.getById(id);

        // 3. 엔티티 업데이트
        journal.setTitle(title);
        journal.setContent(content);
        journal.setWeather(weather);

        // 날짜 처리 (사용자가 선택한 날짜 적용)
        if (dateStr != null && !dateStr.isEmpty()) {
            journal.setCreatedAt(LocalDate.parse(dateStr).atStartOfDay());
        }
        journal.setUpdatedAt(LocalDateTime.now());

        // 4. 저장 (update 수행)
        try {
            journalDao.update(journal); // 본인의 업데이트 메서드 호출
            logger.info("Journal Edit Success: ID " + id);
        } catch (Exception e) {
            logger.error("There was an error while updating a journal", e);
        }

        // 5. 목록으로 돌아가기
        // resp.sendRedirect("displayJournalsByUser");
        // 페이지 이동

        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin != null && isAdmin) {
            resp.sendRedirect("displayAllJournals");
        } else {
            resp.sendRedirect("displayJournalsByUser");
        }
    }
}