package com.traveljournal.controller;

//import edu.matc.entity.User;

import com.traveljournal.entity.User;
import com.traveljournal.persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * A servlet to add a user.
 * @author yyang22
 */
@WebServlet("/addUser")

public class AddUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // Get form parameters from the request
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String reEnterPassword = req.getParameter("reEnterPassword");

        // To check existing users
        UserDao userDao = new UserDao();
        List<User> users = userDao.getByPropertyLike("userName", userName);
        System.out.println("Users: " + users);


        // Insert the new user into the database using UserData class
        //UserData userData = new UserData();
        boolean userCanBeAdded = false;

        // if (req.getParameter("submit").equals("add")) {
        // if (users.size() != 0) {
        if (users.isEmpty()) {
            userCanBeAdded = true;
        }

        // Add a success or error message based on user creation
        if (userCanBeAdded) {
            User userToInsert = new User(firstName, lastName, userName, password);
            userDao.insert(userToInsert);
            req.setAttribute("message", "User added successfully!");
        } else {
            req.setAttribute("message", "Username already exists. Please try again.");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/addResult.jsp");
        dispatcher.forward(req, resp);


    }
}
