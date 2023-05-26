package com.example.application.views.sidenav;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * @author Stefan Uebe
 */
@Route(value = "view2", layout = MainLayout.class)
public class DefaultView2 extends VerticalLayout {

    public DefaultView2() {
        setSizeFull();
        add(new Span("Hello :)"));

        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }
}
