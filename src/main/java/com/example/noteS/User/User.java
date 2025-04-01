package com.example.noteS.User;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
public class User {
    @Id
    private String id;

    private String nickname;
    private String password;

    private Date creation_date;

}
