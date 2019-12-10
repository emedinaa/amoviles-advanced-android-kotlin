package com.emedinaa.kotlinapp.data

import android.content.Context

object Injection {

    private lateinit var context: Context

    fun setUp(mContext: Context){
        context= mContext
    }

    fun provideLogInRepository(): LogInRepository {
        return LogInRepository.getInstance(context)
    }
}