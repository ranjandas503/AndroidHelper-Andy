package com.menasr.andy;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.LinearLayout;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;

public class Layout {

    /**
     * Method which provides divider between Tab items
     *
     * @param tabLayout          just pass your tab layout
     * @param color              int color of line
     * @param width              width of margin
     * @param height             height of margin
     * @param paddingFromDivider padding from divider, it will applied to both side of margin
     */
    public void addMarginInTabLayout(TabLayout tabLayout, int color, int width, int height,
                                            int paddingFromDivider) {
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setSize(width, height);
        linearLayout.setDividerPadding(paddingFromDivider);
        linearLayout.setDividerDrawable(drawable);
    }

    /**
     * Get base context from a view
     *
     * @param view view from which baseContext is required
     * @return context object
     */
    @NonNull
    protected Context getBaseContext(@NonNull View view) {
        Context context = view.getContext();
        if (view.getContext() instanceof ContextThemeWrapper) {
            context = ((ContextThemeWrapper) view.getContext()).getBaseContext();
        }
        return context;
    }

    /**
     * set recyclerview's default vertial scrolling property
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     */
    public void setLayoutListVertical(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * set recyclerview's default Grid scrolling property
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     * @param itemsPerRow  send no. of items in rows you want
     */
    public void setLayoutGridVertical(RecyclerView recyclerView, int itemsPerRow) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), itemsPerRow);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * set recyclerview's default Horizontal scrolling property
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     */
    public void setLayoutListHorizontal(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * set recyclerview's Grid scrolling property to Horizontal
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     * @param itemsPerRow  send no. of items in rows you want
     */
    public void setLayoutGridHorizontal(RecyclerView recyclerView, int itemsPerRow) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), itemsPerRow);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
