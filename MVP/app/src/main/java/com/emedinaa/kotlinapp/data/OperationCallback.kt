package com.emedinaa.kotlinapp.data

interface OperationCallback {

    fun onSuccess(obj:Any?)
    fun onError(obj:Any?)
}