package com.emedinaa.kotlinapp.model

import com.emedinaa.kotlinapp.data.OperationCallback

interface AuthenticationDataSource {

    fun logIn(username:String,password:String,callback: OperationCallback)
    fun cancel()
}