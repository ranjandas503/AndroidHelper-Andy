package com.menasr.androidhelper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class ResourceLoader(val appCtx:Context) {

    /**Get message from resources
     * @param msgId like "R.sting.<int id> to get the string msg"
     * @return String of provided id*/
    fun string(msgId: Int): String? = appCtx.getString(msgId)

    /**Get formatted message from resources
     * @param msgId like "R.sting.<int id> to get the string msg"
     * @param multipleArguments multiple arguments comma seperated for inserting in String(i.e., String.format)
     * @return String combined with msg and arguments*/
    fun formattedString(msgId: Int, vararg multipleArguments: Any?): String? =
        appCtx.getString(msgId, *multipleArguments)

    /**Get color from android resources
     * @param colorId pass your color id i.e., R.color.<color id>
     * @return Int color from resource color*/
    fun color(colorId: Int): Int = ContextCompat.getColor(appCtx, colorId)

    /**Get drawable from android resources
     * @param drawableId pass your drawable id i.e., R.drawable.<drawable id>
     * @return drawble if present*/
    fun drawable(drawableId: Int): Drawable? = ContextCompat.getDrawable(appCtx, drawableId)

    /**Get drawable bitmap from android resources
     * @param drawableId pass your drawable id i.e., R.drawable.<drawable id>
     * @return Bitmap if present*/
    fun drawableBitmap(drawableId: Int): Bitmap? = BitmapFactory.decodeResource(appCtx.resources, drawableId)

    /**Get animation from android anim resource
     * @param animId pass your anim id i.e., R.anim.<animation id>
     * @return Animation if present*/
    fun anim(animId: Int): Animation? = AnimationUtils.loadAnimation(appCtx, animId)

//    fun startAnim(animId: Int,view: View)=view.startAnimation((anim(animId)))

    /**Get dimen value from dimens,
     * @param dimenId pass your dimen id i.e., R.dimen.<dimen-name>
     * @return any, you can cast it to float or int or someting else you want*/
    fun dimen(dimenId: Int): Any? = appCtx.resources.getDimension(dimenId)

}