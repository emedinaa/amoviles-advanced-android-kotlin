package com.emedinaa.kotlinapp.login
import com.emedinaa.kotlinapp.data.DataCallback
import com.emedinaa.kotlinapp.data.remote.ApiClient
import com.emedinaa.kotlinapp.data.remote.BaseRemoteRepository
import com.emedinaa.kotlinapp.data.remote.LogInRaw
import com.emedinaa.kotlinapp.data.remote.LogInResponse
import com.emedinaa.kotlinapp.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInRepository(apiClient: ApiClient.ServicesApiInterface):BaseRemoteRepository<LogInResponse>(apiClient) {

    fun logIn(username:String, password:String, callback:DataCallback<User>){
        val logInRaw= LogInRaw(username,password)
        currentCall= apiClient.loginAdmin(logInRaw)

        currentCall?.enqueue(object:Callback<LogInResponse>{
            override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                processFailure(call,t,callback)
            }

            override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        callback.onSuccess(it)
                    }
                }
            }
        })
    }
}