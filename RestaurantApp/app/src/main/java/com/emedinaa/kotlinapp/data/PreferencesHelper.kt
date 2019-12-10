package com.emedinaa.kotlinapp.data

import android.content.Context
import android.content.SharedPreferences
import com.emedinaa.kotlinapp.model.User
import com.emedinaa.kotlinapp.utils.GsonHelper

object PreferencesHelper {

    private val APP_PREFERENCES = "com.emedinaa.myrestaurant"
    private val PREFERENCES_SESSION = "$APP_PREFERENCES.session"
    private val PREFERENCES_USERNAME = "$APP_PREFERENCES.username"
    private val PREFERENCES_PASSWORD = "$APP_PREFERENCES.password"
    private val PREFERENCES_TOKEN = "$APP_PREFERENCES.token"


    fun signOut(context: Context) {
        val editor = getEditor(context)
        //editor.remove(PREFERENCES_USERNAME);
        //editor.remove(PREFERENCES_PASSWORD);
        editor.clear()
        editor.apply()
    }

    fun saveUser(context: Context, user: User) {
        val editor = getEditor(context)
        editor.putString(PREFERENCES_SESSION, GsonHelper().objectToJSON(user).toString())
        editor.apply()
    }

    fun session(context: Context): User {
        val sharedPreferences = getSharedPreferences(context)
        val userStr = sharedPreferences.getString(PREFERENCES_SESSION, "")
        return GsonHelper().jsonToObject(userStr, User::class.java)
    }

    fun saveSession(context: Context, username: String, token: String) {
        val editor = getEditor(context)
        editor.putString(PREFERENCES_USERNAME, username)
        editor.putString(PREFERENCES_TOKEN, token)
        editor.apply()
    }

    fun getUserSession(context: Context): String? {
        val sharedPreferences = getSharedPreferences(context)

        return sharedPreferences.getString(PREFERENCES_USERNAME, null)
    }

    fun getTokenSession(context: Context): String? {
        val sharedPreferences = getSharedPreferences(context)

        return sharedPreferences.getString(PREFERENCES_TOKEN, null)
    }

    fun isSignedIn(context: Context): Boolean {
        val preferences = getSharedPreferences(context)
        return preferences.contains(PREFERENCES_USERNAME)
    }

    private fun getEditor(context: Context): SharedPreferences.Editor {
        val preferences = getSharedPreferences(context)
        return preferences.edit()
    }

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    }
}