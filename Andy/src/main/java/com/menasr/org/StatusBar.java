package com.menasr.org;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StatusBar {

    /**
     * Method to set Status Bar Color
     *
     * @param context context of apllication
     * @param color   Resources color id i.e., R.color.'color_name'
     *                <p>
     *                Make sure context is not null
     */
    public void setStatusBarColor(@NonNull Context context, @ColorInt int color) {
        setStatusBarColor(context, color, false);
    }

    /**
     * Method to set Status Bar Color
     *
     * @param context     context of apllication
     * @param color       Resources color id i.e., R.color.'color_name'
     * @param transparent if you want transparency
     *                    <p>
     *                    <p>
     *                    Make sure context is not null
     */
    public void setStatusBarColor(@NonNull Context context, @ColorInt int color, boolean transparent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (transparent) ((Activity) context).getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            ((Activity) context).getWindow().setStatusBarColor(color);
        }
    }

    /**
     * Method to check if status bar is light color or dark
     *
     * @param color just pass the int color to check
     * @return true if color is light or false if dark.
     */
    public boolean isStatusLightColor(@ColorInt int color) {
        int title = Andy.COLOR.getTitleTextColor(color);
        return title != Color.WHITE;
    }

    /**
     * Method to set Status bar bar icon color according to context of your activity form
     * attributes
     *
     * @param context just pass the context of your activity
     */
    public void setupStatusBarIconColor(Context context) {
        if (context == null) {
            Andy.LOG.printInAllLogFormat("Color", "setupStatusBarIconColor() context is null");
            return;
        }
        int color = Andy.COLOR.getAttributeColor(context, R.attr.colorPrimaryDark);
        setupStatusBarIconColor(context, isStatusLightColor(color));
    }

    /**
     * Method to setup Status Bar Incon Color
     *
     * @param context        context of Activity
     * @param isLightToolbar isToolbar is Light Colored
     */
    public void setupStatusBarIconColor(Context context, boolean isLightToolbar) {
        if (context == null) {
            Andy.LOG.printInAllLogFormat("Color", "setupStatusBarIconColor() context is null");
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (((AppCompatActivity) context).getWindow() == null) {
                Andy.LOG.printInAllLogFormat("Color", "setupStatusBarIconColor() getWindow() returns null");
                return;
            }

            View view = ((AppCompatActivity) context).getWindow().getDecorView();
            if (view != null) {
                if (isLightToolbar) {
                    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    return;
                }

                view.setSystemUiVisibility(0);
            }
        }
    }

    /**
     * Set Translucent Status Bar
     *
     * @param context     context of app
     * @param translucent pass true or false for translucent status
     *                    <b>Context should not be null</b>
     */
    public void setTranslucentStatusBar(Context context, boolean translucent) {
        if (context == null) {
            Andy.LOG.printInAllLogFormat("RWindow", "context is null");
            return;
        }

        if (!(context instanceof Activity)) {
            Andy.LOG.printInAllLogFormat("WindowHelper", "context must be instance of activity");
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = ((Activity) context).getWindow();
            if (translucent) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                return;
            }

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * Get Height of Status bar
     *
     * @param context content of acitivity
     */
    public int getStatusBarHeight(@NonNull Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
