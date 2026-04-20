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

@WebServlet("/searchByCity")
public class viewByCity extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String city = request.getParameter("city");

        // 검색어가 있을 때만 조회
        if (city != null && !city.trim().isEmpty()) {
            // 네가 구현해둔 JournalDao의 getByLocationName 메서드 활용
            JournalDao journalDao = new JournalDao();
            List<Journal> journals = journalDao.getByLocationName(city);

            // 결과를 request에 담기
            request.setAttribute("journals", journals);
            request.setAttribute("searchedCity", city); // 검색한 도시명도 보여주려면 필요함
        }

        // viewByCity.jsp로 포워딩
        request.getRequestDispatcher("viewByCity.jsp").forward(request, response);
    }
}