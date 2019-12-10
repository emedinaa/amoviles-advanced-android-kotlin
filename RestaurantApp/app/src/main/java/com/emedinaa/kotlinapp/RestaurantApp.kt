package com.emedinaa.kotlinapp

import android.app.Application
import com.emedinaa.kotlinapp.data.DataInjector
import com.emedinaa.kotlinapp.utils.Logger

class RestaurantApp :Application(){

    override fun onCreate() {
        super.onCreate()

        DataInjector.instance().setUp(this)
        Logger.setUp()
    }
}