package com.emedinaa.kotlinapp.model

import java.io.Serializable

data class ContactEntity(val id:Int,val name:String,val phone:String,
                         val email:String,val photo:Int,val group:String):Serializable {
}