package com.menasr.org;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

public class Converter {

    /**
     * @param px send int pixel to convert to Dp
     * @return dp in int
     */
    public int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * @param dp send int dp to convert to pixel
     * @return px in int
     */
    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**dp to pixel
     * @param context context of activity
     * @param dp dp in float
     *
     * @return pixel in float*/
    public float toPixel(@NonNull Context context, float dp){
        return dp * context.getResources().getDisplayMetrics().density;
    }

    /**pixel to dp
     * @param context context of activity
     * @param px dp in float
     *
     * @return dp in float*/
    public float toDp(@NonNull Context context, float px){
        return px / context.getResources().getDisplayMetrics().density;
    }

}
