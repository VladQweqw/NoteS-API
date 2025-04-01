package com.example.noteS.Note;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class Note {
    @Id
    private String id;

    private String title;
    private String content;
    private Date creationDate;
    private Date lastUpdate;
    private String author;
}
