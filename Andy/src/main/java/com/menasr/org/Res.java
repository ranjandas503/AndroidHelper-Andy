package com.menasr.org;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

public class Res {
    private Context context;

    public Res(Context context) {
        this.context = context;
    }

    public Resources getResources() {
        return context.getResources();
    }

    public Drawable getDrawable(int drawableId) {
        return ContextCompat.getDrawable(context, drawableId);
    }

    /**
     * Method to get string from string resource file
     *
     * @param stringId <b>R.string.'filename'</b>
     */
    public String getString(@NonNull int stringId) {
        return getResources().getString(stringId);
    }

    /**
     * Method to get color id
     *
     * @param id just pass color id like <b><u>R.color.colorName</u></b>
     */
    public int getColorID(int id) {
        return getResources().getColor(id);
    }

    /**
     * Pass format for string to get combined string
     *
     * @param format  String format
     * @param objects all objects which is required as per format
     * @return formatted string
     * <p>
     *
     * Ex : format = "Hello %s! %s"
     * objects = xyz,how are you ?
     * output = Hello xyz,how are you ?
     */
    public String getFormattedString(String format, Object... objects) {
        return (String.format(format, objects));
    }
}
