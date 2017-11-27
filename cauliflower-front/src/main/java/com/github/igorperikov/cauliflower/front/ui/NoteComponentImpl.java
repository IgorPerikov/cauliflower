package com.github.igorperikov.cauliflower.front.ui;

import com.github.igorperikov.cauliflower.front.service.NotesService;
import com.github.igorperikov.cauliflower.front.ui.generated.NoteComponent;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

import java.io.IOException;
import java.util.UUID;

public class NoteComponentImpl extends NoteComponent {
    public NoteComponentImpl(NotesService notesService, UUID noteId, String content, UUID userId) {
        noteContent.setValue(content);
        noteContent.setEnabled(false);
        deleteNoteButton.addClickListener(event -> {
            try {
                notesService.deleteNote(userId, noteId);
                Page.getCurrent().reload();
            } catch (IOException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        });
    }
}
