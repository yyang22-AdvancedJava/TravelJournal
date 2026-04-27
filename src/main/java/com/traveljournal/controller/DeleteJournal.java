package com.traveljournal.controller;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.traveljournal.persistence.JournalDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/deleteJournal"}
)

public class DeleteJournal extends HttpServlet {

    private static final Logger log = LogManager.getLogger(DisplayUserInfo.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 파라미터로 넘어온 ID 추출
        int id = Integer.parseInt(req.getParameter("id"));

        if (id > 0) {
            // 2. DAO 객체 생성 및 삭제 실행
            JournalDao dao = new JournalDao();
            Journal journalToDelete = new Journal();
            journalToDelete = dao.getById(id);
            dao.delete(journalToDelete);

            // --- 여기서 핵심 수정 ---
            // 세션에서 현재 유저의 관리자 여부를 가져옵니다.
            Boolean isAdmin = (Boolean) req.getSession().getAttribute("isAdmin");

            // 관리자면 전체 목록으로, 일반 유저면 본인 목록으로 분기합니다.
            if (isAdmin != null && isAdmin) {
                resp.sendRedirect(req.getContextPath() + "/displayAllJournals");
            } else {
                // 본인 것만 보기 페이지로 유저 ID와 함께 리다이렉트
                User user = (User) req.getSession().getAttribute("user");
                resp.sendRedirect(req.getContextPath() + "/displayJournalsByUser?userId=" + user.getId());
            }

        } else {
            // 실패 시 에러 페이지나 메시지 처리
            log.error("Delete Failed");
        }
    }
}