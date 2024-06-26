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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WeatherServise {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String XRapidAPIKey = "20ff479bccmsha2fc0220fbb7f2ap119675jsnada6d90b3a5f";
	private static final String XRapidAPIHost= "forecast9.p.rapidapi.com";
	
	public Object getdataList(String country) {
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-RapidAPI-Key", XRapidAPIKey);
			headers.set("X-RapidAPI-Host", XRapidAPIHost);
			String url = "https://forecast9.p.rapidapi.com/rapidapi/forecast/"+country+"/summary/";
			
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers),String.class);
			
			return response.getBody();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ResponseStatusException(
					HttpStatus.BAD_GATEWAY,
					"Exception while calling endpint ",
					e
					);
		}
	}
}