package com.menasr.andyktx

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView

@Suppress("unused")
class Snack {

    /**
     * @param currentContext context of activity
     * @param msg            pass string message to show snackbar from bottom by default
     */
    fun showSnackShort(currentContext: Context, msg: String) {
        Snackbar.make((currentContext as Activity).findViewById<View>(android.R.id.content),
                msg, Snackbar.LENGTH_SHORT).show()
    }

    /**
     * @param currentContext context of activity
     * @param id             pass string_id(**R.string.'string_name'**) message to show snackbar
     * from bottom by default
     */
    fun showSnackShort(currentContext: Context, id: Int) {
        Snackbar.make((currentContext as Activity).findViewById<View>(android.R.id.content),
                Andy.RES.getString(id), Snackbar.LENGTH_SHORT).show()
    }

    /**
     * @param currentContext context of activity
     * @param msg            pass string message to show snackbar from bottom by default
     */
    fun showSnackLong(currentContext: Context, msg: String) {
        Snackbar.make((currentContext as Activity).findViewById<View>(android.R.id.content),
                msg, Snackbar.LENGTH_LONG).show()
    }

    /**
     * @param currentContext context of activity
     * @param id             pass string_id(**R.string.'string_name'**) message to show snackbar
     * from bottom by default
     */
    fun showSnackLong(currentContext: Context, id: Int) {
        Snackbar.make((currentContext as Activity).findViewById<View>(android.R.id.content),
                Andy.RES.getString(id), Snackbar.LENGTH_LONG).show()
    }

    /**
     * Method to change snackbar font
     *
     * @param snackbar snackbar object
     * @param style    Typeface.BOLD or Typeface.ITALIC or any another
     * @param typeface custom typeface
     */
    fun changeSnackBarFont(snackbar: Snackbar, style: Int, typeface: Typeface) {
        val tv = snackbar.view.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        tv.setTextColor(Color.WHITE)
        tv.setTypeface(typeface, style)
    }
}
