package com.emedinaa.kotlinapp.home

import com.emedinaa.kotlinapp.data.*
import com.emedinaa.kotlinapp.data.remote.ApiClient
import com.emedinaa.kotlinapp.data.remote.BaseRemoteRepository
import com.emedinaa.kotlinapp.data.remote.CategoryResponse
import com.emedinaa.kotlinapp.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository(apiClient: ApiClient.ServicesApiInterface):
    BaseRemoteRepository<CategoryResponse>(apiClient) {

    fun retrieveCategory( id:String,callback: DataCallback<Category>){
        currentCall= apiClient.category(id)

        currentCall?.enqueue(object: Callback<CategoryResponse> {
            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                processFailure(call,t,callback)
            }

            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        callback.onSuccess(it)
                    }
                }
            }
        })
    }
}