package com.example.application.views.sidenav;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author Stefan Uebe
 */
@Route(value = "view3", layout = MainLayout.class)
public class DefaultView3 extends VerticalLayout {

    public DefaultView3() {
        setSizeFull();
        add(new Span("Hello :)"));

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
