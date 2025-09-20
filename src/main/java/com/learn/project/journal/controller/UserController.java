package com.learn.project.journal.controller;

import com.learn.project.journal.model.User;
import com.learn.project.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createUser(@RequestBody User user) {
//        userService.saveNewUser(user);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        System.out.println("user from request - "+user.getUserName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User byUserName = userService.findByUserName(authentication.getName());
        if (byUserName != null) {
            System.out.println("user from auth - "+byUserName.getUserName());
            byUserName.setUserName(byUserName.getUserName());
            byUserName.setPassword(user.getPassword());
            userService.saveEntry(byUserName);
        } //else add a rest exception handler in controller advice
        return new ResponseEntity<>(byUserName, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String userName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User byUserName = userService.findByUserName(authentication.getName());
        if (byUserName != null) {
            userService.deleteByUserName(userName);
        } else {
            return new ResponseEntity<>("User doesn't exist, with the user name " + userName, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>("Deleted user - " + userName, HttpStatus.ACCEPTED);
    }
}
