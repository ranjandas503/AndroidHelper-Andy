package com.appHelperKtx

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri

@SuppressLint("DefaultLocale")
class Maps {
    private val MAPS_URL = "com.google.android.apps.maps"
    private val LOC_MARKER_IN_MAPS_BY_LATLONG = "geo:0,0?q=%f,%f(%s)"
    private val LOC_MARKER_IN_MAPS_BY_ADDRESS = "geo:0,0?q=%s"
    private val SEARCH_PLACES = "geo:0,0?q=%s"
    private val SEARCH_PLACES_BY_LOCATION = "geo:%f,%f?q=%s"

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param name name which is shown for that marker
     * @return true if successfully opened google maps, or false if google maps not found
     */

    fun showMakerInGoogleMap(context: Context, latitude: Double?, longitude: Double?, name: String): Boolean {
        val link = String.format(LOC_MARKER_IN_MAPS_BY_LATLONG, latitude, longitude, name)
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        mapIntent.setPackage(MAPS_URL)
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
            return true
        } else
            return false

    }

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param address address on the basis of which search is performed, to find the address
     * @return true if successfully opened google maps, or false if google maps not found
     */
    fun showMakerInGoogleMap(context: Context, address: String): Boolean {
        val link = String.format(LOC_MARKER_IN_MAPS_BY_ADDRESS, address)
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        mapIntent.setPackage(MAPS_URL)
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
            return true
        } else
            return false

    }

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param context context of activity
     * @param format  string format of which you will send parameters
     * @param objects objects seperated by comma, on the basis of format you defined
     *
     *
     * example : format="geo:0,0?q=%s"
     * objects will be, one string object which will be placed in place of **%s**
     * result will be "geo:0,0?q=<your provided string here>"
     * @return true if successfully opened google maps, or false if google maps not found
    </your> */
    fun showMakerInGoogleMap(context: Context, format: String, vararg objects: Any): Boolean {
        val link = String.format(format, *objects)
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        mapIntent.setPackage(MAPS_URL)
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
            return true
        } else
            return false
    }

    /**
     * Show provided location maker in google maps
     * pass lat,long
     *
     * @param typeOfPlace like restruant,hotel..etc
     * @return true if successfully opened google maps, or false if google maps not found
     */
    fun searchPlaces(context: Context, typeOfPlace: String): Boolean {
        val link = String.format(SEARCH_PLACES, typeOfPlace)
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        mapIntent.setPackage(MAPS_URL)
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
            return true
        } else
            return false

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
    fun searchPlacesByLatLong(context: Context, latitude: Double?, longitude: Double?, typeOfPlace: String): Boolean {
        val link = String.format(SEARCH_PLACES_BY_LOCATION, latitude, longitude, typeOfPlace)
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        mapIntent.setPackage(MAPS_URL)
        if (mapIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(mapIntent)
            return true
        } else
            return false

    }

}
