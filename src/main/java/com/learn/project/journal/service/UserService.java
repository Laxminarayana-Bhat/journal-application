package com.learn.project.journal.service;

import com.learn.project.journal.model.User;
import com.learn.project.journal.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Transactional
    public void saveEntry(User user) {
        userEntryRepository.save(user);
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

    public User findByUserName(String userName){
        return userEntryRepository.findByUserName(userName);
    }
}
