package com.emedinaa.kotlinapp.model

import com.emedinaa.kotlinapp.utils.Logger
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

object Cart {

    private var orderId: String?=null
    private var userId: String=""
    private var items:MutableList<Item> = mutableListOf()

    fun createOrder(mUserId: String) {
        orderId = UUID.randomUUID().toString()
        userId = mUserId
        items = mutableListOf()
    }

    fun addItem(item: Item) {
        item.order=(orderId)
        items.add(item)
    }

    fun removeItem(item: Item) {
        items.remove(item)
    }

    fun getItems(): List<Item> {
        return items
    }

    fun isEmpty(): Boolean= items.isEmpty()

    fun clear() {
        orderId = null
        items = mutableListOf()
    }

    fun total(): Double {
        var amountTotal = 0.0
        for (item in items) {
            amountTotal += item.total()
            Logger.d( "item ${item.name} amount  ${item.amount} price  + ${item.price}")
        }
        return amountTotal
    }

    fun makeOrder(): JSONObject {
        val obj = JSONObject()
        try {
            obj.put("cliente_id", userId)
            var objItem: JSONObject? = null
            val jsonArray = JSONArray()
            for (item in items) {
                objItem = JSONObject()
                objItem.put("plato_id", item.dishId)
                objItem.put("nombre", item.name)
                objItem.put("precio", item.price)
                objItem.put("cantidad", item.amount)
                jsonArray.put(objItem)
            }
            obj.put("orden", jsonArray)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return obj
    }

    class CardMapper {
        fun convert(dish: Dish?): Item {
            var item:Item?
            if (dish == null) return Item("",0,"","",0.0)
            item = Item("",0,dish.id,dish.name,dish.price.toDouble())
            return item
        }
    }
}