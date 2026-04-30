package com.traveljournal.controller;

import com.traveljournal.persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller servlet responsible for searching and retrieving user information.
 *
 * @author pwaite
 */
@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    /**
     * Handles GET requests for user searching and listing.
     * Checks the "submit" parameter to determine if the user is performing a specific
     * search by last name or requesting the full directory. The results are attached
     * to the request attribute "users" and forwarded to the results view.
     *
     * @param req  the {@link HttpServletRequest} containing search parameters and submit actions
     * @param resp the {@link HttpServletResponse}
     * @throws ServletException if a servlet-specific error occurs during processing
     * @throws IOException      if an input or output error is detected during forwarding
     */
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

        UserDao userDao = new UserDao();
        if (req.getParameter("submit").equals("search")) {
            req.setAttribute("users", userDao.getByPropertyEqual("lastName", req.getParameter("searchTerm")));
        } else {
            req.setAttribute("users", userDao.getAll());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}