package com.emedinaa.kotlinapp.home

import com.emedinaa.kotlinapp.data.*
import com.emedinaa.kotlinapp.data.remote.ApiClient
import com.emedinaa.kotlinapp.data.remote.BaseRemoteRepository
import com.emedinaa.kotlinapp.data.remote.CategoriesResponse
import com.emedinaa.kotlinapp.model.Category
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesRepository(apiClient: ApiClient.ServicesApiInterface):
    BaseRemoteRepository<CategoriesResponse>(apiClient) {

    fun retrieveCategories( callback: DataCallback<List<Category>>){
        currentCall= apiClient.categories()

        currentCall?.enqueue(object: Callback<CategoriesResponse> {
            override fun onFailure(call: Call<CategoriesResponse>, t: Throwable) {
                processFailure(call,t,callback)
            }

            override fun onResponse(call: Call<CategoriesResponse>, response: Response<CategoriesResponse>) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        callback.onSuccess(it)
                    }
                }
            }
        })
    }
}