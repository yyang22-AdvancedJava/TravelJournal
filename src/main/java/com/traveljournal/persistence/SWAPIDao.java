package com.traveljournal.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapi.Weather;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class SWAPIDao {

    Weather getWeather() {

        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://api.weatherapi.com/v1/current.json")
                        .queryParam("key", "8fa7d4618f2847dab6c14137262702")
                        .queryParam("q", "London");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        Weather weather = null;
        try {
            weather = mapper.readValue(response, Weather.class);
        } catch (JsonProcessingException e) {
            //TODO set up loggin and write this to the log
            throw new RuntimeException(e);
        }
        return weather;

    }
}
