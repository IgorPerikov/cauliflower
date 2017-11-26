package com.github.igorperikov.cauliflower.front.ui;

import com.github.igorperikov.cauliflower.front.service.AuthService;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@SpringUI(path = "/auth")
@Theme("valo")
public class AuthUI extends UI {
    private static final Logger log = LoggerFactory.getLogger(AuthUI.class);
    private final AuthService authService;

    @Autowired
    public AuthUI(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected void init(VaadinRequest request) {
        AuthForm content = new AuthForm();

        Button loginButton = content.loginButton;
        Button registerButton = content.registerButton;

        TextField loginInput = content.loginInput;
        TextField passwordInput = content.passwordInput;

        UUID id = (UUID) VaadinSession.getCurrent().getAttribute("userId");
        if (id != null) {
            getPage().setLocation("/notes");
        }

        loginButton.addClickListener(event -> {
            UUID userId;
            try {
                userId = authService.authorize(loginInput.getValue(), passwordInput.getValue());
                if (userId == null) {
                    Notification.show("Authorization failed!", Notification.Type.WARNING_MESSAGE);
                    cleanFields(content);
                } else {
                    VaadinSession.getCurrent().setAttribute("userId", userId);
                    getPage().setLocation("/notes");
                }
            } catch (Exception e) {
                log.error("", e);
                Notification.show("Service is having problems, try again later!", Notification.Type.WARNING_MESSAGE);
            }
        });

        registerButton.addClickListener(event -> {
            UUID userId;
            try {
                userId = authService.registerNewUser(loginInput.getValue(), passwordInput.getValue());
                if (userId == null) {
                    Notification.show("Login already taken!", Notification.Type.WARNING_MESSAGE);
                    cleanFields(content);
                } else {
                    VaadinSession.getCurrent().setAttribute("userId", userId);
                    getPage().setLocation("/notes");
                }
            } catch (Exception e) {
                log.error("", e);
                Notification.show("Service is having problems, try again later!", Notification.Type.WARNING_MESSAGE);
            }
        });

        setContent(content);
    }

    private void cleanFields(AuthForm content) {
        content.loginInput.setValue("");
        content.passwordInput.setValue("");
    }
}
