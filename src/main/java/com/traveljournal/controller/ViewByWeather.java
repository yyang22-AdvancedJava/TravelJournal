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
 * Controller servlet responsible for filtering travel journals by weather conditions.
 * It enforces access control by ensuring users only see their own journals,
 * while allowing administrators to search through all entries in the system.
 *
 * @author YourName
 */
@WebServlet("/searchByWeather")
public class ViewByWeather extends HttpServlet {

    /**
     * Handles GET requests to search for journals based on a weather keyword.
     *
     * @param request  the {@link HttpServletRequest} containing the "weather" search term
     * @param response the {@link HttpServletResponse}
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error is detected during forwarding or redirection
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. 세션에서 유저 정보와 관리자 여부 가져오기
        User user = (User) request.getSession().getAttribute("user");
        Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");

        // 2. 로그인 체크
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String weather = request.getParameter("weather");

        if (weather != null && !weather.trim().isEmpty()) {
            JournalDao journalDao = new JournalDao();
            List<Journal> journals;

            // 3. 관리자면 전체 조회, 일반 유저면 본인 일기만 조회
            if (isAdmin != null && isAdmin) {
                // 관리자용: 전체 데이터에서 날씨 키워드 검색
                journals = journalDao.getByPropertyLike("weather", weather);
            } else {
                // 일반 유저용: 본인 ID로 필터링된 데이터에서 날씨 검색
                journals = journalDao.getByWeatherAndUser(weather, user.getId());
            }

            request.setAttribute("journals", journals);
            request.setAttribute("searchedWeather", weather);
        }

        // 4. viewByWeather.jsp로 포워딩
        request.getRequestDispatcher("viewByWeather.jsp").forward(request, response);
    }
}