package com.emedinaa.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emedinaa.kotlinapp.cards.CardsActivity
import com.emedinaa.kotlinapp.contacts.ContactsActivity
import com.emedinaa.kotlinapp.tabs.TabNavigationActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        next(ContactsActivity::class.java,null,true)
        //next(TabNavigationActivity::class.java,null,true)
        //next(CardsActivity::class.java,null,true)

    }
}
