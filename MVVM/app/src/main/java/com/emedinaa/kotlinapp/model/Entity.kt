package com.emedinaa.kotlinapp.model

import java.io.Serializable

data class User(val id:String ,val username:String,
                val firstname:String,val lastname:String): Serializable
