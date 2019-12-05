package com.emedinaa.kotlinapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorViewModel:ViewModel() {

    val colorSelected = MutableLiveData<Int>()

    fun selectedColor(color: Int) {
        colorSelected.value = color
    }
}