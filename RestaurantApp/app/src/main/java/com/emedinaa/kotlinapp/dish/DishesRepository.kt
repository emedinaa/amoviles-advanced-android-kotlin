package com.emedinaa.kotlinapp.dish

import com.emedinaa.kotlinapp.data.*
import com.emedinaa.kotlinapp.data.remote.ApiClient
import com.emedinaa.kotlinapp.data.remote.BaseRemoteRepository
import com.emedinaa.kotlinapp.data.remote.DishesResponse
import com.emedinaa.kotlinapp.model.Dish
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DishesRepository(apiClient: ApiClient.ServicesApiInterface):
    BaseRemoteRepository<DishesResponse>(apiClient) {

    fun retrieveDishes( callback: DataCallback<List<Dish>>){
        currentCall= apiClient.dishes()

        currentCall?.enqueue(object: Callback<DishesResponse> {
            override fun onFailure(call: Call<DishesResponse>, t: Throwable) {
                processFailure(call,t,callback)
            }

            override fun onResponse(call: Call<DishesResponse>, response: Response<DishesResponse>) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        callback.onSuccess(it)
                    }
                }
            }
        })
    }

    fun retrieveDishesByCategory(idCategory:String,callback: DataCallback<List<Dish>>){
        currentCall= apiClient.dishesByCategory(idCategory)

        currentCall?.enqueue(object: Callback<DishesResponse> {
            override fun onFailure(call: Call<DishesResponse>, t: Throwable) {
                processFailure(call,t,callback)
            }

            override fun onResponse(call: Call<DishesResponse>, response: Response<DishesResponse>) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        callback.onSuccess(it)
                    }
                }
            }
        })
    }
}