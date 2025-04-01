package com.example.noteS.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class User {
    @Id
    private String id;

    private String nickname;
    private String password;

    private Date creation_date;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", creation_date=" + creation_date +
                '}';
    }

    public User() {
    }

    public User(String nickname, String password, Date creation_date) {
        this.nickname = nickname;
        this.password = password;
        this.creation_date = creation_date;
    }

    public User(String id, String nickname, String password, Date creation_date) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.creation_date = creation_date;
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

    public void setCreation_date(Date creation_date) {
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

    public Date getCreation_date() {
        return creation_date;
    }
}
