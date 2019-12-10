package com.emedinaa.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var user: User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validateExtras()

        populate()
    }

    private fun  populate(){
        textView.text= "Welcome ${user?.firstname}".plus(" ${user?.lastname}")
    }

    private fun validateExtras(){
       intent?.extras?.let {
           if(it.containsKey("USER") && it.getSerializable("USER") is User ){
               user= it.getSerializable("USER") as User
           }
       }
    }
}
