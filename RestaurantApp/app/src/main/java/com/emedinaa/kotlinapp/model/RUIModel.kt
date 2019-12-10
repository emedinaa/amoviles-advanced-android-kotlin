package com.emedinaa.kotlinapp.model

open  class OrderViewType(var type:Int=0) {

    open fun isHeader():Boolean=false

    open fun  isItem(): Boolean= false

    open fun isFooter(): Boolean=false
}

class OrderViewHeader:OrderViewType(){
    override fun isHeader(): Boolean = true
}

class OrderViewFooter(val total:Double):OrderViewType(){
    override fun isFooter(): Boolean = true
}

data class Item(var order:String?,var amount:Int,val dishId:String, val name:String,val price:Double):OrderViewType(){

    override fun isItem(): Boolean = true
    fun total():Double =amount*price
    override fun toString(): String {
        return "Item(order=$order, amount=$amount, dishId='$dishId', name='$name', price=$price, total=${total()})"
    }
}