package com.menasr.sampleforandroidhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.menasr.androidhelper.Andy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Andy.init(this)

        sampleText.text= Andy.res.string(R.string.app_name)
        sampleText.setTextColor(Andy.res.color(R.color.colorAccent))

        image.setImageDrawable(Andy.res.drawable(R.drawable.ic_directions_bike_black_24dp))
    }
}
