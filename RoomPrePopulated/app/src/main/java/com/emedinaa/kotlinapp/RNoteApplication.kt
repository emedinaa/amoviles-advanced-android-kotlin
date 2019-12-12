package com.emedinaa.kotlinapp

import android.app.Application
import com.emedinaa.kotlinapp.storage.RNoteRepository

class RNoteApplication :Application() {

    private lateinit var repository: RNoteRepository

    override fun onCreate() {
        super.onCreate()

        repository=RNoteRepository(this)
    }

    fun getNoteRepository():RNoteRepository{
        return repository
    }
}