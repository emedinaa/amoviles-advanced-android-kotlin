package com.emedinaa.kotlinapp.data

interface DataCallback<T> {
    fun onSuccess(data:T)
    fun onError(errorMessage:String,exception:Exception)
}