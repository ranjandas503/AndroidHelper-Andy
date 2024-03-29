package com.menasr.andyktx

import android.content.Context
import android.content.res.Resources

class Converter {

    /**
     * @param px send int pixel to convert to Dp
     * @return dp in int
     */
    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    /**
     * @param dp send int dp to convert to pixel
     * @return px in int
     */
    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    /**dp to pixel
     * @param context context of activity
     * @param dp dp in float
     *
     * @return pixel in float
     */
    fun toPixel(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }

    /**pixel to dp
     * @param context context of activity
     * @param px dp in float
     *
     * @return dp in float
     */
    fun toDp(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

}