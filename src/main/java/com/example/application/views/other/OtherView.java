package com.example.application.views.other;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Other")
@Route(value = "other", layout = MainLayout.class)
public class OtherView extends VerticalLayout {
    public OtherView() {
        var p = new Paragraph("This is just a view that tests clicking on expandable side nav button.");
        p.setMaxWidth("400px");
        add(p);
    }
}
