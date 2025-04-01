package com.example.noteS.Note;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
public class Note {
    @Id
    private String id;

    private String title;
    private String content;
    private Date creationDate;
    private Date lastUpdate;
    private String author;

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

    public Note(String title, String content, Date creationDate, Date lastUpdate, String author) {
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.author = author;
    }

    public Note(String id, String title, String content, Date creationDate, Date lastUpdate, String author) {
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastUpdate(Date lastUpdate) {
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getAuthor() {
        return author;
    }
}
