package com.menasr.androidhelper

import android.annotation.SuppressLint
import android.app.Activity
import java.lang.ref.WeakReference

class Andy {

//    constructor(activity: Activity) : this() {
//        activity.let {
//            initializeThings(it)
//        }
//    }

    companion object {
        private var instance: WeakReference<Andy>? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var res: ResourceLoader

        lateinit var color: Color_
        lateinit var converter: Converter

        fun init(activity: Activity) {
            if (instance == null) {
                instance = WeakReference(Andy())

                res = ResourceLoader(activity.applicationContext)
                color = Color_()
                converter = Converter()
            }
        }

    }
}