package com.appHelperKtx

import android.Manifest.permission.ACCESS_WIFI_STATE
import android.Manifest.permission.INTERNET
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.support.annotation.RequiresPermission
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.view.View
import java.io.File
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*

class DeviceUtils {

    /**
     * Returns true if the current layout direction is [View.LAYOUT_DIRECTION_RTL].
     *
     * @return
     *
     *This always returns false on versions below JELLY_BEAN_MR1.
     */
    fun isRtlLayout(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val direction = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault())
            return direction == View.LAYOUT_DIRECTION_RTL
        }
        return false
    }

    /**
     * Method to get Region based on SIM card
     * i.e., it will return country region
     *
     * @return "IN" or this type of country code
     */
    fun getRegionFromSimCard(context: Context): String {
        var country_region = ""
        val teleMgr = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (teleMgr != null) {
            country_region = teleMgr.simCountryIso
        }
        return country_region.toUpperCase()
    }

    /**
     * Method which provides boolean result for simcard
     *
     * @param context provide the context
     * @return **ture** if sim card is present in device,
     * **false** if sim card is not present in device
     */
    fun isSimPresentInDevice(context: Context): Boolean {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager  //gets the current TelephonyManager
        return tm.simState != TelephonyManager.SIM_STATE_ABSENT
    }

    /**
     * Return whether device is rooted.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isDeviceRooted(): Boolean {
        val su = "su"
        val locations = arrayOf("/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/")
        for (location in locations) {
            if (File(location + su).exists()) {
                return true
            }
        }
        return false
    }

    /**
     * Return the version name of device's system.
     *
     * @return the version name of device's system
     */
    fun getSDKVersionName(): String {
        return android.os.Build.VERSION.RELEASE
    }

    /**
     * Return version code of device's system.
     *
     * @return version code of device's system
     */
    fun getSDKVersionCode(): Int {
        return android.os.Build.VERSION.SDK_INT
    }

    /**
     * Return the MAC address.
     *
     * Must hold
     * `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />`,
     * `<uses-permission android:name="android.permission.INTERNET" />`
     *
     * @return the MAC address
     */
    @RequiresPermission(allOf = arrayOf(ACCESS_WIFI_STATE, INTERNET))
    fun getMacAddress(context: Context): String {
        return getMacAddress(context, *((null as Array<String>?)!!))
    }

    /**
     * Return the MAC address.
     *
     * Must hold
     * `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />`,
     * `<uses-permission android:name="android.permission.INTERNET" />`
     *
     * @return the MAC address
     */
    @RequiresPermission(allOf = arrayOf(ACCESS_WIFI_STATE, INTERNET))
    fun getMacAddress(context: Context, vararg excepts: String): String {
        var macAddress = getMacAddressByWifiInfo(context)
        if (isAddressNotInExcepts(macAddress, *excepts)) {
            return macAddress
        }
        macAddress = getMacAddressByNetworkInterface()
        if (isAddressNotInExcepts(macAddress, *excepts)) {
            return macAddress
        }
        macAddress = getMacAddressByInetAddress()
        if (isAddressNotInExcepts(macAddress, *excepts)) {
            return macAddress
        }
        macAddress = getMacAddressByFile()
        return if (isAddressNotInExcepts(macAddress, *excepts)) {
            macAddress
        } else ""
    }

    private fun isAddressNotInExcepts(address: String, vararg excepts: String): Boolean {
        if (excepts == null || excepts.size == 0) {
            return "02:00:00:00:00:00" != address
        }
        for (filter in excepts) {
            if (address == filter) {
                return false
            }
        }
        return true
    }

    private fun getMacAddressByNetworkInterface(): String {
        try {
            val nis = NetworkInterface.getNetworkInterfaces()
            while (nis.hasMoreElements()) {
                val ni = nis.nextElement()
                if (ni == null || !ni.name.equals("wlan0", ignoreCase = true)) continue
                val macBytes = ni.hardwareAddress
                if (macBytes != null && macBytes.size > 0) {
                    val sb = StringBuilder()
                    for (b in macBytes) {
                        sb.append(String.format("%02x:", b))
                    }
                    return sb.substring(0, sb.length - 1)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "02:00:00:00:00:00"
    }

    private fun getMacAddressByInetAddress(): String {
        try {
            val inetAddress = getInetAddress()
            if (inetAddress != null) {
                val ni = NetworkInterface.getByInetAddress(inetAddress)
                if (ni != null) {
                    val macBytes = ni.hardwareAddress
                    if (macBytes != null && macBytes.size > 0) {
                        val sb = StringBuilder()
                        for (b in macBytes) {
                            sb.append(String.format("%02x:", b))
                        }
                        return sb.substring(0, sb.length - 1)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "02:00:00:00:00:00"
    }

    private fun getInetAddress(): InetAddress? {
        try {
            val nis = NetworkInterface.getNetworkInterfaces()
            while (nis.hasMoreElements()) {
                val ni = nis.nextElement()
                // To prevent phone of xiaomi return "10.0.2.15"
                if (!ni.isUp) continue
                val addresses = ni.inetAddresses
                while (addresses.hasMoreElements()) {
                    val inetAddress = addresses.nextElement()
                    if (!inetAddress.isLoopbackAddress) {
                        val hostAddress = inetAddress.hostAddress
                        if (hostAddress.indexOf(':') < 0) return inetAddress
                    }
                }
            }
        } catch (e: SocketException) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * Return the android id of device.
     *
     * @return the android id of device
     */
    @SuppressLint("HardwareIds")
    fun getAndroidID(context: Context): String {

        val id = Settings.Secure.getString(context.contentResolver,
                Settings.Secure.ANDROID_ID)
        return id ?: ""
    }


    @SuppressLint("HardwareIds", "MissingPermission")
    private fun getMacAddressByWifiInfo(context: Context): String {
        try {
            val wifi = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            if (wifi != null) {
                val info = wifi.connectionInfo
                if (info != null) return info.macAddress
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return "02:00:00:00:00:00"
    }


    private fun getMacAddressByFile(): String {
        var result = Andy.SHELL.execCmd("getprop wifi.interface", false)
        if (result.result == 0) {
            val name = result.successMsg
            if (name != null) {
                result = Andy.SHELL.execCmd("cat /sys/class/net/$name/address", false)
                if (result.result == 0) {
                    val address = result.successMsg
                    if (address != null && address!!.length > 0) {
                        return address
                    }
                }
            }
        }
        return "02:00:00:00:00:00"
    }

    /**
     * Return the manufacturer of the product/hardware.
     *
     * e.g. Xiaomi
     *
     * @return the manufacturer of the product/hardware
     */
    fun getManufacturer(): String {
        return Build.MANUFACTURER
    }

    /**
     * Return the model of device.
     *
     * e.g. MI2SC
     *
     * @return the model of device
     */
    fun getModel(): String {
        var model: String? = Build.MODEL
        if (model != null) {
            model = model.trim { it <= ' ' }.replace("\\s*".toRegex(), "")
        } else {
            model = ""
        }
        return model
    }

    /**
     * Return an ordered list of ABIs supported by this device. The most preferred ABI is the first
     * element in the list.
     *
     * @return an ordered list of ABIs supported by this device
     */
    fun getABIs(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Build.SUPPORTED_ABIS
        } else {
            if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
                arrayOf(Build.CPU_ABI, Build.CPU_ABI2)
            } else arrayOf(Build.CPU_ABI)
        }
    }

    @SuppressLint("InlinedApi")
            /**
     * Method to navigate user to app's play page
     * Make sure you have active **Internet Connection** before proceeding
     *
     * @param context just send the context of activity
     */
    fun goToPlaystore(context: Context) {
        val uri = Uri.parse(Const.PLAYSTORELINK + context.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            context.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(Const.RETURN_LINK + context.packageName)))
        }

    }

}