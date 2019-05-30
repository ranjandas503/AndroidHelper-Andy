package com.menasr.org;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class NavigationBar {

    public enum NavigationBarTranslucent {
        PORTRAIT_LANDSCAPE,
        PORTRAIT_ONLY,
        LANDSCAPE_ONLY
    }

    /**
     * Reset navigation Bars Translucent Status
     *
     * @param context                  context of app
     * @param navigationBarTranslucent just use the <b>NavigationBarTranslucent.'value'</b>
     */
    public void resetNavigationBarTranslucent(@NonNull Context context, @NonNull NavigationBarTranslucent navigationBarTranslucent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            boolean tabletMode = context.getResources().getBoolean(R.bool.android_helpers_tablet_mode);
            int orientation = context.getResources().getConfiguration().orientation;

            switch (navigationBarTranslucent) {
                case PORTRAIT_ONLY:
                    if (tabletMode || orientation == Configuration.ORIENTATION_PORTRAIT) {
                        ((AppCompatActivity) context).getWindow().addFlags(
                                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    } else {
                        ((AppCompatActivity) context).getWindow().clearFlags(
                                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                        setNavigationBarColor(context, Color.BLACK);
                    }
                    break;
                case LANDSCAPE_ONLY:
                    if (tabletMode || orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        ((AppCompatActivity) context).getWindow().addFlags(
                                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    } else {
                        ((AppCompatActivity) context).getWindow().clearFlags(
                                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                        setNavigationBarColor(context, Color.BLACK);
                    }
                    break;
                case PORTRAIT_LANDSCAPE:
                    ((AppCompatActivity) context).getWindow().addFlags(
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    break;
                default:
                    ((AppCompatActivity) context).getWindow().addFlags(
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    break;

            }
        }
    }

    /**
     * Disable the translucent navigation bar
     *
     * @param context context of activity
     */
    public void disableTranslucentNavigationBar(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((AppCompatActivity) context).getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * Method to set Status Bar Color
     *
     * @param context context of apllication
     * @param color   Resources color id i.e., R.color.'color_name'
     *                <p>
     *                Make sure context is not null
     */
    public void setNavigationBarColor(@NonNull Context context, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((Activity) context).getWindow().setNavigationBarColor(color);
        }
    }


    /**
     * provide point object which contains height of bottom navigation bar
     */
    public static Point getNavigationBarSize(Context context) {
        Point appUsableSize = Andy.WINDOW.getAppUsableScreenSize(context);
        Point realScreenSize = Andy.WINDOW.getScreenSize(context);

        // navigation bar on the side
        if (appUsableSize.x < realScreenSize.x) {
            return new Point(realScreenSize.x - appUsableSize.x, appUsableSize.y);
        }

        // navigation bar at the bottom
        if (appUsableSize.y < realScreenSize.y) {
            return new Point(appUsableSize.x, realScreenSize.y - appUsableSize.y);
        }

        // navigation bar is not present
        return new Point();
    }

    /**
     * Hide nav bar scroll
     *
     * @param navigationView pass navigation view object
     */
    public void hideNavigationViewScrollBar(@NonNull NavigationView navigationView) {
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null)
            navigationMenuView.setVerticalScrollBarEnabled(false);
    }
}
