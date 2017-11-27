package com.github.igorperikov.cauliflower.front.ui;

import com.github.igorperikov.cauliflower.common.dto.notes.NoteTO;
import com.github.igorperikov.cauliflower.front.service.NotesService;
import com.github.igorperikov.cauliflower.front.ui.generated.NoteComponent;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@SpringUI(path = "/notes")
@Theme("valo")
public class NotesUI extends UI {
    private static final Logger log = LoggerFactory.getLogger(NotesUI.class);

    private final NotesService notesService;

    @Autowired
    public NotesUI(NotesService notesService) {
        this.notesService = notesService;
    }

    @Override
    protected void init(VaadinRequest request) {
        Object id = VaadinSession.getCurrent().getAttribute("userId");
        if (id == null) {
            Notification.show("You are not logged in!", Notification.Type.ERROR_MESSAGE);
            getPage().setLocation("/auth");
        }
        UUID userId = (UUID) id;

        VerticalLayout page = new VerticalLayout();
        page.addComponent(new CreateNoteDialogComponentImpl(notesService, userId));

        GridLayout notesGrid = new GridLayout(3, 1);
        List<NoteTO> notes;
        try {
            notes = notesService.getNotes(userId);
            for (NoteTO note : notes) {
                NoteComponent component = new NoteComponentImpl(
                        notesService,
                        note.getNoteId(),
                        note.getContent(),
                        userId
                );
                notesGrid.addComponent(component);
            }
        } catch (IOException e) {
            log.error("", e);
            Notification.show(e.getMessage(), Notification.Type.WARNING_MESSAGE);
        }
        page.addComponent(notesGrid);

        setContent(page);
    }
}
