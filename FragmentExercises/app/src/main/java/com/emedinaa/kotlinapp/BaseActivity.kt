package com.emedinaa.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle


abstract class BaseActivity:AppCompatActivity() {

    protected fun next(activityClass: Class<*>, bundle: Bundle?, destroy: Boolean) {
        val intent = Intent(this, activityClass)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        if(destroy){
            finish()
        }
    }
}