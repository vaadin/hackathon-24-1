package com.example.application.views;

import com.example.application.views.about.AboutView;
import com.example.application.views.other.OtherView;
import com.example.application.views.warnings.NotificationsView;
import com.example.application.views.warnings.ParagraphView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Herberts Test App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        // SideNav is a production-ready official component under a feature flag.
        // However, it has accessibility issues and is missing some features.
        // Both will be addressed in an upcoming minor version.
        // These changes are likely to cause some breaking change to the custom css
        // applied to the component.
        SideNav nav = new SideNav("Siiiide");
        nav.setCollapsible(true);

        var warningItems = new SideNavItem("Warnings");
        warningItems.setPrefixComponent(LineAwesomeIcon.EXCLAMATION_TRIANGLE_SOLID.create());
        warningItems.addItem(new SideNavItem("Notifications", NotificationsView.class, LineAwesomeIcon.BELL_SOLID.create()));
        warningItems.addItem(new SideNavItem("Paragraphs", ParagraphView.class, LineAwesomeIcon.PARAGRAPH_SOLID.create()));
        warningItems.setExpanded(true);
        nav.addItem(warningItems);

        var otherItems = new SideNavItem("Other", OtherView.class, LineAwesomeIcon.ARCHIVE_SOLID.create());
        var external = new SideNavItem("External link", "https://example.com", LineAwesomeIcon.LINK_SOLID.create());
        otherItems.addItem(external);

        var infoTextItem = new SideNavItem("Just some info here");
        otherItems.addItem(infoTextItem);

        nav.addItem(otherItems);
        nav.addItem(new SideNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
