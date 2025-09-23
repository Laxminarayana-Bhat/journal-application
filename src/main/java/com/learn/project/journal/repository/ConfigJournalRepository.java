package com.learn.project.journal.repository;

import com.learn.project.journal.model.ConfigJournalApp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalRepository extends MongoRepository<ConfigJournalApp, String> {
}
