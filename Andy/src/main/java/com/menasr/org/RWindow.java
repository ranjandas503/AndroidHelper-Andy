package com.menasr.org;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class RWindow {

    /**
     * Method to get useable screen size
     *
     * @param context context of activity
     */
    public Point getAppUsableScreenSize(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Method to get screen size
     *
     * @param context context of activity
     */
    public Point getScreenSize(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealSize(size);
        } else {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception e) {
                size.x = display.getWidth();
                size.y = display.getHeight();
            }
        }
        return size;
    }

    /**
     * Set color of window when app is in background(when you select several apps which are opened)
     * You can set the background color.
     * You can check it by pressing more buttons right to android home button
     * <b>This method is only for pre-lollipop devices</b>
     *
     * @param context  context of activity
     * @param appName  name of app, or you can show some other name also
     * @param drawable int drawable
     * @param color    int color code
     */
    public void setupApplicationWindowColorPreLollipop(@NonNull Context context, String appName, @Nullable Drawable drawable, @ColorInt int color) {
        Bitmap bitmap = null;
        if (drawable != null) bitmap = Andy.IMAGE.toBitmap(drawable);
        setupApplicationWindowColor(context, appName, bitmap, color);
    }

    /**
     * Set color of window when app is in background(when you select several apps which are opened)
     * You can set the background color.
     * You can check it by pressing more buttons right to android home button
     *
     * @param context context of activity
     * @param appName name of app, or you can show some other name also
     * @param resId   int drawable resource
     * @param color   int color code
     */
    public void setupApplicationWindowColor(@NonNull Context context, String appName, @DrawableRes int resId, @ColorInt int color) {
        Drawable drawable = Andy.IMAGE.getDrawable(context, resId);
        Bitmap bitmap = Andy.IMAGE.toBitmap(drawable);
        setupApplicationWindowColor(context, appName, bitmap, color);
    }

    /**
     * Set color of window when app is in background(when you select several apps which are opened)
     * You can set the background color.
     * You can check it by pressing more buttons right to android home button
     *
     * @param context context of activity
     * @param appName name of app, or you can show some other name also
     * @param bitmap  bitmap image
     * @param color   int color code
     */
    public void setupApplicationWindowColor(@NonNull Context context, String appName, @Nullable Bitmap bitmap, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((AppCompatActivity) context).setTaskDescription(new ActivityManager.TaskDescription(
                    appName,
                    bitmap,
                    color));
        }
    }

    /**
     * Method if you want full screen all removed
     * this method requires JELLY_BEAN
     *
     * @param context just pass the context
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void makeFullScreen(Context context) {
        try {
            ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method to enable or disable touch while performing operations
     *
     * @param currentContext  current context of activity
     * @param enableOrDisable <b>true</b> or <b>false</b> to <b>enable</b> or <b>disable</b> touch.
     */
    public void enableDisableTouch(Context currentContext, boolean enableOrDisable) {
        if (enableOrDisable) {      //enable touch
            ((Activity) currentContext).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        } else {                    //disable touch
            ((Activity) currentContext).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
}
