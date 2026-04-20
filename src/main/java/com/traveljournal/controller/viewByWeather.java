package com.traveljournal.controller; // 패키지 경로는 네 프로젝트에 맞게 확인해라

import com.traveljournal.entity.Journal;
import com.traveljournal.persistence.JournalDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/searchByWeather")
public class viewByWeather extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String weather = request.getParameter("weather");

        if (weather != null && !weather.trim().isEmpty()) {
            // JournalDao의 Like 검색 기능을 활용하여 날씨 검색
            JournalDao journalDao = new JournalDao();
            List<Journal> journals = journalDao.getByPropertyLike("weather", weather);

            request.setAttribute("journals", journals);
            request.setAttribute("searchedWeather", weather);
        }

        request.getRequestDispatcher("viewByWeather.jsp").forward(request, response);
    }
}