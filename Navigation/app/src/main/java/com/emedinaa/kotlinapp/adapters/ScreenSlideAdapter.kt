package com.emedinaa.kotlinapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.emedinaa.kotlinapp.fragments.ScreenSlideFragment

/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */

class ScreenSlideAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    companion object {
        /**
         * The number of pages (wizard steps) to show in this demo.
         */
        const val NUM_PAGES=5
    }

    override fun getItem(p0: Int): Fragment {
        return ScreenSlideFragment()
    }

    override fun getCount(): Int {
        return NUM_PAGES
    }
}