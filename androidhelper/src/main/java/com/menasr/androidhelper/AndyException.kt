package com.menasr.androidhelper

open class AndyException : Exception() {

    fun appCtxNotPresent(): String = "Provide Application Context"

    fun activityContext():String="Provide Activity Context"
}