package com.learn.project.journal.controller;

import com.learn.project.journal.model.User;
import com.learn.project.journal.service.UserDetailServiceImpl;
import com.learn.project.journal.service.UserService;
import com.learn.project.journal.service.WeatherApiService;
import com.learn.project.journal.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/public")
@RestController
public class PublicController {

    @Autowired
    UserService userService;
    @Autowired
    WeatherApiService weatherApiService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Healthy";
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
            String s = jwtUtils.generateToken(user.getUserName());
            return new ResponseEntity<>(s,HttpStatus.OK);
        } catch (Exception e){
            log.error("e: ", e);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeatherInfo(@RequestParam String city) {
        return new ResponseEntity<>(weatherApiService.getWeatherData(city), HttpStatus.OK);
    }
}
