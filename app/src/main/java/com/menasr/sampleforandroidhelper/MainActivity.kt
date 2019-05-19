package com.menasr.sampleforandroidhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.menasr.androidhelper.Andy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var andy=Andy(applicationContext)


        sampleText.text= andy.res.getString(R.string.app_name)
    }
}
