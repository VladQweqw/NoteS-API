package com.example.noteS.Note;


import com.example.noteS.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;

public interface NoteRepository extends MongoRepository<Note, String> {

    @Query("{ 'author': ?0 }")
    public ArrayList<Note> getAllUserNotes(String id);

    @Query("{ 'email': ?0}")
    public User existsByEmail(String email);
}
