package com.menasr.andyktx

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager

class StatusBar {

    /**
     * Method to set Status Bar Color
     *
     * @param context     context of apllication
     * @param color       Resources color id i.e., R.color.'color_name'
     * @param transparent if you want transparency
     *
     *
     *
     *
     * Make sure context is not null
     */
    @JvmOverloads
    fun setStatusBarColor(context: Context, @ColorInt color: Int, transparent: Boolean = false) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (transparent)
                (context as Activity).window.addFlags(
                        WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            (context as Activity).window.statusBarColor = color
        }
    }

    /**
     * Method to check if status bar is light color or dark
     *
     * @param color just pass the int color to check
     * @return true if color is light or false if dark.
     */
    fun isStatusLightColor(@ColorInt color: Int): Boolean {
        val title = Andy.COLOR.getTitleTextColor(color)
        return title != Color.WHITE
    }

    /**
     * Method to set Status bar bar icon color according to context of your activity form
     * attributes
     *
     * @param context just pass the context of your activity
     */
    fun setupStatusBarIconColor(context: Context?) {
        if (context == null) {
            Andy.LOG.printInAllLogFormat("Color", "setupStatusBarIconColor() context is null")
            return
        }
        val color = Andy.COLOR.getAttributeColor(context, R.attr.colorPrimaryDark)
        setupStatusBarIconColor(context, isStatusLightColor(color))
    }

    /**
     * Method to setup Status Bar Incon Color
     *
     * @param context        context of Activity
     * @param isLightToolbar isToolbar is Light Colored
     */
    fun setupStatusBarIconColor(context: Context?, isLightToolbar: Boolean) {
        if (context == null) {
            Andy.LOG.printInAllLogFormat("Color", "setupStatusBarIconColor() context is null")
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((context as AppCompatActivity).window == null) {
                Andy.LOG.printInAllLogFormat("Color", "setupStatusBarIconColor() getWindow() returns null")
                return
            }

            val view = context.window.decorView
            if (view != null) {
                if (isLightToolbar) {
                    view.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    return
                }

                view.systemUiVisibility = 0
            }
        }
    }

    /**
     * Set Translucent Status Bar
     *
     * @param context     context of app
     * @param translucent pass true or false for translucent status
     * **Context should not be null**
     */
    fun setTranslucentStatusBar(context: Context?, translucent: Boolean) {
        if (context == null) {
            Andy.LOG.printInAllLogFormat("AndyWindow", "context is null")
            return
        }

        if (context !is Activity) {
            Andy.LOG.printInAllLogFormat("WindowHelper", "context must be instance of activity")
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = context.window
            if (translucent) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                return
            }

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * Get Height of Status bar
     *
     * @param context content of acitivity
     */
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else 0
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
