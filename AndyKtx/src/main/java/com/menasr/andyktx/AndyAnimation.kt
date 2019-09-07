package com.menasr.andyktx

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import kotlin.math.hypot

@Suppress("unused")
class AndyAnimation {

    /**
     * Creates a material style circular reveal entrance animation similar to the one in Google Play.
     * @param view your view.
     */
    @TargetApi(21)
    fun circularRevealEnter(view: View) {
        val cx = view.width / 2
        val cy = view.height / 2

        val finalRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

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

        val initialRadius = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0f)

        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })

        anim.start()
    }

    /**Provide the animation and view , to start the animation,
     * @param animId anim id like R.anim.<id of anim>
     * @param view on which animation is to be performed
     * It will run in main thread*/
    fun startAnimation(animId: Int, view: View) = view.startAnimation((Andy.RES.anim(animId)))

    /**Provide the animation and view , to start the animation,
     * @param animation animation file for animation
     * @param view on which animation is to be performed
     * It will run in main thread*/
    fun startAnimation(animation: Animation,view: View)=view.startAnimation(animation)

    /**Provide the animation and view , to start the animation,
     * It will run in seperate thread
     * @param animId provide the animation id which is present in anim folder
     * @param view view on which animation is to be performed*/
    fun startAnimationInSeperateThread(animId: Int, view: View) {
        Handler(Looper.getMainLooper()).post { view.startAnimation((Andy.RES.anim(animId))) }
    }

    /**Provide the animation and view , to start the animation,
     * It will run in seperate thread
     * @param animation provide the animation id which is present in anim folder
     * @param view view on which animation is to be performed*/
    fun startAnimationInSeperateThread(animation: Animation, view: View) {
        Handler(Looper.getMainLooper()).post { view.startAnimation(animation) }
    }
}
