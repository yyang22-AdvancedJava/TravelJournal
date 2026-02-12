package controller;

import persistence.UserDao;

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
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
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