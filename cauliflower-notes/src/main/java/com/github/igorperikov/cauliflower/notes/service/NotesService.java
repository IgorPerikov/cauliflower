package com.github.igorperikov.cauliflower.notes.service;

import com.datastax.driver.core.utils.UUIDs;
import com.github.igorperikov.cauliflower.notes.entity.NoteEntity;
import com.github.igorperikov.cauliflower.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class NotesService {
    private final NotesRepository notesRepository;

    @Autowired
    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Flux<NoteEntity> getPage(UUID userId) {
        return notesRepository.getAll(userId);
    }

    public Mono<NoteEntity> createNote(UUID userId, String noteContent) {
        NoteEntity noteEntity = new NoteEntity(userId, UUIDs.timeBased(), noteContent);
        return notesRepository.createNote(noteEntity);
    }

    public Mono<Boolean> deleteNote(UUID userId, UUID noteId) {
        return notesRepository.deleteNote(userId, noteId);
    }
}
