package com.appHelperKtx

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.view.View
import android.view.ViewAnimationUtils

class RAnim {

    /**
     * Creates a material style circular reveal entrance animation similar to the one in Google Play.
     * @param view your view.
     */
    @TargetApi(21)
    fun circularRevealEnter(view: View) {
        val cx = view.width / 2
        val cy = view.height / 2

        val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, finalRadius)

        view.visibility = View.VISIBLE
        anim.start()
    }

    /**
     * Creates a material style circular reveal exit animation similar to the one in Google Play.
     * @param view your view.
     */
    @TargetApi(21)
    fun circularRevealExit(view: View) {
        val cx = view.width / 2
        val cy = view.height / 2

        val initialRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0f)

        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })

        anim.start()
    }
}
