package com.example.noteS.User;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserDTO {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private String nickname;
    private String creation_date;

    public UserDTO() {

    }

    public UserDTO(String id, String email, String nickname, String creation_date) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.creation_date = creation_date;
    }

    public UserDTO(String email, String nickname, String creation_date) {
        this.email = email;
        this.nickname = nickname;
        this.creation_date = creation_date;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.creation_date = user.getCreation_date();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", creation_date='" + creation_date + '\'' +
                '}';
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
