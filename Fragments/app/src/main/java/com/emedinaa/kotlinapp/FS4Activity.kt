package com.emedinaa.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.fragments.FragmentS4
import kotlinx.android.synthetic.main.activity_fs4.*

class FS4Activity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private var  fragment: FragmentS4?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fs4)

        fragmentManager= supportFragmentManager
        if(fragmentManager.findFragmentById(R.id.fragment) is FragmentS4){
            fragment= fragmentManager.findFragmentById(R.id.fragment) as FragmentS4
        }

        button.setOnClickListener {
            fragment?.showMessage("Action from Activity!")
        }
    }
}
