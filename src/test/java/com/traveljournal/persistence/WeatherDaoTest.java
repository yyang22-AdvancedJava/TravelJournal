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
}