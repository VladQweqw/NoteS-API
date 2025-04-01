package com.example.noteS.Note;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/api/note")
public class NoteController {
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("check")
    public String check() {
        return noteService.check();
    }

    @GetMapping("all")
    public ResponseEntity<?> getUserNotes(
            @PathVariable(required = false) String user_id
    ) {
        return noteService.getNotes(user_id);
    }

    @PostMapping("")
    public ResponseEntity<?> createNote(
            @RequestBody Note note_data
    ) {
        return noteService.createNote(note_data);
    }

}
