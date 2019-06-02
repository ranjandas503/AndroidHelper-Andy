package com.menasr.andyktx

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.design.widget.TabLayout
import android.support.v7.view.ContextThemeWrapper
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.DisplayMetrics
import android.view.View
import android.widget.LinearLayout
import java.lang.ref.WeakReference
import java.security.SignatureException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@SuppressLint("ParcelCreator")
class Andy() : Application() {
    lateinit var context: Context

    constructor(context: Context) : this() {
        this.context = context
        initializeThings()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: WeakReference<Andy>? = null
        lateinit var RES: AndyResources
        lateinit var METRICS: DisplayMetrics
        lateinit var DATE_TIME: DateTime
        lateinit var ALGORITHM: Algorithm
        lateinit var COLOR: Colour
        lateinit var CONVERTER: Converter
        lateinit var DEVICE: DeviceUtils
        lateinit var SHELL: ShellUtils
        lateinit var FILES: Files
        lateinit var IMAGE: Img
        lateinit var INTENT: IntentUtil
        lateinit var INTERNET: Internet
        lateinit var LOG: LogUtil
        lateinit var MAPS: Maps
        lateinit var MEDIA: Media
        lateinit var ANIMATION: AndyAnimation
        lateinit var NAV_BAR: NavigationBar
        lateinit var REGEX: RegexConst
        lateinit var TOOLBAR: AndyToolbar
        lateinit var WINDOW: AndyWindow
        lateinit var SNACKBAR: Snack
        lateinit var SOFT_INPUT: SoftInput
        lateinit var STATUS_BAR: StatusBar
        lateinit var STRING: StringHelper
        @SuppressLint("StaticFieldLeak")
        lateinit var TOAST: AndyToast
        lateinit var YOU_TUBE: YouTubeUtils
        lateinit var LAYOUT: Layout
        lateinit var NUMBER: AndyNumber

        fun init(context: Context) {

            if (instance == null)
                instance = WeakReference(Andy())

            RES = AndyResources(context.applicationContext)
            METRICS = DisplayMetrics()
            DATE_TIME = DateTime()
            ALGORITHM = Algorithm()
            COLOR = Colour()
            CONVERTER = Converter()
            DEVICE = DeviceUtils()
            SHELL = ShellUtils()
            FILES = Files()
            IMAGE = Img()
            INTENT = IntentUtil()
            INTERNET = Internet()
            LOG = LogUtil()
            MAPS = Maps()
            MEDIA = Media()
            ANIMATION = AndyAnimation()
            NAV_BAR = NavigationBar()
            REGEX = RegexConst
            TOOLBAR = AndyToolbar()
            WINDOW = AndyWindow()
            SNACKBAR = Snack()
            SOFT_INPUT = SoftInput()
            STATUS_BAR = StatusBar()
            STRING = StringHelper()
            TOAST = AndyToast(context.applicationContext)
            YOU_TUBE = YouTubeUtils()
            LAYOUT=Layout()
        }
    }

    private fun initializeThings() {
        (context as Activity).windowManager.defaultDisplay.getMetrics(METRICS)
    }

}