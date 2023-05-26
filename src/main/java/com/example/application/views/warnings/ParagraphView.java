package com.example.application.views.warnings;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Paragraphs")
@Route(value = "paragraphs", layout = MainLayout.class)
public class ParagraphView extends VerticalLayout {

    public ParagraphView() {
        addParagraphVariant("base");
        addParagraphVariant("primary");
        addParagraphVariant("success");
        addParagraphVariant("warning");
        addParagraphVariant("error");
    }

    private void addParagraphVariant(String lumoColor) {
        var text = String.format("This is a message in a paragraph that shows %s color variant. This text " +
                "needs to be long enough to break into multiple lines and look like an actually message.", lumoColor);
        var p = new Paragraph(text);
        p.setWidth("400px");
        p.getStyle().set("padding", "10px")
                .set("border-radius", "var(--lumo-border-radius-m)")
                .set("background-color", String.format("var(--lumo-%s-color)", lumoColor))
                .set("color", String.format("var(--lumo-%s-contrast-color)", lumoColor));
        add(p);
    }
}
