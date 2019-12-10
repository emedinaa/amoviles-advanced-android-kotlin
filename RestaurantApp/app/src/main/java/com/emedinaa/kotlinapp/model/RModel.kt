package com.emedinaa.kotlinapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserType(val id:String)

data class User(@SerializedName("_id") val id:String?,
                @SerializedName("nombres") val username:String?,
                @SerializedName("apellido_paterno") val lastName:String?,
                @SerializedName("apellido_materno") val mLastName:String?,
                val email:String?, val socketId:String?, val type:UserType?,
                @SerializedName("imagen") val image:String?,
                val token:String?):Serializable

data class Category(@SerializedName("_id") val id:String,
                    @SerializedName("nombre") val name:String,
                    @SerializedName("descripcion") val desc:String):Serializable

data class Dish(@SerializedName("_id") val id:String,
                @SerializedName("idCategoria") val idCategory:String,
                @SerializedName("nombre") val name:String,
                @SerializedName("precio") val price:String,
                @SerializedName("descripcion") val desc:String,
                @SerializedName("imagen") val photo:String,
                @SerializedName("categoria_id") val category: Category?):Serializable

data class Order(val id:Int,
                 val idDish:Int,val name:String,val price:Double, val ammount:Double):Serializable

//data class Cart(val id:String, val userId:String,val orderList: List<Order>)
