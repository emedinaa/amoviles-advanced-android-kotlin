package com.emedinaa.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.adapters.ScreenSlideAdapter
import com.emedinaa.kotlinapp.transforms.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {


    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    //val mPager:ViewPager

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    var mPagerAdapter: PagerAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //viewPager.setPageTransformer(true,DepthPageTransformer())
        viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        viewPager.adapter= ScreenSlideAdapter(supportFragmentManager)
    }


    override fun onBackPressed() {
        if(viewPager.currentItem==0){
            super.onBackPressed()
        }else{
            viewPager.currentItem=viewPager.currentItem-1
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
