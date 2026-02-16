package controller;

import entity.Journal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import persistence.JournalDao;

import javax.servlet.RequestDispatcher;
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

            resp.sendRedirect(req.getContextPath() + "/displayAllJournals");

        } else {
            // 실패 시 에러 페이지나 메시지 처리
            log.error("Delete Failed");
        }
    }
}