package com.menasr.andyktx

import android.content.Context
import android.widget.Toast

/** This is custom Toast class */
class AndyToast(val context: Context) {
    /**
     * @param stringId pass String id like **R.string.'String name'**
     */
    fun toastShort(stringId: Int) =
        Toast.makeText(context, Andy.RES.getString(stringId), Toast.LENGTH_SHORT).show()

    /**
     * @param msg "Message" pass String id like **R.string.'String name'**
     */
    fun toastShort(msg: String) =
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()


    /**
     * @param stringId pass String id like **R.string.'String name'**
     */
    fun toastLong(stringId: Int) =
        Toast.makeText(context, Andy.RES.getString(stringId), Toast.LENGTH_LONG).show()


    /**
     * @param msg "Message" pass String id like **R.string.'String name'**
     */
    fun toastLong(msg: String) =
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()

}
