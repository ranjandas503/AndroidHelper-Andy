package com.menasr.sampleforandroidhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import com.menasr.androidhelper.Andy
import com.menasr.androidhelper.ResourceLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Andy.init(this)

        sampleText.text= Andy.res.string(R.string.app_name)
        sampleText.setTextColor(Andy.res.color(R.color.colorAccent))
        sampleText.textSize = Andy.res.dimen(R.dimen.text).toString().toFloat()


        image.setImageDrawable(Andy.res.drawable(R.drawable.ic_directions_bike_black_24dp))
//        image.animation = Andy.res.anim(R.anim.rotate)
//        image.animate().start()
//        Andy.res.startAnim(R.anim.rotate,image)
    }
}
