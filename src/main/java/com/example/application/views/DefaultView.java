package com.example.application.views;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author Stefan Uebe
 */
@Route(value = "", layout = MainLayout.class)
public class DefaultView extends VerticalLayout {

    public DefaultView() {
        setSizeFull();
        add(new Span("Hello :)"));

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
