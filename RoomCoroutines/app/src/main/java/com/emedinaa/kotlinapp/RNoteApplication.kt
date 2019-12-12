package com.emedinaa.kotlinapp

import android.app.Application
import com.emedinaa.kotlinapp.di.Injector

class RNoteApplication :Application() {

    override fun onCreate() {
        super.onCreate()

        Injector.setup(this)
    }
}