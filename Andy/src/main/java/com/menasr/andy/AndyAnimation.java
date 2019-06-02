package com.menasr.andy;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;

public class AndyAnimation {

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

    /**Provide the animation and view , to start the animation,
     * @param animId anim id like R.anim.<id of anim>
     * @param view on which animation is to be performed
     * It will run in main thread*/
    public void startAnimation(int animId, View view) {
        view.startAnimation((Andy.RES.anim(animId)));
    }

    /**Provide the animation and view , to start the animation,
     * @param animation animation
     * @param view on which animation is to be performed
     * It will run in main thread*/
    public void startAnimation(Animation animation, View view) {
        view.startAnimation(animation);
    }

    /**Provide the animation and view , to start the animation,
     * It will run in seperate thread
     * @param animId provide the animation id which is present in anim folder
     * @param view view on which animation is to be performed*/
    public void startAnimationInSeperateThread(final int animId,final View view) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                view.startAnimation((Andy.RES.anim(animId)));
            }
        });
    }

    /**Provide the animation and view , to start the animation,
     * It will run in separate thread
     * @param animation animation file
     * @param view view on which animation is to be performed*/
    public void startAnimationInSeperateThread(final Animation animation,final View view) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                view.startAnimation(animation);
            }
        });
    }
}
