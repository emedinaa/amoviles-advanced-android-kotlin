package com.kotlin.samples.kotlinapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.emedinaa.kotlinapp.fragments.AFragment
import com.emedinaa.kotlinapp.fragments.BFragment
import com.emedinaa.kotlinapp.fragments.CFragment

class TabsPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment? {
        when(position){
            0 -> return AFragment.newInstance("","")
            1 -> return BFragment.newInstance("","")
            2 -> return CFragment.newInstance("","")
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Section ${position+1}"
    }
}