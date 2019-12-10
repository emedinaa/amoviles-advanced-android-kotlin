package com.emedinaa.kotlinapp.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class ApiClient {
    //private val API_BASE_URL = "https://blooming-sierra-86800.herokuapp.com"
    private val API_BASE_URL = "https://diplomado-restaurant-backend.herokuapp.com/"
    private lateinit var servicesApiInterface: ServicesApiInterface


    fun build(): ServicesApiInterface {
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface {

        //https://obscure-earth-55790.herokuapp.com/api/login
        @POST("/api/login")
        fun login(@Body raw: LogInRaw?): Call<LogInResponse>

        @GET("/categorias")
        fun categories(): Call<CategoriesResponse>

        @GET("/categorias/{id}")
        fun category(@Path("id") id:String): Call<CategoryResponse>


        @GET("/platos")
        fun dishes(): Call<DishesResponse>

        @GET("/platos/{id}")
        fun dish(@Path("id") id:String): Call<DishResponse>

        @POST("/auth/clientes-login")
        fun loginAdmin(@Body raw: LogInRaw?): Call<LogInResponse>

        //@POST("/auth/usuarios-login")
        //fun loginAdmin(@Body raw: LogInRaw?): Call<LogInResponse>

        @GET("/api/categorias")
        fun categoriesWT(@Header("Authorization") authorization:String) :Call<CategoriesResponse>

        @GET("/platos/categoria/{idCategory}")
        fun dishesByCategory(@Path("idCategory") idCategory:String):Call<DishesResponse>

    }
}