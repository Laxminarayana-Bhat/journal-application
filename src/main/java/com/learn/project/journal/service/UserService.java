package com.learn.project.journal.service;

import com.learn.project.journal.model.User;
import com.learn.project.journal.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void saveEntry(User user) {
        userEntryRepository.save(user);
    }

    @Transactional
    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        saveEntry(user);
    }

    @Transactional
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        saveEntry(user);
    }

    public List<User> findAll() {
        return userEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId objectId) {
        userEntryRepository.deleteById(objectId);
    }
    public void deleteByUserName(String userName) {
        userEntryRepository.deleteByUserName(userName);
    }


    public User findByUserName(String userName) {
        return userEntryRepository.findByUserName(userName);
    }

    public static void main(String[] args) {
        List<String> roles = List.of("ADMIN", "USER");
        String[] roleArray = roles.toArray(new String[0]);

        System.out.println(roleArray+"----"+roles);
    }
}
