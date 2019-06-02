package com.menasr.andy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AndyResources {
    private Context appCtx;

    public AndyResources(Context context) {
        this.appCtx = context;
    }

    public Resources getResources() {
        return appCtx.getResources();
    }

    public Drawable getDrawable(int drawableId) {
        return ContextCompat.getDrawable(appCtx, drawableId);
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
    public String getFormattedString(int stringId, Object... objects){
        return (String.format(getString(stringId), objects));
    }


    /**Get color from android resources
     * @param colorId pass your color id i.e., R.color.<color id>
     * @return Int color from resource color*/
    public int color(int colorId){
        return ContextCompat.getColor(appCtx, colorId);
    }

    /**Get drawable from android resources
     * @param drawableId pass your drawable id i.e., R.drawable.<drawable id>
     * @return drawble if present*/
    public Drawable drawable(int drawableId){
        return ContextCompat.getDrawable(appCtx, drawableId);
    }

    /**Get drawable bitmap from android resources
     * @param drawableId pass your drawable id i.e., R.drawable.<drawable id>
     * @return Bitmap if present*/
    public Bitmap drawableBitmap(int drawableId){
        return BitmapFactory.decodeResource(getResources(), drawableId);
    }

    /**Get animation from android anim resource
     * @param animId pass your anim id i.e., R.anim.<animation id>
     * @return Animation if present*/
    public Animation anim(int animId){
        return AnimationUtils.loadAnimation(appCtx, animId);
    }


    /**Get dimen value from dimens,
     * @param dimenId pass your dimen id i.e., R.dimen.<dimen-name>
     * @return any, you can cast it to float or int or someting else you want
     * Make sure you cast you object when you get the result from this metod*/
    public Object dimen(int dimenId) {
        return getResources().getDimension(dimenId);
    }
}
