package com.learn.project.journal.controller;

import com.learn.project.journal.model.JournalEntry;
import com.learn.project.journal.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal/api/v1")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping("/create")
    public ResponseEntity<String> createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalService.saveEntry(journalEntry);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public List<JournalEntry> getAll() {
        return journalService.findAll();
    }

    @GetMapping("/get/{objectId}")
    public JournalEntry getByObjectId(@PathVariable ObjectId objectId) {
        return journalService.findById(objectId).orElse(null);
    }

    @DeleteMapping("wipe/{objectId}")
    public ResponseEntity<String> deleteById(@PathVariable ObjectId objectId) {
        journalService.deleteById(objectId);
        return new ResponseEntity<>(objectId + " deleted", HttpStatus.ACCEPTED);
    }

    @PutMapping("put/id/{id}")
    //path variable - we can put in link
    //request param - we can pass it as a parameter
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry) {
        //? - wildcard, it can return any type
        JournalEntry old = journalService.findById(id).orElse(null);

        if (old != null) {
            old.setContent(journalEntry.getContent());
            old.setTitle(journalEntry.getTitle());
        }
        journalService.saveEntry(old);
        return new ResponseEntity<>(old, HttpStatus.ACCEPTED);
    }
}

