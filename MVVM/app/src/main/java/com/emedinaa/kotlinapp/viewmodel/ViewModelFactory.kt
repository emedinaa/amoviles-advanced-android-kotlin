package com.emedinaa.kotlinapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.emedinaa.kotlinapp.model.AuthenticationDataSource

class ViewModelFactory(private val repository:AuthenticationDataSource): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LogInViewModel(repository) as T
    }
}