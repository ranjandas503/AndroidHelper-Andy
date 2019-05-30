package com.menasr.androidhelper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import java.lang.ref.WeakReference

class Andy {

    companion object {
        private var instance: WeakReference<Andy>? = null

        @SuppressLint("StaticFieldLeak")
        lateinit var res: ResourceLoader
        lateinit var log: LogUtil
        lateinit var shell: ShellUtils
        lateinit var CONST: Const
        lateinit var TIME_CONS: RTimeConst
        @SuppressLint("StaticFieldLeak")
        lateinit var toast: ToastUtils
        lateinit var color: Color_
        lateinit var converter: Converter
        lateinit var dateTime: DateTime
        lateinit var device: DeviceUtils
        lateinit var file: Files
        lateinit var image: ImageUtils
        lateinit var intent: IntentUtil
        lateinit var internet: Internet
        lateinit var maps: Maps
        lateinit var media: Media

        fun init(activity: Activity) {
            if (instance == null) {
                instance = WeakReference(Andy())

                res = ResourceLoader(activity.applicationContext)
                log = LogUtil()
                shell = ShellUtils()
                toast = ToastUtils(activity.applicationContext)
                color = Color_()
                converter = Converter()
                dateTime = DateTime()
                device = DeviceUtils()
                file = Files()
                image = ImageUtils()
                intent = IntentUtil()
                internet = Internet()
                maps = Maps()
                media = Media()

            }
        }

    }
}