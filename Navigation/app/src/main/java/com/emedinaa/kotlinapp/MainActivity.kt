package com.emedinaa.kotlinapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val onClickListener= View.OnClickListener {
        var mActivity:Class<*>?=null
        when(it.id){
            R.id.button1 -> mActivity= BottomNavigationActivity::class.java
            R.id.button2 -> mActivity= NavigationDrawerActivity::class.java
            R.id.button3 -> mActivity= SwipeTabsActivity::class.java
            R.id.button4 -> mActivity= ViewPagerActivity::class.java
        }
        mActivity?.let {
            goToView(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener(onClickListener)
        button2.setOnClickListener(onClickListener)
        button3.setOnClickListener(onClickListener)
        button4.setOnClickListener(onClickListener)
    }

    private fun goToView(mActivity:Class<*>){
        startActivity(Intent(this,mActivity))
    }
}
