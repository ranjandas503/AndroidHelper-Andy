package com.menasr.andy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.INTERNET;

public class Internet {

    private static boolean IS_IN_ROAMING;

    /**
     * @return Connectivity Manager Object
     */
    public ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * Get Active network info
     *
     * @return Network Info Object
     * <p>
     * Must hold
     * {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />},
     * {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     * return null in case of failure
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
    public NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManager(context);
        if (connectivityManager == null)
            return null;

        return connectivityManager.getActiveNetworkInfo();
    }

    /**
     * This method checks weather Internet is present or not
     * Weather it is WIFI or Mobile Internet
     * <b>Make sure you have provided Internet permission in manifest</b>
     *
     * @return bo true(if network is present)
     * and false(if network is not present)
     */
    public boolean isInternetIsConnected(Context context) {
        try {
            ConnectivityManager cm = getConnectivityManager(context);
            assert cm != null;
            @SuppressLint("MissingPermission") NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null) { // connected to the internet
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    // connected to wifi
                    //                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                    return true;

                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    // connected to the mobile provider's data plan
                    //                Toast.makeText(context, activeNetwork.getTypeName(), Toast.LENGTH_SHORT).show();
                    return true;
                }
            } else {
                // not connected to the internet
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method to get Network type
     * i.e., either it is mobile newtork or wifi
     * <b>Make sure you have provided Internet permission in manifest</b>
     *
     * @return "wifi" if wifi is using
     **/
    public String getNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        @SuppressLint("MissingPermission") NetworkInfo info = cm.getActiveNetworkInfo();
        return info.getTypeName();
    }

    /**
     * Whether there is an active network connection and it is not metered, e.g. so large amounts of
     * data may be transmitted.
     */
    @SuppressLint("MissingPermission")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean isUnmeteredNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = getConnectivityManager(context);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected()
                && !connectivityManager.isActiveNetworkMetered();
    }

    /**
     * Whether there is an active network connection and it is via WiFi.
     * <p>
     * <p>If you want to check whether to transmit large amounts of data, you may want to use {@link
     * #isUnmeteredNetworkConnected(Context)}.
     */
    @SuppressLint("MissingPermission")
    @RequiresPermission(allOf = {ACCESS_WIFI_STATE, INTERNET})
    public boolean isWifiConnected(Context context) {
        NetworkInfo activeNetwork = getConnectivityManager(context).getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected()
                && activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }

    private boolean checkForRoaming(Context context) {
        final TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        PhoneStateListener phoneStateListener = new PhoneStateListener() {
            @Override
            public void onServiceStateChanged(ServiceState serviceState) {
                super.onServiceStateChanged(serviceState);
                // In Roaming
                // Not in Roaming
                assert telephonyManager != null;
                IS_IN_ROAMING = telephonyManager.isNetworkRoaming();
                // You can also check roaming state using this
                // In Roaming
                // Not in Roaming
                IS_IN_ROAMING = serviceState.getRoaming();
            }
        };

        return IS_IN_ROAMING;
    }
}
