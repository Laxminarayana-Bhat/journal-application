package com.learn.project.journal.repository;

import com.learn.project.journal.model.JournalEntry;
import com.learn.project.journal.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
}

