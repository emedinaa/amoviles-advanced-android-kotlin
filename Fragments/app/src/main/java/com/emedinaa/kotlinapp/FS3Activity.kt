package com.emedinaa.kotlinapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.fragments.FragmentS3

//class FS3Activity : AppCompatActivity(){
class FS3Activity : AppCompatActivity() , FragmentS3.OnFragmentInteractionListener{


    /*
      Caused by: android.view.InflateException: Binary XML file line #9: Binary XML file line #9: Error inflating class fragment
     Caused by: android.view.InflateException: Binary XML file line #9: Error inflating class fragment
     Caused by: java.lang.RuntimeException: com.emedinaa.kotlinapp.FS3Activity@76995c4 must implement OnFragmentInteractionListener
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fs3)
    }

    override fun callParent(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}
