package com.example.application.views;

import com.example.application.components.AppLayoutDrawerSplitter;
import com.example.application.views.grids.EagerLoadingView;
import com.example.application.views.grids.LazyLoadingView;
import com.example.application.views.sidenav.DefaultView1;
import com.example.application.views.sidenav.DefaultView2;
import com.example.application.views.sidenav.DefaultView3;
import com.example.application.views.sidenav.DefaultView4;
import com.vaadin.flow.component.Component;
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
        H1 appName = new H1("My App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());
        scroller.setScrollDirection(Scroller.ScrollDirection.BOTH);

        addToDrawer(new AppLayoutDrawerSplitter(this), header,
                scroller,
                createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        SideNavItem cat1 = new SideNavItem("Category 1");
        nav.addItem(cat1);

        SideNavItem cat1_1 = new SideNavItem("Category 1.1");
        SideNavItem cat1_2 = new SideNavItem("Category 1.2");
        cat1.addItem(cat1_1, cat1_2);

        cat1_1.addItem(createViewItem("Page 1", DefaultView1.class));
        cat1_1.addItem(createViewItem("Page 2", DefaultView2.class));
        cat1_2.addItem(createViewItem("Page 3", DefaultView3.class));
        cat1_2.addItem(createViewItem("Page 4", DefaultView4.class));

        nav.addItem(createViewItem("Eager Loading Grid", EagerLoadingView.class));
        nav.addItem(createViewItem("Lazy Loading Grid", LazyLoadingView.class));

        return nav;
    }

    private static SideNavItem createViewItem(String label, Class<? extends Component> view) {
        SideNavItem navItem = new SideNavItem(label, view);
        navItem.getElement().executeJs("this.addEventListener('expanded-changed', e => requestAnimationFrame(() => e.target.expanded = false));");

        return navItem;
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
