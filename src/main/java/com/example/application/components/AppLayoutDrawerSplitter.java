package com.example.application.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;

/**
 * A splitter for the drawer of the Vaadin App Layout
 * @author Stefan Uebe
 */
@Tag("div")
@JsModule("./scripts/app-layout/drawer-splitter.js")
public class AppLayoutDrawerSplitter extends Component {

    public AppLayoutDrawerSplitter(AppLayout appLayout) {
        this(appLayout, 250);
    }

    public AppLayoutDrawerSplitter(AppLayout appLayout, int initSize) {
        this(appLayout, initSize, 150, 500);
    }

    public AppLayoutDrawerSplitter(AppLayout appLayout, int initSize, int minSize, int maxSize) {
        addClassName("splitter");
        getElement().executeJs("window.Vaadin.Flow._vaadinAppLayoutDrawerSplitterInit(this, $0, $1, $2, $3)"
                , appLayout, initSize, minSize, maxSize);
    }
}
