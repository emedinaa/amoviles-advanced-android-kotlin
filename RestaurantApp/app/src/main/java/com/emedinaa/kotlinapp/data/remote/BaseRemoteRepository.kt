package com.emedinaa.kotlinapp.data.remote
import com.emedinaa.kotlinapp.data.DataCallback
import retrofit2.Call

open class BaseRemoteRepository<T>(val apiClient: ApiClient.ServicesApiInterface) {

    private val DEFAULT_MSG_ERROR = "Ocurrió un error"
    protected var currentCall: Call<T>? = null

    protected fun cancel() {
        currentCall?.cancel()
    }

    protected fun processFailure(call: Call<T>?, t: Throwable, dataCallback: DataCallback<*>) {
        call?.let {
            if(!it.isCanceled){
                dataCallback.onError(t.message?:DEFAULT_MSG_ERROR,Exception(t))
            }
        }
    }
}