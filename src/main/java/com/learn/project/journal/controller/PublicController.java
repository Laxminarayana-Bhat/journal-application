package com.learn.project.journal.controller;

import com.learn.project.journal.model.User;
import com.learn.project.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/public")
@RestController
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Healthy";
    }

    @PostMapping("/create-user")
    public void healthCheck(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
