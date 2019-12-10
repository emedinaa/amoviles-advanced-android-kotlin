package com.emedinaa.kotlinapp.utils

import com.google.gson.GsonBuilder
import org.json.JSONObject

class GsonHelper {

    fun objectToJSON(obj: Any): JSONObject? {
        val gsonb = GsonBuilder()
        val gson = gsonb.create()
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(gson.toJson(obj))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return jsonObject
    }

    fun <T> jsonToObject(json: String, cls: Class<T>): T {

        val gsonb = GsonBuilder()
        val gson = gsonb.create()
        return gson.fromJson(json, cls)
    }
}