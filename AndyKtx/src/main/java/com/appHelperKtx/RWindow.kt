package com.appHelperKtx

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.Display
import android.view.View
import android.view.WindowManager

class RWindow {

    /**
     * Method to get useable screen size
     *
     * @param context context of activity
     */
    fun getAppUsableScreenSize(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

    /**
     * Method to get screen size
     *
     * @param context context of activity
     */
    fun getScreenSize(context: Context): Point {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealSize(size)
        } else {
            try {
                size.x = Display::class.java.getMethod("getRawWidth").invoke(display) as Int
                size.y = Display::class.java.getMethod("getRawHeight").invoke(display) as Int
            } catch (e: Exception) {
                size.x = display.width
                size.y = display.height
            }

        }
        return size
    }

    /**
     * Set color of window when app is in background(when you select several apps which are opened)
     * You can set the background color.
     * You can check it by pressing more buttons right to android home button
     * **This method is only for pre-lollipop devices**
     *
     * @param context  context of activity
     * @param appName  name of app, or you can show some other name also
     * @param drawable int drawable
     * @param color    int color code
     */
    fun setupApplicationWindowColorPreLollipop(context: Context, appName: String, drawable: Drawable?, @ColorInt color: Int) {
        var bitmap: Bitmap? = null
        if (drawable != null) bitmap = Andy.IMAGE.toBitmap(drawable)
        setupApplicationWindowColor(context, appName, bitmap, color)
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
    fun setupApplicationWindowColor(context: Context, appName: String, @DrawableRes resId: Int, @ColorInt color: Int) {
        val drawable = Andy.IMAGE.getDrawable(context, resId)
        val bitmap = Andy.IMAGE.toBitmap(drawable)
        setupApplicationWindowColor(context, appName, bitmap, color)
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
    fun setupApplicationWindowColor(context: Context, appName: String, bitmap: Bitmap?, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            (context as AppCompatActivity).setTaskDescription(ActivityManager.TaskDescription(
                    appName,
                    bitmap,
                    color))
        }
    }

    /**
     * Method if you want full screen all removed
     * this method requires JELLY_BEAN
     *
     * @param context just pass the context
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    fun makeFullScreen(context: Context) {
        try {
            (context as Activity).window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * method to enable or disable touch while performing operations
     *
     * @param currentContext  current context of activity
     * @param enableOrDisable **true** or **false** to **enable** or **disable** touch.
     */
    fun enableDisableTouch(currentContext: Context, enableOrDisable: Boolean) {
        if (enableOrDisable) {      //enable touch
            (currentContext as Activity).window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {                    //disable touch
            (currentContext as Activity).window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
}
