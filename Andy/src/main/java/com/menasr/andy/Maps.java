package com.menasr.andy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

@SuppressLint("DefaultLocale")
public class Maps {
    private final String MAPS_URL = "com.google.android.apps.maps";
    private final String LOC_MARKER_IN_MAPS_BY_LATLONG = "geo:0,0?q=%f,%f(%s)";
    private final String LOC_MARKER_IN_MAPS_BY_ADDRESS = "geo:0,0?q=%s";
    private final String SEARCH_PLACES = "geo:0,0?q=%s";
    private final String SEARCH_PLACES_BY_LOCATION = "geo:%f,%f?q=%s";

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param name name which is shown for that marker
     * @return true if successfully opened google maps, or false if google maps not found
     */

    public boolean showMakerInGoogleMap(Context context, Double latitude, Double longitude, String name) {
        String link = String.format(LOC_MARKER_IN_MAPS_BY_LATLONG, latitude, longitude, name);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        mapIntent.setPackage(MAPS_URL);
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
            return true;
        } else return false;

    }

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param address address on the basis of which search is performed, to find the address
     * @return true if successfully opened google maps, or false if google maps not found
     */
    public boolean showMakerInGoogleMap(Context context, String address) {
        String link = String.format(LOC_MARKER_IN_MAPS_BY_ADDRESS, address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        mapIntent.setPackage(MAPS_URL);
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
            return true;
        } else return false;

    }

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param context context of activity
     * @param format  string format of which you will send parameters
     * @param objects objects seperated by comma, on the basis of format you defined
     *                <p>
     *                example : format="geo:0,0?q=%s"
     *                objects will be, one string object which will be placed in place of <b>%s</b>
     *                result will be "geo:0,0?q=<your provided string here>"
     * @return true if successfully opened google maps, or false if google maps not found
     */
    public boolean showMakerInGoogleMap(Context context, String format, Object... objects) {
        String link = String.format(format, objects);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        mapIntent.setPackage(MAPS_URL);
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
            return true;
        } else return false;
    }

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param typeOfPlace like restruant,hotel..etc
     * @return true if successfully opened google maps, or false if google maps not found
     */
    public boolean searchPlaces(Context context, String typeOfPlace) {
        String link = String.format(SEARCH_PLACES, typeOfPlace);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        mapIntent.setPackage(MAPS_URL);
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
            return true;
        } else return false;

    }

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param typeOfPlace like restruant,hotel..etc
     * @param latitude    latitude
     * @param longitude   longitude
     * @return true if successfully opened google maps, or false if google maps not found
     */
    public boolean searchPlacesByLatLong(Context context, Double latitude, Double longitude, String typeOfPlace) {
        String link = String.format(SEARCH_PLACES_BY_LOCATION, latitude, longitude, typeOfPlace);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        mapIntent.setPackage(MAPS_URL);
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
            return true;
        } else return false;

    }

}
