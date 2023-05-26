package de.sissbruecker.markertexthelper;

import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.map.configuration.style.TextStyle;

public class MarkerTextHelper {

    public enum TextPosition {
        TOP, BOTTOM, LEFT, RIGHT;
    }

    public static void setTextPosition(MarkerFeature feature, TextPosition position, int spacing) {
        Icon icon = feature.getIcon();
        if (icon == null || icon.getImgSize() == null || icon.getAnchor() == null || icon.getAnchorOrigin() == null) {
            throw new IllegalArgumentException("Marker must have an icon with an explicit size, anchor and anchor origin.");
        }

        if (feature.getTextStyle() == null) {
            feature.setTextStyle(new TextStyle());
        }
        TextStyle textStyle = feature.getTextStyle();

        double offsetX = 0, offsetY = 0;
        double imageWidth = icon.getImgSize().getWidth() * icon.getScale();
        double imageHeight = icon.getImgSize().getHeight() * icon.getScale();
        double imageLeft = -imageWidth * icon.getAnchor().getX();
        double imageTop = -imageHeight * icon.getAnchor().getY();

        if (icon.getAnchorOrigin() == Icon.AnchorOrigin.TOP_RIGHT || icon.getAnchorOrigin() == Icon.AnchorOrigin.BOTTOM_RIGHT) {
            imageLeft = -imageLeft - imageWidth;
        }

        if (icon.getAnchorOrigin() == Icon.AnchorOrigin.BOTTOM_LEFT || icon.getAnchorOrigin() == Icon.AnchorOrigin.BOTTOM_RIGHT) {
            imageTop = -imageTop - imageHeight;
        }

        if (position == TextPosition.LEFT) {
            textStyle.setTextAlign(TextStyle.TextAlign.RIGHT);
            textStyle.setTextBaseline(TextStyle.TextBaseline.MIDDLE);
            offsetX = imageLeft - spacing;
            offsetY = imageTop + (imageHeight / 2);
        }

        if (position == TextPosition.RIGHT) {
            textStyle.setTextAlign(TextStyle.TextAlign.LEFT);
            textStyle.setTextBaseline(TextStyle.TextBaseline.MIDDLE);
            offsetX = imageLeft + imageWidth + spacing;
            offsetY = imageTop + (imageHeight / 2);
        }

        if (position == TextPosition.TOP) {
            textStyle.setTextAlign(TextStyle.TextAlign.CENTER);
            textStyle.setTextBaseline(TextStyle.TextBaseline.BOTTOM);
            offsetX = imageLeft + (imageWidth / 2);
            offsetY = imageTop - spacing;
        }

        if (position == TextPosition.BOTTOM) {
            textStyle.setTextAlign(TextStyle.TextAlign.CENTER);
            textStyle.setTextBaseline(TextStyle.TextBaseline.TOP);
            offsetX = imageLeft + (imageWidth / 2);
            offsetY = imageTop + imageHeight + spacing;
        }

        textStyle.setOffset((int) Math.round(offsetX), (int) Math.round(offsetY));
    }
}
