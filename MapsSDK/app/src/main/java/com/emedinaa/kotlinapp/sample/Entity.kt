package com.emedinaa.kotlinapp.sample

class Place(val lat:Double,val lng:Double, val name:String?){
    override fun toString(): String {
        return "Place(lat=$lat, lng=$lng, name=$name)"
    }
}