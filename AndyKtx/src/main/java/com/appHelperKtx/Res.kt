package com.appHelperKtx

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

class Res(private val context: Context) {

    val resources: Resources
        get() = context.resources

    fun getDrawable(drawableId: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableId)
    }

    /**
     * Method to get string from string resource file
     *
     * @param stringId **R.string.'filename'**
     */
    fun getString(stringId: Int): String {
        return resources.getString(stringId)
    }

    /**
     * Method to get color id
     *
     * @param id just pass color id like **<u>R.color.colorName</u>**
     */
    fun getColorID(id: Int): Int {
        return resources.getColor(id)
    }

    /**
     * Pass format for string to get combined string
     *
     * @param format  String format
     * @param objects all objects which is required as per format
     * @return formatted string
     *
     *
     *
     * Ex : format = "Hello %s! %s"
     * objects = xyz,how are you ?
     * output = Hello xyz,how are you ?
     */
    fun getFormattedString(format: String, vararg objects: Any): String {
        return String.format(format, *objects)
    }
}
