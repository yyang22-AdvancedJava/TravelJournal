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
        String referer = req.getHeader("Referer");
        // 주소가 있고, 자기 자신(edit)이 아닐 때만 세션에 '복귀 주소'로 저장
        if (referer != null && !referer.contains("edit")) {
            req.getSession().setAttribute("returnUrl", referer);
        }

        try {
            // 2. 수정할 ID 가져오기
            int id = Integer.parseInt(req.getParameter("id"));

            // 3. 데이터 조회
            JournalDao journalDao = new JournalDao();
            Journal journal = journalDao.getById(id);

            // 4. JSP에 데이터 전달
            req.setAttribute("journal", journal);
            req.setAttribute("activePage", "main");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/editJournal.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            logger.error("Error loading edit form", e);
            resp.sendRedirect("error.jsp");
        }
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

            // 세션에 저장된 주소가 있는지 확인
            String returnUrl = (String) session.getAttribute("returnUrl");

            // 3. 리다이렉트
            if (returnUrl != null && !returnUrl.isEmpty()) {
                // 주소를 썼으니 세션에서 지워줍니다.
                session.removeAttribute("returnUrl");
                resp.sendRedirect(returnUrl);
            } else {
                // 주소가 없으면 권한별 기본 목록으로 (기존에 만드신 메서드)
                redirectToList(req, resp, session);
            }
            return; // 리다이렉트 후 코드 실행 중단 (매우 중요!)

        } catch (Exception e) {
            logger.error("There was an error while updating a journal", e);
        }

        // 5. 목록으로 돌아가기
        // resp.sendRedirect("displayJournalsByUser");
        // 페이지 이동
        /*
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin != null && isAdmin) {
            resp.sendRedirect("displayAllJournals");
        } else {
            resp.sendRedirect("displayJournalsByUser");
        }
        */
    }

    /**
     * 기본 목록 페이지로 리다이렉트하는 헬퍼 메서드
     */
    private void redirectToList(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws IOException {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (isAdmin != null && isAdmin) {
            resp.sendRedirect("displayAllJournals");
        } else {
            resp.sendRedirect("displayJournalsByUser");
        }
    }

}