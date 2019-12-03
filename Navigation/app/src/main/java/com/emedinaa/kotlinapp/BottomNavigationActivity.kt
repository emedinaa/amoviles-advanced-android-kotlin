package com.emedinaa.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.fragments.AFragment
import com.emedinaa.kotlinapp.fragments.BFragment
import com.emedinaa.kotlinapp.fragments.CFragment
import kotlinx.android.synthetic.main.activity_bottom_navigation.*


class BottomNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bottomNavigationView.setOnNavigationItemSelectedListener {

            var fragment:Fragment?=null

            when(it.itemId){
                R.id.action_favorites -> fragment= AFragment.newInstance("","")
                R.id.action_schedules -> fragment= BFragment.newInstance("","")
                R.id.action_music -> fragment= CFragment.newInstance("","")
            }

            fragment?.let {
                changeFragment(it)
            }
            return@setOnNavigationItemSelectedListener true
        }

        //first tab
        changeFragment(AFragment.newInstance("",""))
    }


    private fun changeFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout,fragment,null)
            commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
