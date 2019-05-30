package com.menasr.androidhelper

import android.content.Context
import android.widget.Toast

/** This is custom Toast class */
class ToastUtils(val appctx:Context) {
    val res:ResourceLoader = ResourceLoader(appctx)

    /**
     * @param stringId pass String id like **R.string.'String name'**
     */
    fun toastShort(stringId: Int) {
        Toast.makeText(appctx, res.string(stringId), Toast.LENGTH_SHORT).show()
    }

    /**
     * @param msg "Message" pass String id like **R.string.'String name'**
     */
    fun toastShort(msg: String) {
        Toast.makeText(appctx, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * @param stringId pass String id like **R.string.'String name'**
     */
    fun toastLong(stringId: Int) {
        Toast.makeText(appctx, res.string(stringId), Toast.LENGTH_LONG).show()
    }

    /**
     * @param msg "Message" pass String id like **R.string.'String name'**
     */
    fun toastLong(msg: String) {
        Toast.makeText(appctx, msg, Toast.LENGTH_LONG).show()
    }


}