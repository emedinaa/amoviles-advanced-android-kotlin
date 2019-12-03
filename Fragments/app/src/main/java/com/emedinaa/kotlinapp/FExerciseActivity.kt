package com.emedinaa.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.fragments.BottomBarFragment
import com.emedinaa.kotlinapp.fragments.BoxFragment
import com.emedinaa.kotlinapp.listeners.ColorListener

class FExerciseActivity : AppCompatActivity() , ColorListener{
//class FExerciseActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private var  bottomBarFragment: BottomBarFragment?=null
    private var  boxFragment: BoxFragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fexercise)

        fragmentManager = supportFragmentManager

        if (fragmentManager.findFragmentById(R.id.fragBottom) is BottomBarFragment) {
            bottomBarFragment= fragmentManager.findFragmentById(R.id.fragBottom) as BottomBarFragment
        }

        if (fragmentManager.findFragmentById(R.id.fragBox) is BoxFragment) {
            boxFragment= fragmentManager.findFragmentById(R.id.fragBox) as BoxFragment
        }
    }

    /*override fun onColorSelected(color: Int) {
        boxFragment?.paintBox(color)
    }*/

    override fun onColorSelected(position: Int) {
        boxFragment?.paintColor(position)
    }
}
