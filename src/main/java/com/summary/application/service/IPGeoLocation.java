package com.summary.application.service;

import io.ipgeolocation.api.GeolocationParams;
import io.ipgeolocation.api.IPGeolocationAPI;
import  io.ipgeolocation.api.Geolocation;

public class IPGeoLocation {
    public static void main(String[] args) {
            // Create IPGeolocationAPI object, passing your valid API key
            IPGeolocationAPI api = new IPGeolocationAPI("YOUR_API_KEY");
            // Get geolocation for IP address (1.1.1.1) and fields (geo, time_zone and currency)
            GeolocationParams geoParams = new GeolocationParams();
            geoParams.setIPAddress("1.1.1.1");
            geoParams.setFields("geo,time_zone,currency");

            Geolocation geolocation = api.getGeolocation(geoParams);

// Check if geolocation lookup was successful
            if(geolocation.getStatus() == 200) {
                System.out.println(geolocation.getCountryName());
                System.out.println(geolocation.getCurrency().getName());
                System.out.println(geolocation.getTimezone().getCurrentTime());
            } else {
                System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
            }

// Get geolocation in Russian** for IP address (1.1.1.1) and all fields
            geoParams = new GeolocationParams();
            geoParams.setIPAddress("1.1.1.1");
            geoParams.setLang("ru");

            geolocation = api.getGeolocation(geoParams);

// Check if geolocation lookup was successful
            if(geolocation.getStatus() == 200) {
                System.out.println(geolocation.getIPAddress());
                System.out.println(geolocation.getCountryName());
            } else {
                System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
            }

// Get geolocation for the calling machine's IP address for all fields
            geolocation = api.getGeolocation();

            if(geolocation.getStatus() == 200) {
                System.out.println(geolocation.getCountryCode2());
                System.out.println(geolocation.getTimezone().getCurrentTime());
            } else {
                System.out.printf("Status Code: %d, Message: %s\n", geolocation.getStatus(), geolocation.getMessage());
            }
    }
}
