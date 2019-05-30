package com.menasr.org;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewAnimationUtils;

public class RAnim {

    /**
     * Creates a material style circular reveal entrance animation similar to the one in Google Play.
     * @param view your view.
     */
    @TargetApi(21)
    public void circularRevealEnter(View view) {
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    /**
     * Creates a material style circular reveal exit animation similar to the one in Google Play.
     * @param view your view.
     */
    @TargetApi(21)
    public void circularRevealExit(final View view) {
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        float initialRadius = (float) Math.hypot(cx, cy);

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

        anim.start();
    }
}
