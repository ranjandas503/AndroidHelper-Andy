package com.appHelperKtx

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.support.annotation.ColorInt
import android.support.design.internal.NavigationMenuView
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager

class NavigationBar {

    enum class NavigationBarTranslucent {
        PORTRAIT_LANDSCAPE,
        PORTRAIT_ONLY,
        LANDSCAPE_ONLY
    }

    /**
     * Reset navigation Bars Translucent Status
     *
     * @param context                  context of app
     * @param navigationBarTranslucent just use the **NavigationBarTranslucent.'value'**
     */
    fun resetNavigationBarTranslucent(context: Context, navigationBarTranslucent: NavigationBarTranslucent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val tabletMode = context.resources.getBoolean(R.bool.android_helpers_tablet_mode)
            val orientation = context.resources.configuration.orientation

            when (navigationBarTranslucent) {
                NavigationBar.NavigationBarTranslucent.PORTRAIT_ONLY -> if (tabletMode || orientation == Configuration.ORIENTATION_PORTRAIT) {
                    (context as AppCompatActivity).window.addFlags(
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                } else {
                    (context as AppCompatActivity).window.clearFlags(
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                    setNavigationBarColor(context, Color.BLACK)
                }
                NavigationBar.NavigationBarTranslucent.LANDSCAPE_ONLY -> if (tabletMode || orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    (context as AppCompatActivity).window.addFlags(
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                } else {
                    (context as AppCompatActivity).window.clearFlags(
                            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                    setNavigationBarColor(context, Color.BLACK)
                }
                NavigationBar.NavigationBarTranslucent.PORTRAIT_LANDSCAPE -> (context as AppCompatActivity).window.addFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                else -> (context as AppCompatActivity).window.addFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            }
        }
    }

    /**
     * Disable the translucent navigation bar
     *
     * @param context context of activity
     */
    fun disableTranslucentNavigationBar(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (context as AppCompatActivity).window.clearFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

    /**
     * Method to set Status Bar Color
     *
     * @param context context of apllication
     * @param color   Resources color id i.e., R.color.'color_name'
     *
     *
     * Make sure context is not null
     */
    fun setNavigationBarColor(context: Context, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (context as Activity).window.navigationBarColor = color
        }
    }

    /**
     * Hide nav bar scroll
     *
     * @param navigationView pass navigation view object
     */
    fun hideNavigationViewScrollBar(navigationView: NavigationView) {
        val navigationMenuView = navigationView.getChildAt(0) as NavigationMenuView
        if (navigationMenuView != null)
            navigationMenuView.isVerticalScrollBarEnabled = false
    }

    companion object {


        /**
         * provide point object which contains height of bottom navigation bar
         */
        fun getNavigationBarSize(context: Context): Point {
            val appUsableSize = Andy.WINDOW.getAppUsableScreenSize(context)
            val realScreenSize = Andy.WINDOW.getScreenSize(context)

            // navigation bar on the side
            if (appUsableSize.x < realScreenSize.x) {
                return Point(realScreenSize.x - appUsableSize.x, appUsableSize.y)
            }

            // navigation bar at the bottom
            return if (appUsableSize.y < realScreenSize.y) {
                Point(appUsableSize.x, realScreenSize.y - appUsableSize.y)
            } else Point()

            // navigation bar is not present
        }
    }
}
