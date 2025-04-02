package com.example.noteS.Note;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
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

    @GetMapping("user/{user_id}")
    public ResponseEntity<?> getUserNotes(
            @PathVariable String user_id
    ) {
        return noteService.getNotes(user_id);
    }

    @GetMapping("{note_id}")
    public ResponseEntity<?> getNoteById(
            @PathVariable String note_id,
            @RequestParam String user_id
    ) {
        return noteService.getNoteById(note_id, user_id);
    }

    @PostMapping("")
    public ResponseEntity<?> createNote(
            @RequestBody Note note_data
    ) {
        return noteService.createNote(note_data);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateNote(
            @PathVariable String id,
            @RequestParam String user_id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content
    ) {
        return noteService.updateNote(id, user_id, title, content);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> updateNote(
            @RequestParam String user_id,
            @PathVariable String id
    ) {
        return noteService.deleteNote(id, user_id);
    }

}
