package com.menasr.andyktx

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.design.widget.TabLayout
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.view.View
import android.widget.LinearLayout
import java.security.SignatureException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class Layout {

    /**
     * set recyclerview's Grid scrolling property to Horizontal
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     * @param itemsPerRow  send no. of items in rows you want
     */
    fun setLayoutGridHorizontal(recyclerView: RecyclerView, itemsPerRow: Int) {
        val gridLayoutManager = GridLayoutManager(recyclerView.context, itemsPerRow)
        // set Horizontal Orientation
        gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        // set LayoutManager to RecyclerView
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    /**
     * set recyclerview's default Horizontal scrolling property
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     */
    fun setLayoutListHorizontal(recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(recyclerView.context)
        // set Horizontal Orientation
        linearLayoutManager.orientation = LinearLayout.HORIZONTAL
        // set LayoutManager to RecyclerView
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

    }

    /**
     * set recyclerview's default Grid scrolling property
     * it will only set **layoutmanager** and **ItemAnimator** to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     * @param itemsPerRow  send no. of items in rows you want
     */
    fun setLayoutGridVertical(recyclerView: RecyclerView, itemsPerRow: Int) {
        val gridLayoutManager = GridLayoutManager(recyclerView.context, itemsPerRow)
        recyclerView.layoutManager = gridLayoutManager // set LayoutManager to RecyclerView
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    /**
     * set recyclerview's default vertial scrolling property
     * it will only set **layoutmanager** and **ItemAnimator** to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     */
    fun setLayoutListVertical(recyclerView: RecyclerView) {
        val linearLayoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = linearLayoutManager // set LayoutManager to RecyclerView
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    /**
     * Get base context from a view
     *
     * @param view view from which baseContext is required
     * @return context object
     */
    fun getBaseContext(view: View): Context {
        var context = view.context
        if (context is ContextThemeWrapper) {
            context = (view.context as ContextThemeWrapper).baseContext
        }
        return context
    }

    /**
     * Method which provides divider between Tab items
     *
     * @param tabLayout          just pass your tab layout
     * @param color              int color of line
     * @param width              width of margin
     * @param height             height of margin
     * @param paddingFromDivider padding from divider, it will applied to both side of margin
     */
    fun addMarginInTabLayout(
        tabLayout: TabLayout, color: Int, width: Int, height: Int,
        paddingFromDivider: Int
    ) {
        val linearLayout = tabLayout.getChildAt(0) as LinearLayout
        linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        val drawable = GradientDrawable();
        drawable.setColor(color)
        drawable.setSize(width, height)
        linearLayout.dividerPadding = paddingFromDivider
        linearLayout.dividerDrawable = drawable
    }
}