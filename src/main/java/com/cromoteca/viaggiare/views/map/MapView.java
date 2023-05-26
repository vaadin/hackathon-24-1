package com.cromoteca.viaggiare.views.map;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.Feature;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.map.configuration.feature.MarkerFeature;
import com.vaadin.flow.component.map.configuration.layer.FeatureLayer;
import com.vaadin.flow.component.map.configuration.style.Icon;
import com.vaadin.flow.component.map.configuration.style.TextStyle;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Map")
@Route(value = "map")
@RouteAlias(value = "")
public class MapView extends HorizontalLayout {

    private final Map map = new Map();

    public MapView() {
        addClassName("map-view");
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        map.getElement().setAttribute("theme", "borderless");
        map.getElement().setAttribute("class", "map");
        map.setHeightFull();

        add(map);

        configureMap();
    }

    private void centerMapOn(Coordinate location) {
        View view = map.getView();
        view.setCenter(location);
        view.setZoom(3);
    }

    private void configureMap() {

        FeatureLayer featureLayer = this.map.getFeatureLayer();

        for (Feature f : featureLayer.getFeatures().toArray(Feature[]::new)) {
            featureLayer.removeFeature(f);
        }
        Coordinate coordinate = new Coordinate(0, 0);
        MarkerFeature feature = new MarkerFeature(coordinate);
        feature.setDraggable(true);
        featureLayer.addFeature(feature);
        centerMapOn(coordinate);

        map.addFeatureDropListener(event -> {
            MarkerFeature droppedMarker = (MarkerFeature) event.getFeature();
            Coordinate destination = event.getCoordinate();
            String url = String.format("https://geocode.maps.co/reverse?lat=%f&lon=%f", destination.getY(), destination.getX());
            try {
                String response = callRestApi(url);
                // System.out.println(response);
                JsonNode jsonNode = parseJsonResponse(response);
                JsonNode address = jsonNode.get("address");

                if (address == null) {
                    Notification.show("No address found");
                    resetMarker(droppedMarker);
                    return;
                }

                String city = getAddressField(address, "city");
                String countryName = getAddressField(address, "country");
                String countryCode = getAddressField(address, "country_code");
                String place = city != null ? city + ", " + countryName : countryName;
                String flag = countryCodeToFlag(countryCode);
                Icon weatherIcon = getWeatherIcon(destination);

                String color = getColor(destination.getY());

                // Create a marker with a custom text style
                TextStyle textStyle = new TextStyle();
                // Customize font and color
                textStyle.setFont("bold 16px sans-serif");
                textStyle.setStroke(color, 3);
                textStyle.setFill("#ffffff");
                // Position text to the right of the icon
                textStyle.setTextAlign(TextStyle.TextAlign.LEFT);
                textStyle.setOffset(22, -18);

                droppedMarker.setIcon(weatherIcon);
                droppedMarker.setText(place + " " + flag);
                droppedMarker.setTextStyle(textStyle);
            } catch (Exception e) {
                Notification.show(e.getMessage());
                resetMarker(droppedMarker);
            }
        });

        //Notification notification = new Notification();
        Notification.show("Start travelling: drag the marker to the destination");
    }

    private String callRestApi(String apiUrl) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        }
        return null;
    }

    private JsonNode parseJsonResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(jsonResponse);
    }

    private String getAddressField(JsonNode address, String fieldName) {
        JsonNode field = address.get(fieldName);
        if (field != null) {
            return field.asText();
        }
        return null;
    }

    public String countryCodeToFlag(String code) {
        StringBuilder flag = new StringBuilder();

        for (char c : code.toUpperCase().toCharArray()) {
            flag.append(Character.toChars(c + 127397));
        }

        return flag.toString();
    }

    private static final String API_KEY = ""; // INSERT YOUR API KEY HERE
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";

    public static Icon getWeatherIcon(Coordinate coordinate) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = BASE_URL + "?lat=" + coordinate.getY() + "&lon=" + coordinate.getX() + "&appid=" + API_KEY;
        HttpGet httpGet = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(result);
                // System.out.println(rootNode);
                JsonNode weatherNode = rootNode.path("weather").get(0);
                // String weatherCondition = weatherNode.path("main").asText();
                String icon = weatherNode.path("icon").asText();

                return getIcon(icon);
            }
        }

        return null;
    }

    public static String getColor(double latitude) {
        int r, g, b;

        if (latitude < 0) { // Southern hemisphere
            latitude = -latitude; // Convert to positive for simplicity
        }

        if (latitude <= 30) { // Tropical or near-tropical
            r = 255;
            g = (int) (255 - latitude * 255 / 30);
            b = 0;
        } else if (latitude <= 60) { // Temperate
            latitude -= 30;
            r = (int) (255 - latitude * 255 / 30);
            g = 0;
            b = (int) (latitude * 255 / 30);
        } else { // Near-polar or polar
            latitude -= 60;
            r = 0;
            g = 0;
            b = 255;
        }

        // Convert RGB to hexadecimal color
        String color = String.format("#%02X%02X%02X", r, g, b);

        return color;
    }

    private static final String WEATHER_BASE_URL = "http://openweathermap.org/img/wn/";

    private static final HashMap<String, Icon> iconCache = new HashMap<>();

    public static Icon getIcon(String iconCode) {
        Icon icon = iconCache.get(iconCode);
        if (icon == null) {
            Icon.Options iconOptions = new Icon.Options();
            iconOptions.setSrc(WEATHER_BASE_URL + iconCode + ".png");
            icon = new Icon(iconOptions);
            iconCache.put(iconCode, icon);
        }
        return icon;
    }

    private void resetMarker(MarkerFeature droppedMarker) {
        droppedMarker.setText("");
        droppedMarker.setIcon(MarkerFeature.PIN_ICON);
    }

}
