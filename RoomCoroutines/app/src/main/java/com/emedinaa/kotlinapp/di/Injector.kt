package com.emedinaa.kotlinapp.di

import android.content.Context
import com.emedinaa.kotlinapp.storage.CNoteRepository

object Injector {

    private lateinit var repository: CNoteRepository

    fun setup(context: Context){
        repository= CNoteRepository(context)
    }

    fun provideRepository():CNoteRepository= repository
}