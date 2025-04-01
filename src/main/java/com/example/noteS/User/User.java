package com.example.noteS.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Document
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private String nickname;
    private String password;
    private String creation_date = getCurrentDate();

    public User() {
    }

    public User(String email, String nickname, String password, String creation_date) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.creation_date = creation_date;
    }

    public User(String id, String email, String nickname, String password, String creation_date) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.creation_date = creation_date;
    }

    private String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = currentDate.format(formatter);

        return formatted;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
