package com.learn.project.journal.controller;

import com.learn.project.journal.model.User;
import com.learn.project.journal.service.UserService;
import com.learn.project.journal.service.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/public")
@RestController
public class PublicController {

    @Autowired
    UserService userService;
    @Autowired
    WeatherApiService weatherApiService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Healthy";
    }

    @PostMapping("/create-user")
    public void healthCheck(@RequestBody User user) {
        userService.saveNewUser(user);
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeatherInfo(@RequestParam String city) {
        return new ResponseEntity<>(weatherApiService.getWeatherData(city), HttpStatus.OK);
    }
}
