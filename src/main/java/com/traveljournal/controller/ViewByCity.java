package com.traveljournal.controller; // 패키지 경로는 네 프로젝트에 맞게 확인해라

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.User;
import com.traveljournal.persistence.JournalDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller servlet responsible for searching travel journals by city name.
 *
 * @author yyang22
 */
@WebServlet("/searchByCity")
public class ViewByCity extends HttpServlet {

    /**
     * Handles GET requests to search for journals by a specific city.
     * The method validates the user's session, retrieves the city parameter from the request,
     * and performs a filtered search.
     *
     * Logic flow:
     * 1. Check for a valid user session; redirect to login if missing.
     * 2. If the user is an admin, retrieve all journals matching the city name.
     * 3. If the user is a standard user, retrieve only their personal journals matching the city name.
     *
     * @param request  the {@link HttpServletRequest} containing the city search parameter
     * @param response the {@link HttpServletResponse}
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected during forwarding or redirection
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 세션에서 유저 정보와 관리자 여부 가져오기
        User user = (User) request.getSession().getAttribute("user");
        Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String city = request.getParameter("city");

        if (city != null && !city.trim().isEmpty()) {
            JournalDao journalDao = new JournalDao();
            List<Journal> journals;

            // 관리자면 전체 조회, 아니면 본인 것만 조회
            if (isAdmin != null && isAdmin) {
                journals = journalDao.getByLocationName(city);
            } else {
                journals = journalDao.getByLocationNameAndUser(city, user.getId());
            }

            request.setAttribute("journals", journals);
        }

        request.getRequestDispatcher("viewByCity.jsp").forward(request, response);
    }
}