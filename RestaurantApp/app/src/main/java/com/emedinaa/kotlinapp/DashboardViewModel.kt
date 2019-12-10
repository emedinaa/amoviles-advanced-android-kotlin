package com.emedinaa.kotlinapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel:ViewModel() {

    val cartNotification = MutableLiveData<Int>()
    val orderAction= MutableLiveData<Boolean>()
}