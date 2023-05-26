package com.example.application.views.warnings;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Notifications")
@Route(value = "notifications", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class NotificationsView extends HorizontalLayout {

    public NotificationsView() {
        setMargin(true);
        addNotificationButton(NotificationVariant.LUMO_PRIMARY);
        addNotificationButton(NotificationVariant.LUMO_CONTRAST);
        addNotificationButton(NotificationVariant.LUMO_SUCCESS);
        addNotificationButton(NotificationVariant.LUMO_WARNING);
        addNotificationButton(NotificationVariant.LUMO_ERROR);
    }

    private void addNotificationButton(NotificationVariant variant) {
        var button = new Button(String.format("Show %s", variant.getVariantName()));
        button.addClickListener(clickEvent -> {
            var notification = new Notification(String.format("This is a %s notification", variant.getVariantName()), 2000, Notification.Position.TOP_END);
            notification.addThemeVariants(variant);
            notification.open();
        });
        add(button);
    }

}
