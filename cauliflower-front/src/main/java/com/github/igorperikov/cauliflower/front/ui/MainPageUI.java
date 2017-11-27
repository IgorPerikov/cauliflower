package com.github.igorperikov.cauliflower.front.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI(path = "/")
public class MainPageUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        getPage().setLocation("/auth");
    }
}
