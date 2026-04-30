package com.traveljournal.controller;

import com.traveljournal.entity.Journal;
import com.traveljournal.entity.Location;
import com.traveljournal.entity.User;
import com.traveljournal.persistence.JournalDao;
import com.traveljournal.persistence.LocationDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
/**
 * Controller servlet for handling the creation of new travel journals.
 * Provides functionality to load configuration for weather APIs and
 * persist journal entries associated with specific locations and users.
 *
 * @author yyang22
 */
@WebServlet(urlPatterns = {"/addJournal"})
public class AddJournal extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Handles GET requests to display the journal entry form.
     * Loads weather API properties from the configuration file and sets them as request attributes.
     *
     * @param req  the {@link HttpServletRequest} object containing the request the client made of the servlet
     * @param resp the {@link HttpServletResponse} object containing the response the servlet returns to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties properties = new Properties();
        try {
            // resources 폴더 내의 설정 파일을 읽어 JSP로 전달
            properties.load(getClass().getClassLoader().getResourceAsStream("weatherapi.properties"));
            req.setAttribute("weatherURL", properties.getProperty("weatherURL"));
            req.setAttribute("weatherKEY", properties.getProperty("weatherKEY"));
            logger.info("Weather API properties loaded.");
        } catch (Exception e) {
            logger.error("Failed to load weather properties", e);
        }

        req.getRequestDispatcher("/addJournal.jsp").forward(req, resp);
    }

    /**
     * Handles POST requests to save a new journal entry.
     *
     * @param req  the {@link HttpServletRequest} object containing the journal data parameters
     * @param resp the {@link HttpServletResponse} object containing the response the servlet returns to the client
     * @throws ServletException if the request for the POST could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the POST request
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            resp.sendRedirect("logIn");
            return;
        }

        // 파라미터 수집
        String title = req.getParameter("title");
        String cityName = req.getParameter("city");
        String journalDate = req.getParameter("date");
        String content = req.getParameter("content");
        String weather = req.getParameter("weather");


        JournalDao journalDao = new JournalDao();
        LocationDao locationDao = new LocationDao();

        try {
            // 1. Location 처리 (중복 확인 및 생성)
            Location location = null;
            List<Location> existingLocations = locationDao.getAll();
            for (Location loc : existingLocations) {
                if (loc.getName() != null && loc.getName().equalsIgnoreCase(cityName)) {
                    location = loc;
                    break;
                }
            }

            if (location == null) {
                location = new Location();
                location.setName(cityName);
                locationDao.insert(location);
                logger.info("New location saved: {}", cityName);
            }

            // 2. Journal 엔티티 생성 및 저장
            Journal journal = new Journal();
            journal.setUser(currentUser);
            journal.setLocation(location);
            journal.setTitle(title);
            journal.setContent(content);
            journal.setWeather(weather != null && !weather.isEmpty() ? weather : "Unknown");

            LocalDateTime createdDateTime;
            // LocalTime currentTime = LocalTime.now();
            LocalDateTime now = LocalDateTime.now();

            // createdDateTime = LocalDate.parse(journalDate).atTime(currentTime);
            try {
                if (journalDate != null && !journalDate.isEmpty()) {
                    // 날짜가 정상적으로 넘어왔을 때
                    createdDateTime = LocalDate.parse(journalDate).atTime(LocalTime.now());
                } else {
                    // 날짜가 없으면 현재 시간으로 세팅
                    createdDateTime = LocalDateTime.now();
                }
            } catch (Exception e) {
                logger.error("날짜 파싱 에러: " + journalDate, e);
                createdDateTime = LocalDateTime.now(); // 에러 나면 현재 시간으로 강제 고정
            }

            journal.setCreatedAt(createdDateTime);
            journal.setUpdatedAt(now);

            journalDao.insert(journal);
            logger.info("Successfully added journal: {}", title);

            // 3. 페이지 이동
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (isAdmin != null && isAdmin) {
                resp.sendRedirect("displayAllJournals");
            } else {
                resp.sendRedirect("displayJournalsByUser");
            }

        } catch (Exception e) {
            logger.error("Error adding journal", e);
            req.setAttribute("errorMessage", "저널 저장 실패");
            req.getRequestDispatcher("/addJournal.jsp").forward(req, resp);
        }
    }
}