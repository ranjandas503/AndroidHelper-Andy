package com.menasr.org;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RToolbar {

    /**
     * Setup toolbar for activity that have both translucent status and navigation bar
     *
     * @param toolbar toolbar object
     */
    public void setupToolbar(@NonNull Toolbar toolbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = Andy.getBaseContext(toolbar);
            int statusBarSize = Andy.STATUS_BAR.getStatusBarHeight(context);

            if (toolbar.getLayoutParams() instanceof AppBarLayout.LayoutParams) {
                AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
                params.setMargins(
                        params.leftMargin,
                        params.topMargin + statusBarSize,
                        params.rightMargin,
                        params.bottomMargin);
                return;
            }

            toolbar.setPadding(
                    toolbar.getPaddingLeft(),
                    toolbar.getPaddingTop() + statusBarSize,
                    toolbar.getPaddingRight(),
                    toolbar.getPaddingBottom()
            );
            toolbar.getLayoutParams().height = getToolbarHeight(context) + statusBarSize;
        }
    }

    /**
     * Get toolbar height
     *
     * @param context context of activity
     * @return int size of toolbar
     */
    public int getToolbarHeight(@NonNull Context context) {
        TypedValue typedValue = new TypedValue();
        int[] actionBarSize = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = context.obtainStyledAttributes(typedValue.data, actionBarSize);
        int size = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return size;
    }

    /**
     * Reset the no. of items in grid in recyclerview
     *
     * @param recyclerView recyclerview object
     * @param spanCount    no. of objects to be used for spanning
     */
    public void resetSpanCount(@NonNull RecyclerView recyclerView, int spanCount) {
        try {
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
                manager.setSpanCount(spanCount);
                manager.requestLayout();
            } else if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                manager.setSpanCount(spanCount);
                manager.requestLayout();
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Set Search view text color
     *
     * @param view      searchview object or any view object which you are using for search
     * @param textColor int color id to be used as color
     */
    public void setSearchViewTextColor(@Nullable View view, @ColorInt int textColor) {
        if (view != null) {
            int hintColor = Andy.COLOR.setColorAlpha(textColor, 0.5f);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(textColor);
                ((TextView) view).setHintTextColor(hintColor);
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    setSearchViewTextColor(viewGroup.getChildAt(i), textColor);
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
    public void setSearchViewBackgroundColor(@Nullable View view, int color) {
        if (view != null) {
            View background = view.findViewById(android.support.v7.appcompat.R.id.search_plate);
            if (background != null) {
                Context context = Andy.getBaseContext(view);
                background.setBackgroundColor(Andy.COLOR.get(context, color));
            }
        }
    }

    /**
     * Set icon in Serachview
     *
     * @param view  view object
     * @param resId Drawable resource id for drawaing
     */
    public void setSearchViewSearchIcon(@Nullable View view, @DrawableRes int resId) {
        if (view == null) return;

        Context context = Andy.getBaseContext(view);
        Drawable drawable = Andy.IMAGE.getDrawable(context, resId);
        setSearchViewSearchIcon(view, drawable);
    }

    /**
     * Set icon in Serachview
     *
     * @param view     view object
     * @param drawable Drawable object
     */
    public void setSearchViewSearchIcon(@Nullable View view, @Nullable Drawable drawable) {
        if (view == null) return;

        ImageView searchIcon = view.findViewById(
                android.support.v7.appcompat.R.id.search_mag_icon);
        if (searchIcon == null) return;

        if (drawable == null) {
            ViewGroup viewGroup = (ViewGroup) searchIcon.getParent();
            if (viewGroup == null) return;

            viewGroup.removeView(searchIcon);
            viewGroup.addView(searchIcon);

            searchIcon.setAdjustViewBounds(true);
            searchIcon.setMaxWidth(0);
            searchIcon.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
        }

        searchIcon.setImageDrawable(drawable);
    }

    /**
     * Set icon in Serachview when it is closing
     *
     * @param view  view object
     * @param resId Drawable resource id for drawaing
     */
    public void setSearchViewCloseIcon(@Nullable View view, @DrawableRes int resId) {
        if (view == null) return;

        Context context = Andy.getBaseContext(view);
        Drawable drawable = Andy.IMAGE.get(context, resId);
        setSearchViewCloseIcon(view, drawable);
    }

    /**
     * Set icon in Serachview
     *
     * @param view     view object
     * @param drawable Drawable object
     */
    public void setSearchViewCloseIcon(@Nullable View view, @Nullable Drawable drawable) {
        if (view == null) return;

        ImageView closeIcon = view.findViewById(
                android.support.v7.appcompat.R.id.search_close_btn);
        if (closeIcon != null) {
            if (drawable == null) {
                ViewGroup viewGroup = (ViewGroup) closeIcon.getParent();
                if (viewGroup == null) return;

                viewGroup.removeView(closeIcon);
                viewGroup.addView(closeIcon);

                closeIcon.setAdjustViewBounds(true);
                closeIcon.setMaxWidth(0);
                closeIcon.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
            }

            closeIcon.setImageDrawable(drawable);
        }
    }
}
