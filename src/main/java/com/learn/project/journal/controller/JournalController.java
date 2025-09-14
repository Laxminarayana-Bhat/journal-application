package com.learn.project.journal.controller;

import com.learn.project.journal.model.JournalEntry;
import com.learn.project.journal.model.User;
import com.learn.project.journal.service.JournalService;
import com.learn.project.journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create/{userName}")
    public ResponseEntity<String> createEntry(@RequestBody JournalEntry journalEntry, @RequestParam String userName) {
        journalEntry.setDate(LocalDateTime.now());
        User user = userService.findByUserName(userName);

        journalService.saveEntry(journalEntry, userName);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("/getall/{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesByUser(@RequestParam String userName) {
        return new ResponseEntity<>(userService.findByUserName(userName).getJournalEntryList(), HttpStatus.OK);
    }

    @GetMapping("/get/{objectId}")
    public JournalEntry getByObjectId(@PathVariable ObjectId objectId) {
        return journalService.findById(objectId).orElse(null);
    }

    @DeleteMapping("wipe/{userName}/{objectId}")
    public ResponseEntity<String> deleteById(@PathVariable String userName, @PathVariable ObjectId objectId) {
        journalService.deleteById(objectId, userName);
        return new ResponseEntity<>(objectId + " deleted", HttpStatus.ACCEPTED);
    }

    @PutMapping("put/{userName}/{id}")
    //path variable - we can put in link
    //request param - we can pass it as a parameter
    public ResponseEntity<?> updateEntry(@PathVariable String userName, @PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        //? - wildcard, it can return any type
        JournalEntry old = journalService.findById(id).orElse(null);

        if (old != null) {
            old.setContent(journalEntry.getContent());
            old.setTitle(journalEntry.getTitle());
        }
        journalService.saveUpdatedJournal(old);
        return new ResponseEntity<>(old, HttpStatus.ACCEPTED);
    }
}

