package com.menasr.andy;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

public class SoftInput {

    /**
     * @param context pass context to close keyboard
     */
    public void closeKeyboard(@NonNull Context context) {
        InputMethodManager input = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View view = ((Activity) context).getCurrentFocus();
        if (view != null) {
            input.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * @param context pass context to open keyboard
     */
    public void openKeyboard(@NonNull Context context) {
        InputMethodManager input = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View view = ((Activity) context).getCurrentFocus();
        if (view != null) {
            input.toggleSoftInputFromWindow(view.getWindowToken(), InputMethodManager.SHOW_IMPLICIT, 0);
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
     *                    <p>
     *                    Also your can get <b>SoftInputHandler</b> class object from this method, you can
     *                    <b>enable()</b> or <b>disable()</b> view tree ovserver by that methods.
     *                    <p>
     *                    For more <a href="https://github.com/mikepenz/MaterialDrawer/issues/95#issuecomment-80519589">visit</a> this.
     */
    public SoftInputHandler setSoftInput(Activity activity, View contentView) {
        return new SoftInputHandler(activity, contentView);
    }

    public class SoftInputHandler {
        private final View decorView;
        private final View contentView;

        public SoftInputHandler(Activity activity, View contentView) {
            this.decorView = activity.getWindow().getDecorView();
            this.contentView = contentView;

            //only required on newer android versions. it was working on API level 19 (DeviceUtils.VERSION_CODES.KITKAT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
            }
        }

        /**
         * Enable ViewTree observer
         */
        public void enable() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
            }
        }

        /**
         * Disable ViewTree observer
         */
        public void disable() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                decorView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            }
        }

        /**
         * a small helper to allow showing the editText focus
         */
        private final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                //r will be populated with the coordinates of your view that area still visible.
                decorView.getWindowVisibleDisplayFrame(r);

                //get screen height and calculate the difference with the useable area from the r
                int height = decorView.getContext().getResources().getDisplayMetrics().heightPixels;
                int diff = height - r.bottom;

                //if it could be a keyboard add the padding to the view
                if (diff != 0) {
                    // if the use-able screen height differs from the total screen height we assume that it shows a keyboard now
                    //check if the padding is 0 (if yes set the padding for the keyboard)
                    if (contentView.getPaddingBottom() != diff) {
                        //set the padding of the contentView for the keyboard
                        contentView.setPadding(0, 0, 0, diff);
                    }
                } else {
                    //check if the padding is != 0 (if yes reset the padding)
                    if (contentView.getPaddingBottom() != 0) {
                        //reset the padding of the contentView
                        contentView.setPadding(0, 0, 0, 0);
                    }
                }
            }
        };

    }
}
