package com.menasr.andy;

import android.util.Log;

import java.util.regex.Pattern;

public class LogUtil {

    private boolean SHOW_VERBOSE = true;
    private boolean SHOW_DEBUG = true;
    private boolean SHOW_INFO = true;
    private boolean SHOW_WARNING = true;
    private boolean SHOW_ERROR = true;
    private boolean SHOW_WTF = true;
    private final Pattern mPattern = Pattern.compile("[\\.]+");

    /**
     * Method to show all log at once, if you are using LOG(LogUtil) class for showing log
     * in an application
     *
     * @param showOrHide pass "true" to show all log and "false" to hide all log
     */
    public void showHideAllLog(boolean showOrHide) {
        SHOW_DEBUG = SHOW_ERROR = SHOW_INFO = SHOW_VERBOSE = SHOW_WARNING = SHOW_WTF = showOrHide;
    }

    public void v(String tag, String msg) {
        if (SHOW_VERBOSE)
            Log.v(tag, msg);
    }

    public void v(String tag, String msg, Throwable throwable) {
        if (SHOW_VERBOSE)
            Log.v(tag, msg, throwable);
    }

    public void d(String tag, String msg) {
        if (SHOW_DEBUG)
            Log.d(tag, msg);
    }

    public void d(String tag, String msg, Throwable throwable) {
        if (SHOW_DEBUG)
            Log.d(tag, msg, throwable);
    }

    public void i(String tag, String msg) {
        if (SHOW_INFO)
            Log.i(tag, msg);
    }

    public void i(String tag, String msg, Throwable throwable) {
        if (SHOW_INFO)
            Log.i(tag, msg, throwable);
    }

    public void w(String tag, String msg) {
        if (SHOW_WARNING)
            Log.w(tag, msg);
    }

    public void w(String tag, String msg, Throwable throwable) {
        if (SHOW_WARNING)
            Log.w(tag, msg, throwable);
    }

    public void e(String tag, String msg) {
        if (SHOW_ERROR)
            Log.e(tag, msg);
    }

    public void e(String tag, String msg, Throwable throwable) {
        if (SHOW_ERROR)
            Log.e(tag, msg, throwable);
    }

    public void wtf(String tag, String msg) {
        if (SHOW_WTF)
            Log.wtf(tag, msg);
    }

    public void wtf(String tag, String msg, Throwable throwable) {
        if (SHOW_WTF)
            Log.wtf(tag, msg, throwable);
    }

    public void printInAllLogFormat(String tag, String msg) {
        v(tag, msg);
        d(tag, msg);
        i(tag, msg);
        w(tag, msg);
        e(tag, msg);
        wtf(tag, msg);
    }

    public void printInAllLogFormat(String tag, String msg, Throwable throwable) {
        v(tag, msg, throwable);
        d(tag, msg, throwable);
        i(tag, msg, throwable);
        w(tag, msg, throwable);
        e(tag, msg, throwable);
        wtf(tag, msg, throwable);
    }

    /**
     * Method to getTag of this class
     *
     * @return tag
     */
    private String getTag() {
        final StackTraceElement trace = Thread.currentThread().getStackTrace()[4];
        final String cla = trace.getClassName();
        final String[] splitedStr = mPattern.split(cla);
        final String simpleClass = splitedStr[splitedStr.length - 1];
        final String mthd = trace.getMethodName();
        final int line = trace.getLineNumber();
        return simpleClass + "#" + mthd + ":" + line;
    }


}
