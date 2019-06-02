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
        lateinit var RES: Res
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

        fun init(context: Context) {

            if (instance == null)
                instance = WeakReference(Andy())

            RES = Res(context.applicationContext)
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
        }

        /**
         * Get factorial of a number
         */
        fun getFatorial(number: Int): Long {
            return if (number == 0) 0
            else if (number == 1) 1
            else number * getFatorial(number - 1)
        }

        /**
         * Get Fibonacci series upto total no of digits
         */
        fun getFibonacci(totalDigits: Int): List<Int> {
            val allFibo = LinkedList<Int>()
            var t1 = 0
            var t2 = 1
            for (i in 1..totalDigits) {
                allFibo.add(t1)

                val sum = t1 + t2
                t1 = t2
                t2 = sum
            }
            return allFibo
        }

        /**
         * set recyclerview's Grid scrolling property to Horizontal
         * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
         * set adapter later
         *
         * @param recyclerView pass your recyclerview
         * @param itemsPerRow  send no. of items in rows you want
         */
        fun setLayoutGridHorizontal(recyclerView: RecyclerView, itemsPerRow: Int) {
            val gridLayoutManager = GridLayoutManager(recyclerView.context, itemsPerRow)
            // set Horizontal Orientation
            gridLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            // set LayoutManager to RecyclerView
            recyclerView.layoutManager = gridLayoutManager
            recyclerView.itemAnimator = DefaultItemAnimator()
        }

        /**
         * set recyclerview's default Horizontal scrolling property
         * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
         * set adapter later
         *
         * @param recyclerView pass your recyclerview
         */
        fun setLayoutListHorizontal(recyclerView: RecyclerView) {
            val linearLayoutManager = LinearLayoutManager(recyclerView.context)
            // set Horizontal Orientation
            linearLayoutManager.orientation = LinearLayout.HORIZONTAL
            // set LayoutManager to RecyclerView
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.itemAnimator = DefaultItemAnimator()

        }

        /**
         * set recyclerview's default Grid scrolling property
         * it will only set **layoutmanager** and **ItemAnimator** to default you have to set
         * set adapter later
         *
         * @param recyclerView pass your recyclerview
         * @param itemsPerRow  send no. of items in rows you want
         */
        fun setLayoutGridVertical(recyclerView: RecyclerView, itemsPerRow: Int) {
            val gridLayoutManager = GridLayoutManager(recyclerView.context, itemsPerRow)
            recyclerView.layoutManager = gridLayoutManager // set LayoutManager to RecyclerView
            recyclerView.itemAnimator = DefaultItemAnimator()
        }

        /**
         * set recyclerview's default vertial scrolling property
         * it will only set **layoutmanager** and **ItemAnimator** to default you have to set
         * set adapter later
         *
         * @param recyclerView pass your recyclerview
         */
        fun setLayoutListVertical(recyclerView: RecyclerView) {
            val linearLayoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.layoutManager = linearLayoutManager // set LayoutManager to RecyclerView
            recyclerView.itemAnimator = DefaultItemAnimator()
        }

        /***
         * Computes RFC 2104-compliant HMAC signature. This can be used to sign the Amazon S3
         * request urls
         *
         * @param data The data to be signed.
         * @param key  The signing key.
         * @return The Base64-encoded RFC 2104-compliant HMAC signature.
         * @throws java.security.SignatureException when signature generation fails
         */
        @Throws(SignatureException::class)
        fun getHMac(data: String?, key: String): String? {

            if (data == null) {
                throw NullPointerException("Data to be signed cannot be null")
            }

            var result: String? = null
            try {

                val HMAC_SHA1_ALGORITHM = "HmacSHA1"

                // get an hmac_sha1 key from the raw key bytes
                val signingKey = SecretKeySpec(key.toByteArray(), HMAC_SHA1_ALGORITHM)

                // get an hmac_sha1 Mac instance &
                // initialize with the signing key
                val mac = Mac.getInstance(HMAC_SHA1_ALGORITHM)
                mac.init(signingKey)

                // compute the hmac on input data bytes
                val digest = mac.doFinal(data.toByteArray())

                if (digest != null) {
                    // Base 64 Encode the results
                    result = Base64.encodeToString(digest, Base64.NO_WRAP)
                }

            } catch (e: Exception) {
                throw SignatureException("Failed to generate HMAC : " + e.message)
            }

            return result
        }

        /**
         * Get base context from a view
         *
         * @param view view from which baseContext is required
         * @return context object
         */
        fun getBaseContext(view: View): Context {
            var context = view.context
            if (context is ContextThemeWrapper) {
                context = (view.context as ContextThemeWrapper).baseContext
            }
            return context
        }

        /**
         * Method which provides divider between Tab items
         *
         * @param tabLayout          just pass your tab layout
         * @param color              int color of line
         * @param width              width of margin
         * @param height             height of margin
         * @param paddingFromDivider padding from divider, it will applied to both side of margin
         */
        fun addMarginInTabLayout(
            tabLayout: TabLayout, color: Int, width: Int, height: Int,
            paddingFromDivider: Int
        ) {
            val linearLayout = tabLayout.getChildAt(0) as LinearLayout
            linearLayout.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable();
            drawable.setColor(color)
            drawable.setSize(width, height)
            linearLayout.dividerPadding = paddingFromDivider
            linearLayout.dividerDrawable = drawable
        }

        /**
         * Function to get Progress percentage
         *
         * @param currentDuration current progress duration
         * @param totalDuration total duration
         */
        fun getProgressPercentage(currentDuration: Long, totalDuration: Long): Int {
            val currentSeconds = (currentDuration / 1000).toInt().toLong()
            val totalSeconds = (totalDuration / 1000).toInt().toLong()

            return (currentSeconds.toDouble() / totalSeconds * 100).toInt()
        }

        /**
         * This method filter the value in array, which is two frequent
         *
         * @param input  input array to filter
         * @param output output array to filter
         * @param filter factor to filter input and output(best is 0.05 to 0.25)
         * @return output array on basis of filter
         */
        fun lowPassFilter(input: FloatArray, output: FloatArray?, filter: Float): FloatArray {
            if (output == null) return input
            for (i in input.indices)
                output[i] = output[i] + filter * (input[i] - output[i])

            return output
        }
    }

    private fun initializeThings() {
        (context as Activity).windowManager.defaultDisplay.getMetrics(METRICS)
    }

}