package com.menasr.sampleforandroidhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.menasr.andyktx.Andy

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize it once in any activity before using others
        Andy.init(this)

        //start using andy
        Andy.ANIMATION.startAnimationInSeperateThread(R.anim.rotate,sampleText)
    }
}
