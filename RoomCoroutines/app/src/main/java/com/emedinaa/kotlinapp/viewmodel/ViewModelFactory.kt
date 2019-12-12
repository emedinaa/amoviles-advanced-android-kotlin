package com.emedinaa.kotlinapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emedinaa.kotlinapp.storage.CNoteRepository

class ViewModelFactory(private val repository:CNoteRepository,
                       private val application:Application):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(repository,application ) as T
    }
}