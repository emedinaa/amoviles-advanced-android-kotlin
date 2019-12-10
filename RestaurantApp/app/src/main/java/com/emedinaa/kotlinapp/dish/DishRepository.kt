package com.emedinaa.kotlinapp.dish

import com.emedinaa.kotlinapp.data.*
import com.emedinaa.kotlinapp.data.remote.ApiClient
import com.emedinaa.kotlinapp.data.remote.BaseRemoteRepository
import com.emedinaa.kotlinapp.data.remote.DishResponse
import com.emedinaa.kotlinapp.model.Category
import com.emedinaa.kotlinapp.model.Dish
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishRepository(apiClient: ApiClient.ServicesApiInterface):
    BaseRemoteRepository<DishResponse>(apiClient) {

    fun retrieveCategory( id:String,callback: DataCallback<Dish>){
        currentCall= apiClient.dish(id)

        currentCall?.enqueue(object: Callback<DishResponse> {
            override fun onFailure(call: Call<DishResponse>, t: Throwable) {
                processFailure(call,t,callback)
            }

            override fun onResponse(call: Call<DishResponse>, response: Response<DishResponse>) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        callback.onSuccess(it)
                    }
                }
            }
        })
    }
}