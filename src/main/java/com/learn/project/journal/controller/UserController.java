package com.learn.project.journal.controller;

import com.learn.project.journal.model.User;
import com.learn.project.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.saveEntry(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User byUserName = userService.findByUserName(user.getUserName());
        if (byUserName!=null){
            byUserName.setUserName(byUserName.getUserName());
            byUserName.setPassword(byUserName.getPassword());
            userService.saveEntry(byUserName);
        } //else add a rest exception handler in controller advice
        return new ResponseEntity<>(byUserName,HttpStatus.NO_CONTENT);
    }
}
