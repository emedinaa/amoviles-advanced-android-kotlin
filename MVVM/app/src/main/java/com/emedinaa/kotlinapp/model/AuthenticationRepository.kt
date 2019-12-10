package com.emedinaa.kotlinapp.model

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.emedinaa.kotlinapp.data.EndPoints
import com.emedinaa.kotlinapp.data.LogInResponse
import com.emedinaa.kotlinapp.data.OperationCallback
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject

class AuthenticationRepository(private val context: Context):AuthenticationDataSource {

    private val TAG = "LoginRequestTAG"

    private val requestQueue= Volley.newRequestQueue(context)
    private val url= EndPoints.logIn()
    private val gson= Gson()

    override fun logIn(username: String, password: String,callback: OperationCallback) {
        val jsonObject = JSONObject()
        jsonObject.put("username",username)
        jsonObject.put("password",password)

        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.POST,url,jsonObject,
            Response.Listener { response ->
                Log.v("CONSOLE",response.toString())
                var jsonObject: JSONObject?
                try {
                    jsonObject= JSONObject(response.toString())
                    val loginResponse: LogInResponse = gson.fromJson(
                        jsonObject.toString(),
                        LogInResponse::class.java
                    )
                    if(loginResponse.isSuccess()){
                        callback.onSuccess(loginResponse.data)
                    }else{
                        callback.onError(loginResponse.msg)
                    }

                }catch (e: JSONException){
                    callback.onError(e.message)
                }
            },
            Response.ErrorListener { error ->
                Log.v("CONSOLE","message ${error.message} responseError ${error.networkResponse.statusCode}")
                val messageError= "error : ${error.networkResponse.statusCode} ".plus("message ${error.message}")
                callback.onError(messageError)
            }
        )
        requestQueue.add(jsonObjectRequest)
    }

    override fun cancel() {
        requestQueue?.cancelAll(TAG)
    }
}