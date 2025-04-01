package com.example.noteS.Note;
import com.example.noteS.CustomError.CustomResponseError;
import com.example.noteS.User.User;
import com.example.noteS.User.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    // add the NoteRepository to current Class
    public NoteService(
            NoteRepository noteRepository,
            UserRepository userRepository
    ) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }


    // default route to check if the API route is working
    public String check() {
        return "Success";
    }

    public ResponseEntity<?> getNotes(String id) {
        try {
            if(id == null) {
                throw new Exception("Invalid user ID");
            }

            Optional<User> user = userRepository.findById(id);
            if(!user.isPresent()) {
                throw new Exception("Invalid user");
            }

            ArrayList<Note> notes = noteRepository.getAllUserNotes(id);
            return ResponseEntity.ok(notes);

        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400,"Invalid user ID"));
        }
    }

    public ResponseEntity<?> getNoteById(String noteId, String user_id) {
        try {
            if(noteId == null) {
                throw new Exception("Invalid note ID");
            }

            Optional<Note> note = noteRepository.findById(noteId);
            if(!note.isPresent()) {
                throw new Exception("Invalid note ID");
            }

            Optional<User> user_given = userRepository.findById(user_id);
            Optional<User> real_user = userRepository.findById(note.get().getAuthor());

            if(!user_given.isPresent() || !user_given.get().getId().equals(real_user.get().getId())) {
                throw new Exception("Invalid User");
            }

            return ResponseEntity.ok(note);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400, e.getMessage()));
        }
    }

    public ResponseEntity<?> createNote(Note note_data) {
        try {
            if(note_data == null) {
                throw new Exception("Invalid data");
            }else {
                if(note_data.getTitle() == null || note_data.getTitle().length() < 2) {
                    throw new Exception("Title must be at least 3 letters");
                }

                return ResponseEntity.ok(noteRepository.save(note_data));
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponseError(400, e.getMessage()));
        }
    }
}
