package com.menasr.androidhelper

import android.content.Context

class ResourceLoader(var appCtx: Context) {

    /**Get message from resources
     * @param msgId like "R.sting.<int id> to get the string msg"*/
    fun getString(msgId: Int): String = appCtx.getString(msgId)
}