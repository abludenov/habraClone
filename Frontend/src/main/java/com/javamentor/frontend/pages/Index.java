package com.javamentor.frontend.pages;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("/")
public class Index extends VerticalLayout {

    public Index() {
        add(helloText);
    }

    Text helloText = new Text("Hello");
}
