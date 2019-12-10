package com.emedinaa.kotlinapp.storage

import io.realm.Realm
import io.realm.RealmConfiguration

class NoteRealmDatabase {

    companion object {
        const val DATABASE_VERSION:Long=1
        const val DATABASE_NAME:String="BDNote-Realm"
    }

    val realm: Realm by lazy {
        val config = RealmConfiguration.Builder()
            .name(DATABASE_NAME)
            .schemaVersion(DATABASE_VERSION)
            .build()
        Realm.getInstance(config)
    }

}