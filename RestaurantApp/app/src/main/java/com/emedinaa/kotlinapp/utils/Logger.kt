package com.emedinaa.kotlinapp.utils

import com.emedinaa.kotlinapp.BuildConfig
import timber.log.Timber

object Logger {

    fun setUp(){
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    fun d(message:String) = Timber.d(message)
    fun d(throwable:Throwable) = Timber.d(throwable)
    fun v(message:String) = Timber.v(message)
    fun i(message:String) = Timber.i(message)
    fun e(message:String) = Timber.e(message)
    fun e(throwable:Throwable) = Timber.e(throwable)
}