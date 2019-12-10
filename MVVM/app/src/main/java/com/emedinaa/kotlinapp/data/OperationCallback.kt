package com.emedinaa.kotlinapp.data

import com.emedinaa.kotlinapp.model.User

interface OperationCallback {

    fun onSuccess(user:User?)
    fun onError(obj:Any?)
}