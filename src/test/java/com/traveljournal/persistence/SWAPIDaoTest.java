package com.traveljournal.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SWAPIDaoTest {

    @Test
    void getWeatherSuccess() {
        SWAPIDao dao = new SWAPIDao();
        assertEquals("London", dao.getWeather().getLocation().getName());
    }
}