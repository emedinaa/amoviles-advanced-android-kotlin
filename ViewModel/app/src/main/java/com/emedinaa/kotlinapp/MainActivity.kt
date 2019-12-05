package com.emedinaa.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    /*
    https://kotlinlang.org/docs/reference/scope-functions.html
     */
    private lateinit var viewModel: ColorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ColorViewModel::class.java)

        //observers

        viewModel.colorSelected.observe(this, Observer<Int> {
            Log.v("CONSOLE", "activity $it")
        })
    }
}
