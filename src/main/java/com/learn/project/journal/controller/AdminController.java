package com.learn.project.journal.controller;

import com.learn.project.journal.model.User;
import com.learn.project.journal.service.UserService;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/admin")
@RestController
public class AdminController {

    private final UserService userService;

    AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers() {
        List<User> all = userService.findAll();
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        userService.saveAdmin(user);
       return new ResponseEntity<>("Added admin",HttpStatus.CREATED);
    }


}
