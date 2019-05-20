package com.menasr.androidhelper

import android.content.Context
import android.support.v4.content.ContextCompat

class ResourceLoader(var appCtx:Context) {

    /**Get message from resources
     * @param msgId like "R.sting.<int id> to get the string msg"*/
    fun string(msgId: Int): String = appCtx.getString(msgId)

    /**Get formatted message from resources
     * @param msgId like "R.sting.<int id> to get the string msg"
     * @param multipleArguments multiple arguments comma seperated for inserting in String(i.e., String.format)*/
    fun getFormattedString(msgId: Int, vararg multipleArguments: Any?): String =
        appCtx.getString(msgId, *multipleArguments)

    /**Get color from android resources
     * @param colorId pass your color id i.e., R.color.<color id>*/
    fun color(colorId: Int): Int = ContextCompat.getColor(appCtx, colorId)

    /**Get drawable from android resources
     * @param drawableId pass your color id i.e., R.drawable.<color id>*/
    fun drawable(drawableId: Int) = ContextCompat.getDrawable(appCtx, drawableId)


}