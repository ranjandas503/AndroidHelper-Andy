package com.menasr.andyktx

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.view.animation.Animation
import android.view.animation.AnimationUtils

class Res(private val appCtx: Context) {

    val resources: Resources
        get() = appCtx.resources

    fun getDrawable(drawableId: Int): Drawable? = ContextCompat.getDrawable(appCtx, drawableId)

    /**
     * Method to get string from string resource file
     *
     * @param stringId **R.string.'filename'**
     */
    fun getString(stringId: Int): String =resources.getString(stringId)

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
    fun getFormattedString(format: String, vararg objects: Any): String = String.format(format, *objects)

    /**
     * Pass format for string to get combined string
     *
     * @param stringId  String msg id
     * @param objects all objects which is required as per format
     * @return formatted string
     *
     *
     *
     * Ex : format = "Hello %s! %s"
     * objects = xyz,how are you ?
     * output = Hello xyz,how are you ?
     */
    fun getFormattedString(stringId: Int, vararg objects: Any): String = String.format(getString(stringId), *objects)

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
    fun drawableBitmap(drawableId: Int): Bitmap? = BitmapFactory.decodeResource(resources, drawableId)

    /**Get animation from android anim resource
     * @param animId pass your anim id i.e., R.anim.<animation id>
     * @return AndyAnimation if present*/
    fun anim(animId: Int): Animation? = AnimationUtils.loadAnimation(appCtx, animId)


    /**Get dimen value from dimens,
     * @param dimenId pass your dimen id i.e., R.dimen.<dimen-name>
     * @return any, you can cast it to float or int or someting else you want*/
    fun dimen(dimenId: Int): Any? = appCtx.resources.getDimension(dimenId)
}
