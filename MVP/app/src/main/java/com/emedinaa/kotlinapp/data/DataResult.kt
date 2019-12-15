package com.emedinaa.kotlinapp.data

sealed class DataResult<out T> {

    data class Success<T>(val data:T):DataResult<T>()
    data class InvalidUserOrPw(val message:String): DataResult<Nothing>()
    data class Failure(val e:Exception): DataResult<Nothing>()
}