package com.menasr.androidhelper

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.INTERNET
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.support.annotation.RequiresPermission
import android.telephony.PhoneStateListener
import android.telephony.ServiceState
import android.telephony.TelephonyManager

class Internet {
    private var IS_IN_ROAMING: Boolean = false

    /**
     * @return Connectivity Manager Object
     */
    fun getConnectivityManager(context: Context): ConnectivityManager? {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /**
     * Get Active network info
     *
     * @return Network Info Object
     *
     *
     * Must hold
     * `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />`,
     * `<uses-permission android:name="android.permission.INTERNET" />`
     * return null in case of failure
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = arrayOf(ACCESS_WIFI_STATE, INTERNET))
    fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val connectivityManager = getConnectivityManager(context) ?: return null

        return connectivityManager.activeNetworkInfo
    }

    /**
     * This method checks weather Internet is present or not
     * Weather it is WIFI or Mobile Internet
     * **Make sure you have provided Internet permission in manifest**
     *
     * @return bo true(if network is present)
     * and false(if network is not present)
     */
    fun isInternetIsConnected(context: Context): Boolean {
        try {
            val cm = getConnectivityManager(context)!!
            @SuppressLint("MissingPermission") val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    //                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                    return true

                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    //                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                    return true
                }
            } else {
                // not connected to the internet
                return false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }

    /**
     * Method to get Network type
     * i.e., either it is mobile newtork or wifi
     * **Make sure you have provided Internet permission in manifest**
     *
     * @return "wifi" if wifi is using
     */
    fun getNetworkType(context: Context): String {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        @SuppressLint("MissingPermission") val info = cm.activeNetworkInfo
        return info.typeName
    }

    /**
     * Whether there is an active network connection and it is not metered, e.g. so large amounts of
     * data may be transmitted.
     */
    @SuppressLint("MissingPermission")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun isUnmeteredNetworkConnected(context: Context): Boolean {
        val connectivityManager = getConnectivityManager(context) ?: return false
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected
                && !connectivityManager.isActiveNetworkMetered)
    }

    /**
     * Whether there is an active network connection and it is via WiFi.
     *
     *
     *
     * If you want to check whether to transmit large amounts of data, you may want to use [ ][.isUnmeteredNetworkConnected].
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = arrayOf(ACCESS_WIFI_STATE, INTERNET))
    fun isWifiConnected(context: Context): Boolean {
        val activeNetwork = getConnectivityManager(context)!!.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnected
                && activeNetwork.type == ConnectivityManager.TYPE_WIFI)
    }

    private fun checkForRoaming(context: Context): Boolean {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val phoneStateListener = object : PhoneStateListener() {
            override fun onServiceStateChanged(serviceState: ServiceState) {
                super.onServiceStateChanged(serviceState)
                // In Roaming
                // Not in Roaming
                IS_IN_ROAMING = telephonyManager.isNetworkRoaming
                // You can also check roaming state using this
                // In Roaming
                // Not in Roaming
                IS_IN_ROAMING = serviceState.roaming
            }
        }

        return IS_IN_ROAMING
    }
}