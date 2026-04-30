package com.traveljournal.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traveljournal.util.PropertiesLoader;
import com.weatherapi.Weather;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Data Access Object for fetching weather information from WeatherAPI.
 *
 * @author yyang22
 */
public class WeatherDao implements PropertiesLoader {

    Properties properties;
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String WEATHER_URL;
    public static String WEATHER_KEY;

    /**
     * Weather Dao Constructor
     * Load properties and initialize the url and key
     */
    public WeatherDao() {
        loadProperties();
    }

    /**
     * Function to load properties
     */
    private void loadProperties() {
        try {
            properties = loadProperties("/weatherapi.properties");
            WEATHER_URL = properties.getProperty("weatherURL");
            WEATHER_KEY = properties.getProperty("weatherKEY");
        } catch (IOException ioException) {
            logger.error("Cannot load properties..." + ioException.getMessage(), ioException);
        } catch (Exception e) {
            logger.error("Error loading properties" + e.getMessage(), e);
        }
    }


    /**
     * Retrieves the current weather for a specific city from API.
     *
     * @param city the city to get the weather
     * @return weather the weather object containing API response data
     */
    public Weather getWeather(String city) {

        Client client = ClientBuilder.newClient();
        WebTarget target =
                //client.target("c")
                client.target(WEATHER_URL)
                        .queryParam("key", WEATHER_KEY)
                        .queryParam("q", city);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = mapper.readValue(response, Weather.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return weather;

    }
}
