package com.learn.project.journal.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data
public class ConfigJournalApp {
    private String key;
    private String value;
}
