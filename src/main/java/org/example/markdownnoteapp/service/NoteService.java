package org.example.markdownnoteapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.markdownnoteapp.dao.NoteDao;
import org.example.markdownnoteapp.repository.NoteRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoteService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public String saveNote(MultipartFile file) {
        NoteDao noteDao;
        try {
            noteDao = NoteDao.builder()
                    .title(file.getOriginalFilename())
                    .content(file.getBytes())
                    .build();

            noteRepository.save(noteDao);
            return "Saved";
        } catch (Exception e) {
            log.error("Error saving note", e);
            return "Error saving note";
        }
    }

    public String createAndSaveNote(String content) {
        NoteDao noteDao;
        try {
            noteDao = NoteDao.builder()
                    .content(content.getBytes())
                    .build();

            return "Saved note with title: %s".formatted(noteRepository.save(noteDao).getTitle());
        } catch (Exception e) {
            log.error("Error saving note", e);
            return "Error saving note";
        }
    }

    public String getNoteContent(Long id) {
        NoteDao noteDao = noteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return new String(noteDao.getContent());
    }
}
