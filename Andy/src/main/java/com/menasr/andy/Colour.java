package com.menasr.andy;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

public class Colour {

    /**
     * @param color can be ColorInt or ColorRes i.e, R.color.'color_name'
     */
    @ColorInt
    public int get(@NonNull Context context, int color) {
        try {
            return ContextCompat.getColor(context, color);
        } catch (Exception e) {
            return color;
        }
    }

    /**
     * Method to get Attribute color
     *
     * @param context context of application
     * @param attr    attributes to be send
     * @return white color context is null, else get int color
     */
    @ColorInt
    public int getAttributeColor(Context context, @AttrRes int attr) {
        if (context == null) {
            return Color.WHITE;
        }

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(attr, typedValue, true);
        return typedValue.data;
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
    public int getTitleTextColor(@ColorInt int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return (darkness < 0.35) ? getDarkerColor(color, 0.25f) : Color.WHITE;
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
    public int getBodyTextColor(@ColorInt int color) {
        int title = getTitleTextColor(color);
        return setColorAlpha(title, 0.7f);
    }

    /**
     * Method to get Darker color from provided color
     *
     * @param color        pass the int color, i.e., you can send it from color resource file.
     * @param transparency set transparency between 0.0f to 1.0f
     * @return int color
     */
    @ColorInt
    public int getDarkerColor(@ColorInt int color, @FloatRange(from = 0.0f, to = 1.0f) float transparency) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= transparency;
        return Color.HSVToColor(hsv);
    }

    /**
     * Method to get transparency from provided color
     *
     * @param color pass the int color, i.e., you can send it from color resource file.
     * @param alpha set alpha between 0.0f to 1.0f
     * @return int color
     */
    @ColorInt
    public int setColorAlpha(@ColorInt int color, @FloatRange(from = 0.0f, to = 1.0f) float alpha) {
        int alpha2 = Math.round(Color.alpha(color) * alpha);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha2, red, green, blue);
    }

    /**
     * Method to get color state list .i.e.,
     * weather it is <b>pressed_state</b> or <b>focuses_state</b>
     *
     * @param color just pass the color
     *              return ColorStateList
     */
    public ColorStateList getColorStateList(@ColorInt int color) {
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{}
        };
        int[] colors = new int[]{
                getDarkerColor(color, 0.8f),
                getDarkerColor(color, 0.8f),
                color
        };
        return new ColorStateList(states, colors);
    }

    /**
     * Method to get Checked Color State List
     *
     * @param checked   int checked color
     * @param unchecked unchecked color
     * @return ColorStateList
     */
    public ColorStateList getCheckedColorStateList(@ColorInt int unchecked, @ColorInt int checked) {
        int[][] states = new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked},
        };

        int[] colors = new int[]{
                unchecked,
                checked
        };
        return new ColorStateList(states, colors);
    }


    /**
     * Check weather it is a valid string color code or not, like "#000000"
     *
     * @param string pass the string color
     * @return true if valid color else false
     */
    public boolean isValidColor(String string) {
        try {
            Color.parseColor(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
