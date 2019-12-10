package com.emedinaa.kotlinapp.data

import android.content.Context
import com.emedinaa.kotlinapp.data.remote.ApiClient
import com.emedinaa.kotlinapp.data.socket.SocketManager
import com.emedinaa.kotlinapp.dish.DishesRepository
import com.emedinaa.kotlinapp.home.CategoriesRepository
import com.emedinaa.kotlinapp.login.LogInRepository

object DataInjector {

    private lateinit var apiClient:ApiClient.ServicesApiInterface
    private lateinit var logInRepository:LogInRepository
    private lateinit var dishesRepository: DishesRepository
    private lateinit var categoriesRepository: CategoriesRepository
    private lateinit var socketManager:SocketManager

    fun setUp(context:Context){
        apiClient= ApiClient().build()
        socketManager= SocketManager()

        logInRepository = LogInRepository(apiClient)
        dishesRepository = DishesRepository(apiClient)
        categoriesRepository = CategoriesRepository(apiClient)
    }

    fun provideLogInRepository():LogInRepository{
        return logInRepository
    }


    fun provideDishesRepository():DishesRepository{
        return dishesRepository
    }


    fun provideCategoriesRepository():CategoriesRepository{
        return categoriesRepository
    }

    fun provideSocketManager():SocketManager{
        return socketManager
    }

    fun instance():DataInjector= this
}