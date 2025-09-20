package com.learn.project.journal.controller;

import com.learn.project.journal.model.JournalEntry;
import com.learn.project.journal.model.User;
import com.learn.project.journal.service.JournalService;
import com.learn.project.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/journals")
public class JournalController {

    @Autowired
    JournalService journalService;
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createEntry(@RequestBody JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        journalEntry.setDate(LocalDateTime.now());
        User user = userService.findByUserName(authentication.getName());
        journalService.saveEntry(journalEntry, user.getUserName());
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User byUserName = userService.findByUserName(authentication.getName());
        return new ResponseEntity<>(userService.findByUserName(authentication.getName()).getJournalEntryList(), HttpStatus.OK);
    }

    @GetMapping("/get/{objectId}")
    public ResponseEntity<?> getByObjectId(@PathVariable ObjectId objectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User byUserName = userService.findByUserName(name);
        List<JournalEntry> entry = byUserName.getJournalEntryList().stream().filter(x -> x.getId().equals(objectId)).toList();
        if (!entry.isEmpty()) {
            return new ResponseEntity<>(entry.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("wipe/{objectId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId objectId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return new ResponseEntity<>(journalService.deleteById(objectId, name), HttpStatus.ACCEPTED);
    }

    @PutMapping("put/{id}")
    //path variable - we can put in link
    //request param - we can pass it as a parameter
    public ResponseEntity<?> updateJournal(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        //? - wildcard, it can return any type
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User byUserName = userService.findByUserName(name);
        List<JournalEntry> entry = byUserName.getJournalEntryList().stream().filter(x -> x.getId().equals(id)).toList();

        JournalEntry old = entry.get(0);
        if (old != null) {
            old.setContent(journalEntry.getContent());
            old.setTitle(journalEntry.getTitle());
            old.setDate(LocalDateTime.now());
        }
        journalService.saveUpdatedJournal(old);
        return new ResponseEntity<>(old, HttpStatus.ACCEPTED);
    }
}

