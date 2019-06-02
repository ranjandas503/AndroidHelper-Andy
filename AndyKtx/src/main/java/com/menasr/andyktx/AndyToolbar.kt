package com.menasr.andyktx

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class AndyToolbar {

    /**
     * Setup toolbar for activity that have both translucent status and navigation bar
     *
     * @param toolbar toolbar object
     */
    fun setupToolbar(toolbar: Toolbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val context = Andy.getBaseContext(toolbar)
            val statusBarSize = Andy.STATUS_BAR.getStatusBarHeight(context)

            if (toolbar.layoutParams is AppBarLayout.LayoutParams) {
                val params = toolbar.layoutParams as AppBarLayout.LayoutParams
                params.setMargins(
                        params.leftMargin,
                        params.topMargin + statusBarSize,
                        params.rightMargin,
                        params.bottomMargin)
                return
            }

            toolbar.setPadding(
                    toolbar.paddingLeft,
                    toolbar.paddingTop + statusBarSize,
                    toolbar.paddingRight,
                    toolbar.paddingBottom
            )
            toolbar.layoutParams.height = getToolbarHeight(context) + statusBarSize
        }
    }

    /**
     * Get toolbar height
     *
     * @param context context of activity
     * @return int size of toolbar
     */
    fun getToolbarHeight(context: Context): Int {
        val typedValue = TypedValue()
        val actionBarSize = intArrayOf(R.attr.actionBarSize)
        val indexOfAttrTextSize = 0
        val a = context.obtainStyledAttributes(typedValue.data, actionBarSize)
        val size = a.getDimensionPixelSize(indexOfAttrTextSize, -1)
        a.recycle()
        return size
    }

    /**
     * Reset the no. of items in grid in recyclerview
     *
     * @param recyclerView recyclerview object
     * @param spanCount    no. of objects to be used for spanning
     */
    fun resetSpanCount(recyclerView: RecyclerView, spanCount: Int) {
        try {
            if (recyclerView.layoutManager is GridLayoutManager) {
                val manager = recyclerView.layoutManager as GridLayoutManager?
                manager!!.spanCount = spanCount
                manager.requestLayout()
            } else if (recyclerView.layoutManager is StaggeredGridLayoutManager) {
                val manager = recyclerView.layoutManager as StaggeredGridLayoutManager?
                manager!!.spanCount = spanCount
                manager.requestLayout()
            }
        } catch (ignored: Exception) {
        }

    }

    /**
     * Set Search view text color
     *
     * @param view      searchview object or any view object which you are using for search
     * @param textColor int color id to be used as color
     */
    fun setSearchViewTextColor(view: View?, @ColorInt textColor: Int) {
        if (view != null) {
            val hintColor = Andy.COLOR.setColorAlpha(textColor, 0.5f)
            if (view is TextView) {
                view.setTextColor(textColor)
                view.setHintTextColor(hintColor)
            } else if (view is ViewGroup) {
                val viewGroup = view as ViewGroup?
                for (i in 0 until viewGroup!!.childCount) {
                    setSearchViewTextColor(viewGroup.getChildAt(i), textColor)
                }
            }
        }
    }

    /**
     * Set the color of SearchView background
     *
     * @param view  view
     * @param color color id resources
     */
    fun setSearchViewBackgroundColor(view: View?, color: Int) {
        if (view != null) {
            val background = view.findViewById<View>(android.support.v7.appcompat.R.id.search_plate)
            if (background != null) {
                val context = Andy.getBaseContext(view)
                background.setBackgroundColor(Andy.COLOR.get(context, color))
            }
        }
    }

    /**
     * Set icon in Serachview
     *
     * @param view  view object
     * @param resId Drawable resource id for drawaing
     */
    fun setSearchViewSearchIcon(view: View?, @DrawableRes resId: Int) {
        if (view == null) return

        val context = Andy.getBaseContext(view)
        val drawable = Andy.IMAGE.getDrawable(context, resId)
        setSearchViewSearchIcon(view, drawable)
    }

    /**
     * Set icon in Serachview
     *
     * @param view     view object
     * @param drawable Drawable object
     */
    fun setSearchViewSearchIcon(view: View?, drawable: Drawable?) {
        if (view == null) return

        val searchIcon = view.findViewById<ImageView>(
                android.support.v7.appcompat.R.id.search_mag_icon) ?: return

        if (drawable == null) {
            val viewGroup = searchIcon.parent as ViewGroup ?: return

            viewGroup.removeView(searchIcon)
            viewGroup.addView(searchIcon)

            searchIcon.adjustViewBounds = true
            searchIcon.maxWidth = 0
            searchIcon.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
        }

        searchIcon.setImageDrawable(drawable)
    }

    /**
     * Set icon in Serachview when it is closing
     *
     * @param view  view object
     * @param resId Drawable resource id for drawaing
     */
    fun setSearchViewCloseIcon(view: View?, @DrawableRes resId: Int) {
        if (view == null) return

        val context = Andy.getBaseContext(view)
        val drawable = Andy.IMAGE.get(context, resId)
        setSearchViewCloseIcon(view, drawable)
    }

    /**
     * Set icon in Serachview
     *
     * @param view     view object
     * @param drawable Drawable object
     */
    fun setSearchViewCloseIcon(view: View?, drawable: Drawable?) {
        if (view == null) return

        val closeIcon = view.findViewById<ImageView>(
                android.support.v7.appcompat.R.id.search_close_btn)
        if (closeIcon != null) {
            if (drawable == null) {
                val viewGroup = closeIcon.parent as ViewGroup ?: return

                viewGroup.removeView(closeIcon)
                viewGroup.addView(closeIcon)

                closeIcon.adjustViewBounds = true
                closeIcon.maxWidth = 0
                closeIcon.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
            }

            closeIcon.setImageDrawable(drawable)
        }
    }
}
