package com.emedinaa.kotlinapp.data.socket

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

class SocketMapper {

    private val PARAM_SUCCESS = "success"
    private val PARAM_MESSAGE = "message"

    fun convert(jsonObject: JSONObject?): SocketResponse {
        var socketResponse:SocketResponse? = null
        if (jsonObject == null) return SocketResponse(false,"")

        var success = false
        var message = ""
        try {
            success = jsonObject?.getBoolean(PARAM_SUCCESS)
            message = jsonObject?.getString(PARAM_MESSAGE)
        } catch (e: JSONException) {
            Log.e("CONSOLE", e.message)
        }
        socketResponse= SocketResponse(success,message)
        return socketResponse
    }
}