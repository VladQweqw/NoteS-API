package com.example.noteS.Note;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Document
public class Note {
    @Id
    private String id;

    private String title;
    private String content = "";
    private String creationDate = getCurrentDate();
    private String lastUpdate = getCurrentDate();
    private String author;

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = currentDate.format(formatter);

        return formatted;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdate=" + lastUpdate +
                ", author='" + author + '\'' +
                '}';
    }

    public Note() {
    }

    public Note(String title, String content, String creationDate, String lastUpdate, String author) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.author = author;
    }

    public Note(String id, String title, String content, String creationDate, String lastUpdate, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.author = author;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastUpdate(String  lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getAuthor() {
        return author;
    }
}
