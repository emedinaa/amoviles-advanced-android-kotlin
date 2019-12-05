package com.emedinaa.kotlinapp.cards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.emedinaa.kotlinapp.R
import kotlinx.android.synthetic.main.activity_cards.*


class CardsActivity : AppCompatActivity(), OnCardListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        btnReset.setOnClickListener {
            clearFragments()
            populateCards()
        }

        populateCards()
    }

    private fun populateCards() {

        for (i in 0..4) {
            addFragment()
        }
    }

    private fun clearFragments() {
        for (fragment in supportFragmentManager.fragments) {
            removeFragment(fragment)
        }
    }

    private fun addFragment() {

        val cardFragment = CardFragment()
        val args = Bundle()
        cardFragment.arguments=args

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.flayContainer, cardFragment)
        transaction.addToBackStack(null)

        // Commit the transaction
        transaction.commit()
    }

    private fun removeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.remove(fragment)
        transaction.commit()
    }
    override fun removeCard(fragment: Fragment) {
        removeFragment(fragment)
    }
}
