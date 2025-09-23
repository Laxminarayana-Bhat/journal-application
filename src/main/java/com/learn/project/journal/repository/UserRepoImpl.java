package com.learn.project.journal.repository;

import com.learn.project.journal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserFor() {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("admin"));
        return mongoTemplate.find(query, User.class);
    }

}
