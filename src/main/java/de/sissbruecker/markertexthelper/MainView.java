package de.sissbruecker.markertexthelper;

import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Map Marker Text Helpers")
@Route(value = "")
public class MainView extends HorizontalLayout {

    private final Map map = new Map();

    public MainView() {
        setMargin(true);

        addPin(new Coordinate(-75, 20), MarkerTextHelper.TextPosition.LEFT);
        addPin(new Coordinate(-25, 20), MarkerTextHelper.TextPosition.TOP);
        addPin(new Coordinate(25, 20), MarkerTextHelper.TextPosition.BOTTOM);
        addPin(new Coordinate(75, 20), MarkerTextHelper.TextPosition.RIGHT);

        addDot(new Coordinate(-75, -20), MarkerTextHelper.TextPosition.LEFT);
        addDot(new Coordinate(-25, -20), MarkerTextHelper.TextPosition.TOP);
        addDot(new Coordinate(25, -20), MarkerTextHelper.TextPosition.BOTTOM);
        addDot(new Coordinate(75, -20), MarkerTextHelper.TextPosition.RIGHT);

        add(map);
    }

    private void addPin(Coordinate coordinate, MarkerTextHelper.TextPosition textPosition) {
        MarkerFeature marker = new MarkerFeature(coordinate);
        marker.setText("Label position:\n" + textPosition.name().toLowerCase());
        map.getFeatureLayer().addFeature(marker);

        MarkerTextHelper.setTextPosition(marker, textPosition, 5);
    }

    private void addDot(Coordinate coordinate, MarkerTextHelper.TextPosition textPosition) {
        MarkerFeature marker = new MarkerFeature(coordinate);
        marker.setIcon(MarkerFeature.POINT_ICON);
        marker.setText("Label position:\n" + textPosition.name().toLowerCase());
        map.getFeatureLayer().addFeature(marker);

        MarkerTextHelper.setTextPosition(marker, textPosition, 5);
    }
}
