package com.github.igorperikov.cauliflower.notes.controller;

import com.github.igorperikov.cauliflower.common.dto.notes.ContentWrapperTO;
import com.github.igorperikov.cauliflower.common.dto.notes.NoteTO;
import com.github.igorperikov.cauliflower.notes.converter.NotesConverter;
import com.github.igorperikov.cauliflower.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/notes")
public class NotesController {
    private final NotesService notesService;
    private final NotesConverter notesConverter;

    @Autowired
    public NotesController(NotesService notesService, NotesConverter notesConverter) {
        this.notesService = notesService;
        this.notesConverter = notesConverter;
    }

    @GetMapping
    public Flux<NoteTO> getNotes(@RequestHeader(name = "X-User-Id") UUID userId) {
        return notesService
                .getPage(userId)
                .map(notesConverter::convertFromEntity);
    }

    @PostMapping
    public Mono<NoteTO> createNote(
            @RequestHeader(name = "X-User-Id") UUID userId,
            @RequestBody @Valid ContentWrapperTO contentWrapperTO
    ) {
        return notesService
                .createNote(userId, contentWrapperTO.getContent())
                .map(notesConverter::convertFromEntity);
    }

    /**
     * @return complete object in case client want to resurrect this note
     */
    @DeleteMapping("/{uuid}")
    public Mono<Boolean> deleteNote(
            @RequestHeader(name = "X-User-Id") UUID userId,
            @PathVariable(name = "uuid") UUID noteId
    ) {
        return notesService.deleteNote(userId, noteId);
    }
}
