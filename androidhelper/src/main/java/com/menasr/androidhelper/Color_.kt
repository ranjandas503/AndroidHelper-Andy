package com.menasr.androidhelper

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.util.TypedValue

class Color_ {
    /**
     * Method to get Attribute color
     *
     * @param context context of application
     * @param attr    attributes to be send
     * @return white color context is null, else get int color
     */
    @ColorInt
    fun getAttributeColor(context: Context?, @AttrRes attr: Int): Int {
        if (context == null) {
            return Color.WHITE
        }

        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }

    /**
     * Method to get Title text color for an app,
     * Just pass the color and it will calculate new color according to
     * provided color
     *
     * @param color pass the int color, i.e., you can send it from color resource file.
     * @return int color
     */
    @ColorInt
    fun getTitleTextColor(@ColorInt color: Int): Int {
        val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return if (darkness < 0.35) getDarkerColor(color, 0.25f) else Color.WHITE
    }

    /**
     * Method to get body text color for an app,
     * Just pass the color and it will calculate new color according to
     * provided color
     *
     * @param color pass the int color, i.e., you can send it from color resource file.
     * @return int color
     */
    @ColorInt
    fun getBodyTextColor(@ColorInt color: Int): Int {
        val title = getTitleTextColor(color)
        return setColorAlpha(title, 0.7f)
    }

    /**
     * Method to get Darker color from provided color
     *
     * @param color        pass the int color, i.e., you can send it from color resource file.
     * @param transparency set transparency between 0.0f to 1.0f
     * @return int color
     */
    @ColorInt
    fun getDarkerColor(@ColorInt color: Int, @FloatRange(from = 0.0, to = 1.0) transparency: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= transparency
        return Color.HSVToColor(hsv)
    }

    /**
     * Method to get transparency from provided color
     *
     * @param color pass the int color, i.e., you can send it from color resource file.
     * @param alpha set alpha between 0.0f to 1.0f
     * @return int color
     */
    @ColorInt
    fun setColorAlpha(@ColorInt color: Int, @FloatRange(from = 0.0, to = 1.0) alpha: Float): Int {
        val alpha2 = Math.round(Color.alpha(color) * alpha)
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha2, red, green, blue)
    }

    /**
     * Method to get color state list .i.e.,
     * weather it is **pressed_state** or **focuses_state**
     *
     * @param color just pass the color
     * return ColorStateList
     */
    fun getColorStateList(@ColorInt color: Int): ColorStateList {
        val states = arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf(android.R.attr.state_focused), intArrayOf())
        val colors = intArrayOf(getDarkerColor(color, 0.8f), getDarkerColor(color, 0.8f), color)
        return ColorStateList(states, colors)
    }

    /**
     * Method to get Checked Color State List
     *
     * @param checked   int checked color
     * @param unchecked unchecked color
     * @return ColorStateList
     */
    fun getCheckedColorStateList(@ColorInt unchecked: Int, @ColorInt checked: Int): ColorStateList {
        val states = arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked))

        val colors = intArrayOf(unchecked, checked)
        return ColorStateList(states, colors)
    }


    /**
     * Check weather it is a valid color string code or not, like "#000000"
     *
     * @param string pass the string color
     * @return true if valid color else false
     */
    fun isValidColor(string: String): Boolean {
        try {
            Color.parseColor(string)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}