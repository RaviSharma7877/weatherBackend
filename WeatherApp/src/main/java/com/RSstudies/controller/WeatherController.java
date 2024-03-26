package com.RSstudies.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RSstudies.servise.WeatherServise;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WeatherController {

	private final WeatherServise weatherService;
	
	@GetMapping("/getweatherdata")
	public ResponseEntity<?> getData(@RequestParam String country) {
		return ResponseEntity.ok(weatherService.getdataList(country));
	}
}
