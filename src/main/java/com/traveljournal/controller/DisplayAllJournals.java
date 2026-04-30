package com.traveljournal.controller;

import com.traveljournal.persistence.JournalDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller servlet responsible for retrieving and displaying all travel journals.
 *
 * @author yyang22
 */
@WebServlet(
        urlPatterns = {"/displayAllJournals"}
)

public class DisplayAllJournals extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
        UserData userData = new UserData();
        if (req.getParameter("submit").equals("search")) {
            req.setAttribute("users", userData.getUsersByLastName(req.getParameter("searchTerm")));
        } else {
            req.setAttribute("users", userData.getAllUsers());
        }
        */
        /*** NEw &**/
        JournalDao journalDao = new JournalDao();

        req.setAttribute("journals", journalDao.getAll());

        //RequestDispatcher dispatcher = req.getRequestDispatcher("/displayJournals.jsp");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}