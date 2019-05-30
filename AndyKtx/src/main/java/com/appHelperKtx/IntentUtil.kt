package com.appHelperKtx

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

class IntentUtil {

    /**
     * Throw url
     *
     * @param url in String format
     */
    fun GetOpenURLIntent(url: String): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        return intent
    }

    /**
     * Open a url
     *
     * @param context                  context of acitiy
     * @param url                      url to open
     * @param selectionTitle           title which is shown selecting options
     * @param exceptionMessageIfOccurs if exception occurs, this will be shown as toast,
     * You can send **null** if you don't want to show any toast
     */
    fun openURL(context: Context, url: String, selectionTitle: String,
                exceptionMessageIfOccurs: String?) {
        val intent = GetOpenURLIntent(url)
        try {
            val chooser = Intent.createChooser(intent, selectionTitle)
            context.startActivity(chooser)
        } catch (ex: ActivityNotFoundException) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs)
        }

    }


    /**
     * get Call phone intent
     *
     * @param number number to call
     */
    fun getCallPhoneIntent(number: String): Intent {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$number")
        return callIntent
    }

    /**
     * Call a phone number
     *
     * @param context                  context of activity
     * @param number                   number in which call to make(use country in pref like 0..etc)
     * @param selectionTitle           title which is shown while navigating to caller screen
     * @param exceptionMessageIfOccurs msg if exception occurs,
     * pass **null** if you don't want to show the toast msg
     */
    fun callPhone(context: Context, number: String, selectionTitle: String,
                  exceptionMessageIfOccurs: String?) {
        val intent = getCallPhoneIntent(number)

        try {
            val chooser = Intent.createChooser(intent, selectionTitle)
            context.startActivity(chooser)
        } catch (ex: ActivityNotFoundException) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs)
        }

    }

    /**
     * Get Send Intent Sms
     *
     * @param number In which sms is to be send
     * @param body   msg body to send
     * @return intent
     */
    fun getSendSMSIntent(number: String, body: String): Intent {
        val callIntent = Intent(Intent.ACTION_SENDTO)
        callIntent.data = Uri.parse("sms:$number")
        callIntent.putExtra("sms_body", body)
        return callIntent
    }

    /**
     * Send Sms
     *
     * @param context                  context of acitivity
     * @param number                   number in which sms is to be send
     * @param body                     message body
     * @param selectionTitle           tile which is set while selecting
     * @param exceptionMessageIfOccurs msg if exception occurs,
     * pass **null** if you don't want to show the toast msg
     */
    fun sendSMS(context: Context, number: String, body: String, selectionTitle: String, exceptionMessageIfOccurs: String?) {
        val intent = getSendSMSIntent(number, body)

        try {
            val chooser = Intent.createChooser(intent, selectionTitle)
            context.startActivity(chooser)
        } catch (ex: ActivityNotFoundException) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs)
        }

    }


    /**
     * Get Gmail Intent
     *
     * @param address email address in which mail is to be send
     * @param subject subject of email
     * @param content body of email
     * @return Intent object
     */
    fun getEmailIntent(address: String, subject: String, content: CharSequence): Intent {
        val mailIntent = Intent(Intent.ACTION_SEND)
        mailIntent.type = "message/rfc822"
        mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mailIntent.putExtra(Intent.EXTRA_TEXT, content)
        return mailIntent
    }

    /**
     * Send email
     *
     * @param content                  context of acitivity
     * @param address                  email address
     * @param subject                  email subject
     * @param content                  body of email
     * @param selectionTitle           selection tile which is shown on the time of selection msg
     * @param exceptionMessageIfOccurs msg if exception occurs,
     * pass **null** if you don't want to show the toast msg
     */
    fun SendEmail(context: Context, address: String, subject: String, content: CharSequence,
                  selectionTitle: String, exceptionMessageIfOccurs: String?) {
        val mailIntent = getEmailIntent(address, subject, content)

        try {
            val chooser = Intent.createChooser(mailIntent, selectionTitle)
            context.startActivity(chooser)
        } catch (ex: ActivityNotFoundException) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs)
        }

    }


    /**
     * Get Map Coordinates
     *
     * @param latitude
     * @param longitude
     * @param markerTitle title/name of route or name
     * @return Intent
     */
    fun getMapCoordinatesIntent(latitude: Double, longitude: Double, markerTitle: String?): Intent {
        var uri = "geo:$latitude,$longitude?q=$latitude,$longitude"
        if (markerTitle != null && markerTitle.length > 0) {
            uri += "($markerTitle)"
        }
        return Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    }

    /**
     * Get Map Coordinates
     *
     * @param latitude
     * @param longitude
     * @param markerTitle              title/name of route or name
     * @param selectionTitle           title while choosing from multiple maps
     * @param exceptionMessageIfOccurs msg if exception occurs,
     * pass **null** if you don't want to show the toast msg
     */
    fun ShowMapCoordinates(context: Context, latitude: Double, longitude: Double,
                           markerTitle: String, selectionTitle: String, exceptionMessageIfOccurs: String?) {
        val intent = getMapCoordinatesIntent(latitude, longitude, markerTitle)
        try {
            val chooser = Intent.createChooser(intent, selectionTitle)
            context.startActivity(chooser)
        } catch (ex: ActivityNotFoundException) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs)
        }

    }

    /**
     * Get Map Coordinates
     *
     * @param latitudeFrom
     * @param longitudeFrom
     * @param latitudeTo
     * @param longitudeTo
     */
    fun getMapRouteIntent(latitudeFrom: Double, longitudeFrom: Double, latitudeTo: Double,
                          longitudeTo: Double): Intent {
        val uri = Uri.parse("http://maps.google.com/maps?saddr=$latitudeFrom,$longitudeFrom&daddr=$latitudeTo,$longitudeTo")
        return Intent(Intent.ACTION_VIEW, uri)
    }


    /**
     * Get Map Coordinates
     *
     * @param latitudeFrom
     * @param longitudeFrom
     * @param latitudeTo
     * @param longitudeTo
     * @param selectionTitle           title while choosing from multiple maps
     * @param exceptionMessageIfOccurs msg if exception occurs,
     * pass **null** if you don't want to show the toast msg
     */
    fun ShowMapRoute(context: Context, latitudeFrom: Double, longitudeFrom: Double, latitudeTo: Double,
                     longitudeTo: Double, selectionTitle: String,
                     exceptionMessageIfOccurs: String?) {
        val intent = getMapRouteIntent(latitudeFrom, longitudeFrom, latitudeTo, longitudeTo)
        try {
            val chooser = Intent.createChooser(intent, selectionTitle)
            context.startActivity(chooser)
        } catch (ex: ActivityNotFoundException) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs)
        }

    }

}