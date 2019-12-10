package com.emedinaa.kotlinapp

import android.app.Application
import io.realm.Realm

class NoteApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}