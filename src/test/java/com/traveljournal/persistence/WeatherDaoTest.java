package com.traveljournal.persistence;

import com.weatherapi.Weather;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherDaoTest {

    /*
    @Test
    void getWeather() {
        WeatherDao dao = new WeatherDao();
        assertEquals("London", dao.getWeather().getLocation().getName());
    }
    */

    @Test
    void testGetWeather() {
        WeatherDao dao = new WeatherDao();
        Weather weather = dao.getWeather("London");
        assertEquals("London", weather.getLocation().getName());
    }

    /**
     * Tests for when non-existent city is requested.
     * Expects a {@link javax.ws.rs.BadRequestException} because the current
     * WeatherDao implementation does not handle API error responses internally.
     */
    @Test
    void testGetWeatherCityNotFound() {
        WeatherDao dao = new WeatherDao();

        // 1. 예외가 발생할 것을 기대하고 assertThrows를 사용합니다.
        // Jersey 클라이언트가 던지는 BadRequestException이 발생하는지 확인합니다.
        assertThrows(javax.ws.rs.BadRequestException.class, () -> {
            dao.getWeather("NonExistentCity12345");
        }, "존재하지 않는 도시를 조회하면 BadRequestException이 발생해야 합니다.");
    }
}