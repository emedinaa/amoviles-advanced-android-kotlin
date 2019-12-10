package com.emedinaa.kotlinapp.data

import android.content.Context
import com.emedinaa.kotlinapp.model.AuthenticationDataSource
import com.emedinaa.kotlinapp.model.AuthenticationRepository

object Injection {

    private lateinit var context: Context

    fun setUp(mContext: Context){
        context= mContext
    }

    fun provideLogInRepository(): AuthenticationDataSource {
        return AuthenticationRepository(context)
    }
}