package com.appHelperKtx

import android.util.Log
import java.util.regex.Pattern

class LogUtil {
    private var SHOW_VERBOSE = true
    private var SHOW_DEBUG = true
    private var SHOW_INFO = true
    private var SHOW_WARNING = true
    private var SHOW_ERROR = true
    private var SHOW_WTF = true
    private val mPattern = Pattern.compile("[\\.]+")

    /**
     * Method to show all log at once, if you are using LOG(LogUtil) class for showing log
     * in an application
     *
     * @param showOrHide pass "true" to show all log and "false" to hide all log
     */
    fun showHideAllLog(showOrHide: Boolean) {
        SHOW_WTF = showOrHide
        SHOW_WARNING = SHOW_WTF
        SHOW_VERBOSE = SHOW_WARNING
        SHOW_INFO = SHOW_VERBOSE
        SHOW_ERROR = SHOW_INFO
        SHOW_DEBUG = SHOW_ERROR
    }

    fun v(tag: String, msg: String) {
        if (SHOW_VERBOSE)
            Log.v(tag, msg)
    }

    fun v(tag: String, msg: String, throwable: Throwable) {
        if (SHOW_VERBOSE)
            Log.v(tag, msg, throwable)
    }

    fun d(tag: String, msg: String) {
        if (SHOW_DEBUG)
            Log.d(tag, msg)
    }

    fun d(tag: String, msg: String, throwable: Throwable) {
        if (SHOW_DEBUG)
            Log.d(tag, msg, throwable)
    }

    fun i(tag: String, msg: String) {
        if (SHOW_INFO)
            Log.i(tag, msg)
    }

    fun i(tag: String, msg: String, throwable: Throwable) {
        if (SHOW_INFO)
            Log.i(tag, msg, throwable)
    }

    fun w(tag: String, msg: String) {
        if (SHOW_WARNING)
            Log.w(tag, msg)
    }

    fun w(tag: String, msg: String, throwable: Throwable) {
        if (SHOW_WARNING)
            Log.w(tag, msg, throwable)
    }

    fun e(tag: String, msg: String) {
        if (SHOW_ERROR)
            Log.e(tag, msg)
    }

    fun e(tag: String, msg: String, throwable: Throwable) {
        if (SHOW_ERROR)
            Log.e(tag, msg, throwable)
    }

    fun wtf(tag: String, msg: String) {
        if (SHOW_WTF)
            Log.wtf(tag, msg)
    }

    fun wtf(tag: String, msg: String, throwable: Throwable) {
        if (SHOW_WTF)
            Log.wtf(tag, msg, throwable)
    }

    fun printInAllLogFormat(tag: String, msg: String) {
        v(tag, msg)
        d(tag, msg)
        i(tag, msg)
        w(tag, msg)
        e(tag, msg)
        wtf(tag, msg)
    }

    fun printInAllLogFormat(tag: String, msg: String, throwable: Throwable) {
        v(tag, msg, throwable)
        d(tag, msg, throwable)
        i(tag, msg, throwable)
        w(tag, msg, throwable)
        e(tag, msg, throwable)
        wtf(tag, msg, throwable)
    }

    /**
     * Method to getTag of this class
     *
     * @return tag
     */
    private fun getTag(): String {
        val trace = Thread.currentThread().stackTrace[4]
        val cla = trace.className
        val splitedStr = mPattern.split(cla)
        val simpleClass = splitedStr[splitedStr.size - 1]
        val mthd = trace.methodName
        val line = trace.lineNumber
        return "$simpleClass#$mthd:$line"
    }
}