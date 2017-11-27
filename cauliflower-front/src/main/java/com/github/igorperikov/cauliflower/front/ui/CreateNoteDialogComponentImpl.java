package com.github.igorperikov.cauliflower.front.ui;

import com.github.igorperikov.cauliflower.front.service.NotesService;
import com.github.igorperikov.cauliflower.front.ui.generated.CreateNoteDialogComponent;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

import java.io.IOException;
import java.util.UUID;

public class CreateNoteDialogComponentImpl extends CreateNoteDialogComponent {
    public CreateNoteDialogComponentImpl(NotesService notesService, UUID userId) {
        createNewNoteButton.addClickListener(event -> {
            if (newNoteContent.getValue() == null || newNoteContent.getValue().isEmpty()) {
                Notification.show("You cant create empty note", Notification.Type.WARNING_MESSAGE);
            } else {
                try {
                    notesService.createNote(userId, newNoteContent.getValue());
                    Page.getCurrent().reload();
                } catch (IOException e) {
                    Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
    }
}
