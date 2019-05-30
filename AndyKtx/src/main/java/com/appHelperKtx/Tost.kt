package com.appHelperKtx

import android.widget.Toast

/** This is custom Toast class */
class Tost {
    internal var context = Andy().context

    /**
     * @param stringId pass String id like **R.string.'String name'**
     */
    fun toastShort(stringId: Int) {
        Toast.makeText(context, Andy.RES.getString(stringId), Toast.LENGTH_SHORT).show()
    }

    /**
     * @param msg "Message" pass String id like **R.string.'String name'**
     */
    fun toastShort(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * @param stringId pass String id like **R.string.'String name'**
     */
    fun toastLong(stringId: Int) {
        Toast.makeText(context, Andy.RES.getString(stringId), Toast.LENGTH_LONG).show()
    }

    /**
     * @param msg "Message" pass String id like **R.string.'String name'**
     */
    fun toastLong(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }


}
