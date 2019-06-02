package com.menasr.andyktx

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager

class SoftInput {

    /**
     * @param context pass context to close keyboard
     */
    fun closeKeyboard(context: Context) {
        val input = context.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = (context as Activity).currentFocus
        if (view != null) {
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * @param context pass context to open keyboard
     */
    fun openKeyboard(context: Context) {
        val input = context.getSystemService(
                Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = (context as Activity).currentFocus
        if (view != null) {
            input.toggleSoftInputFromWindow(view.windowToken, InputMethodManager.SHOW_IMPLICIT, 0)
        }
    }


    /**
     * Everytime the keyboard showed up it changed the height of the containing view
     * (adding enough padding/margin at the bottom)
     * Getting same functionality like this.
     *
     * @param activity    context of activity
     * @param contentView view on which is to be formed.
     *
     *
     *
     *
     * Also your can get **SoftInputHandler** class object from this method, you can
     * **enable()** or **disable()** view tree ovserver by that methods.
     *
     *
     * For more [visit](https://github.com/mikepenz/MaterialDrawer/issues/95#issuecomment-80519589) this.
     */
    fun setSoftInput(activity: Activity, contentView: View): SoftInputHandler {
        return SoftInputHandler(activity, contentView)
    }

    inner class SoftInputHandler(activity: Activity, private val contentView: View) {
        private var decorView: View = activity.window.decorView

        /**
         * a small helper to allow showing the editText focus
         */
        private val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            //r will be populated with the coordinates of your view that area still visible.
            decorView.getWindowVisibleDisplayFrame(r)

            //get screen height and calculate the difference with the useable area from the r
            val height = decorView.context.resources.displayMetrics.heightPixels
            val diff = height - r.bottom

            //if it could be a keyboard add the padding to the view
            if (diff != 0) {
                // if the use-able screen height differs from the total screen height we assume that it shows a keyboard now
                //check if the padding is 0 (if yes set the padding for the keyboard)
                if (contentView.paddingBottom != diff) {
                    //set the padding of the contentView for the keyboard
                    contentView.setPadding(0, 0, 0, diff)
                }
            } else {
                //check if the padding is != 0 (if yes reset the padding)
                if (contentView.paddingBottom != 0) {
                    //reset the padding of the contentView
                    contentView.setPadding(0, 0, 0, 0)
                }
            }
        }

        init {
            this.decorView = activity.window.decorView

            //only required on newer android versions. it was working on API level 19 (DeviceUtils.VERSION_CODES.KITKAT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                decorView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
            }
        }

        /**
         * Enable ViewTree observer
         */
        fun enable() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                decorView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
            }
        }

        /**
         * Disable ViewTree observer
         */
        fun disable() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                decorView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
            }
        }

    }
}
