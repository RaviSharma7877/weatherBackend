package com.RSstudies.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin; // Import CrossOrigin annotation

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String XRapidAPIKey = "5e3134c76amsh5cc4891dcc4ecd6p104170jsnf6803a56aedb";
    private static final String XRapidAPIHost= "forecast9.p.rapidapi.com";

    @CrossOrigin(origins = "https://main--weatherforcastdata.netlify.app/") // Add CrossOrigin annotation with allowed origin
    public Object getDataList(String country) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", XRapidAPIKey);
            headers.set("X-RapidAPI-Host", XRapidAPIHost);
            String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/"+country+"/summary/";

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers),String.class);

            return response.getBody();

        } catch (Exception e) {
            log.error("Something went wrong with Rapid API");
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling endpoint ",
                    e
            );
        }
    }
}