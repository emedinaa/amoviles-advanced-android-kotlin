package com.emedinaas.kotlinapp.sample

import com.emedinaa.kotlinapp.sample.Place

object Markers {

    private val data:MutableList<Place> = mutableListOf()

    fun addPlace(place:Place){
        data.add(place)
    }

    fun places():List<Place>{
        return data
    }
}