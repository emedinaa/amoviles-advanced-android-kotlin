package com.emedinaa.kotlinapp.tabs

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emedinaa.kotlinapp.R
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.graphics.Typeface
import kotlinx.android.synthetic.main.activity_tab_navigation.*


class TabNavigationActivity : AppCompatActivity() ,OnTabNavListener,View.OnClickListener{

    private val FRAGMENT_WEB = 0
    private val FRAGMENT_SHOPPING = 1
    private val FRAGMENT_VIDEOS = 2

    private val indicatorViews= mutableListOf<View>()
    private val titleViews= mutableListOf<TextView>()
    private var currentIndicator = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_navigation)

        indicatorViews.add(iviWeb)
        indicatorViews.add(iviShopping)
        indicatorViews.add(iviVideos)

        titleViews.add(tviWeb)
        titleViews.add(tviShopping)
        titleViews.add(tviVideos)

        titleViews.forEach {
            it.setOnClickListener(this)
        }

        selectFirst()
    }

    override fun onClick(view: View?) {
        val bundle = Bundle()

        var fragmentId = when(view?.id){
            R.id.tviWeb->  FRAGMENT_WEB
            R.id.tviShopping->  FRAGMENT_SHOPPING
            R.id.tviVideos->  FRAGMENT_VIDEOS

            else -> FRAGMENT_WEB
        }

        updateUI(fragmentId);
        changeFragment(bundle,fragmentId)
    }

    private fun selectFirst() {
        val bundle = Bundle()
        val fragmentId = FRAGMENT_WEB
        updateUI(fragmentId)
        changeFragment(bundle, fragmentId)
    }

    private fun updateUI(fragmentId: Int) {
        if (currentIndicator >= 0) {
            indicatorViews[currentIndicator].setBackgroundColor(Color.TRANSPARENT)
            titleViews[currentIndicator].inputType = Typeface.NORMAL
        }
        indicatorViews[fragmentId].setBackgroundColor(Color.parseColor("#ffeb3b"))
        titleViews[fragmentId].inputType = Typeface.BOLD
        currentIndicator = fragmentId
    }


    private fun changeFragment(bundle: Bundle, fragmentId: Int) {

        val fragment = factoryFragment(bundle, fragmentId)
        //fragment.setArguments(bundle);

        fragment?.let {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flayContainer, it)
            transaction.addToBackStack(null)

            // Commit the transaction
            transaction.commit()
        }
    }

    private fun factoryFragment(bundle: Bundle, fragmentId: Int): Fragment? {
        when (fragmentId) {
            FRAGMENT_WEB -> return WebFragment()
            FRAGMENT_SHOPPING -> return ShoppingFragment()
            FRAGMENT_VIDEOS -> return VideosFragment()
        }
        return null
    }
}
