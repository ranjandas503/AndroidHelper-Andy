package com.menasr.andy;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class IntentUtil {

    /**
     * Throw url
     *
     * @param url in String format
     */
    public Intent GetOpenURLIntent(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        return intent;
    }

    /**
     * Open a url
     *
     * @param context                  context of acitiy
     * @param url                      url to open
     * @param selectionTitle           title which is shown selecting options
     * @param exceptionMessageIfOccurs if exception occurs, this will be shown as toast,
     *                                 You can send <b>null</b> if you don't want to show any toast
     */
    public void openURL(Context context, String url, @NonNull String selectionTitle,
                        @Nullable String exceptionMessageIfOccurs) {
        Intent intent = GetOpenURLIntent(url);
        try {
            Intent chooser = Intent.createChooser(intent, selectionTitle);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs);
        }
    }


    /**
     * get Call phone intent
     *
     * @param number number to call
     */
    public Intent getCallPhoneIntent(String number) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + number));
        return callIntent;
    }

    /**
     * Call a phone number
     *
     * @param context                  context of activity
     * @param number                   number in which call to make(use country in pref like 0..etc)
     * @param selectionTitle           title which is shown while navigating to caller screen
     * @param exceptionMessageIfOccurs msg if exception occurs,
     *                                 pass <b>null</b> if you don't want to show the toast msg
     */
    public void callPhone(Context context, String number, @NonNull String selectionTitle,
                          @Nullable String exceptionMessageIfOccurs) {
        Intent intent = getCallPhoneIntent(number);

        try {
            Intent chooser = Intent.createChooser(intent, selectionTitle);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs);
        }
    }

    /**
     * Get Send Intent Sms
     *
     * @param number In which sms is to be send
     * @param body   msg body to send
     * @return intent
     */
    public Intent getSendSMSIntent(String number, String body) {
        Intent callIntent = new Intent(Intent.ACTION_SENDTO);
        callIntent.setData(Uri.parse("sms:" + number));
        callIntent.putExtra("sms_body", body);
        return callIntent;
    }

    /**
     * Send Sms
     *
     * @param context                  context of acitivity
     * @param number                   number in which sms is to be send
     * @param body                     message body
     * @param selectionTitle           tile which is set while selecting
     * @param exceptionMessageIfOccurs msg if exception occurs,
     *                                 pass <b>null</b> if you don't want to show the toast msg
     */
    public void sendSMS(Context context, String number, String body, @NonNull String selectionTitle, @Nullable String exceptionMessageIfOccurs) {
        Intent intent = getSendSMSIntent(number, body);

        try {
            Intent chooser = Intent.createChooser(intent, selectionTitle);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs);
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
    public Intent getEmailIntent(String address, String subject, CharSequence content) {
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("message/rfc822");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        mailIntent.putExtra(Intent.EXTRA_TEXT, content);
        return mailIntent;
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
     *                                 pass <b>null</b> if you don't want to show the toast msg
     */
    public void SendEmail(Context context, String address, String subject, CharSequence content,
                          @NonNull String selectionTitle, @Nullable String exceptionMessageIfOccurs) {
        Intent mailIntent = getEmailIntent(address, subject, content);

        try {
            Intent chooser = Intent.createChooser(mailIntent, selectionTitle);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs);
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
    public Intent getMapCoordinatesIntent(double latitude, double longitude, String markerTitle) {
        String uri = "geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude;
        if (markerTitle != null && markerTitle.length() > 0) {
            uri += "(" + markerTitle + ")";
        }
        return new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    }

    /**
     * Get Map Coordinates
     *
     * @param latitude
     * @param longitude
     * @param markerTitle              title/name of route or name
     * @param selectionTitle           title while choosing from multiple maps
     * @param exceptionMessageIfOccurs msg if exception occurs,
     *                                 pass <b>null</b> if you don't want to show the toast msg
     */
    public void ShowMapCoordinates(Context context, double latitude, double longitude,
                                   String markerTitle, @NonNull String selectionTitle, @Nullable String exceptionMessageIfOccurs) {
        Intent intent = getMapCoordinatesIntent(latitude, longitude, markerTitle);
        try {
            Intent chooser = Intent.createChooser(intent, selectionTitle);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs);
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
    public Intent getMapRouteIntent(double latitudeFrom, double longitudeFrom, double latitudeTo,
                                    double longitudeTo) {
        Uri uri = Uri.parse("http://maps.google.com/maps?saddr=" + latitudeFrom + "," + longitudeFrom + "&daddr=" + latitudeTo + "," + longitudeTo + "");
        return new Intent(Intent.ACTION_VIEW, uri);
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
     *                                 pass <b>null</b> if you don't want to show the toast msg
     */
    public void ShowMapRoute(Context context, double latitudeFrom, double longitudeFrom, double latitudeTo,
                             double longitudeTo, @NonNull String selectionTitle,
                             @Nullable String exceptionMessageIfOccurs) {
        Intent intent = getMapRouteIntent(latitudeFrom, longitudeFrom, latitudeTo, longitudeTo);
        try {
            Intent chooser = Intent.createChooser(intent, selectionTitle);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            if (exceptionMessageIfOccurs != null)
                Andy.TOAST.toastShort(exceptionMessageIfOccurs);
        }
    }
}
