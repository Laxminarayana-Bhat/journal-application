package com.learn.project.journal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document("journal_entries")
@Builder
@Data
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime date;

}
