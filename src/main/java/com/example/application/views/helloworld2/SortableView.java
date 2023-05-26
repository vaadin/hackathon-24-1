package com.example.application.views.helloworld2;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;

@PageTitle("Sortable")
@Route(value = "sortable", layout = MainLayout.class)
public class SortableView extends HorizontalLayout {
    public SortableView() {
        // add your layout
        VVerticalLayout verticalLayout = new VVerticalLayout();

        add(verticalLayout);
    }

}
