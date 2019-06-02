package com.menasr.sampleforandroidhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.menasr.andy.Andy

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Andy.init(this)

//        Andy.ANIMATION.startAnimation(R.anim.rotate,sampleText)
        Andy.ANIMATION.startAnimationInSeperateThread(R.anim.rotate,sampleText)

    }
}
