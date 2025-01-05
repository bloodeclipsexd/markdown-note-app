package org.example.markdownnoteapp.controller;

import org.example.markdownnoteapp.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("api/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public String uploadFile(@RequestParam MultipartFile file) {
        return noteService.saveNote(file);
    }

    @PostMapping(consumes = {"text/plain"})
    public String createAndUploadFile(@RequestBody String content) {
        return noteService.createAndSaveNote(content);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getFile(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("noteContent", noteService.getNoteContent(id));

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/api/render/note")
                .build();
    }
}
