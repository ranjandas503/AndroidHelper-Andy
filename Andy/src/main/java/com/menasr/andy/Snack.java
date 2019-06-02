package com.menasr.andy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

public class Snack {

    /**
     * @param currentContext context of activity
     * @param msg            pass string message to show snackbar from bottom by default
     */
    public void showSnackShort(Context currentContext, String msg) {
        Snackbar.make(((Activity) currentContext).findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * @param currentContext context of activity
     * @param id             pass string_id(<b>R.string.'string_name'</b>) message to show snackbar
     *                       from bottom by default
     */
    public void showSnackShort(Context currentContext, int id) {
        Snackbar.make(((Activity) currentContext).findViewById(android.R.id.content),
                Andy.RES.getString(id), Snackbar.LENGTH_SHORT).show();
    }

    /**
     * @param currentContext context of activity
     * @param msg            pass string message to show snackbar from bottom by default
     */
    public void showSnackLong(Context currentContext, String msg) {
        Snackbar.make(((Activity) currentContext).findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_LONG).show();
    }

    /**
     * @param currentContext context of activity
     * @param id             pass string_id(<b>R.string.'string_name'</b>) message to show snackbar
     *                       from bottom by default
     */
    public void showSnackLong(Context currentContext, int id) {
        Snackbar.make(((Activity) currentContext).findViewById(android.R.id.content),
                Andy.RES.getString(id), Snackbar.LENGTH_LONG).show();
    }

    /**
     * Method to change snackbar font
     *
     * @param snackbar snackbar object
     * @param style    Typeface.BOLD or Typeface.ITALIC or any another
     * @param typeface custom typeface
     */
    public void changeSnakBarFont(Snackbar snackbar, int style, Typeface typeface) {
        TextView tv = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        tv.setTypeface(typeface, style);
    }
}
