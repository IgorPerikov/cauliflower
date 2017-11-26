package com.github.igorperikov.cauliflower.front.ui;

import com.github.igorperikov.cauliflower.front.service.NotesService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@SpringUI(path = "/notes")
@Theme("valo")
public class NotesUI extends UI {
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
        } else {
            UUID userId = (UUID) id;
        }
    }
}
