package com.learn.project.journal.service;

import com.learn.project.journal.model.JournalEntry;
import com.learn.project.journal.model.User;
import com.learn.project.journal.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JournalService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntryList().add(saved);
        userService.saveEntry(user);
    }

    @Transactional
    public void saveUpdatedJournal(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId objectId, String userName) {
        try {
            User user = userService.findByUserName(userName);
            boolean b = user.getJournalEntryList().removeIf(entry -> entry.getId().equals(objectId));
            if (b) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(objectId);
            }
            return b;
        } catch (RuntimeException e) {
            log.error("e: ", e);
            return false;
        }
    }
}
