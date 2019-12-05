package com.emedinaa.kotlinapp.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.model.ContactEntity

class ContactsActivity : AppCompatActivity(), OnContactListener {

    private lateinit var contactsFragment: ContactsFragment
    private lateinit var contactDetailFragment: ContactDetailFragment
    private lateinit var fragmentManager: FragmentManager

    override fun onSendMessage(msg: String) {

    }

    override fun selectedItemContact(contactEntity: ContactEntity) {
        contactDetailFragment.renderContact(contactEntity)
    }

    override fun renderFirst(contactEntity: ContactEntity?) {
        contactEntity?.let {
            selectedItemContact(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        fragmentManager= supportFragmentManager
        contactsFragment= fragmentManager.findFragmentById(R.id.fragContacts) as ContactsFragment
        contactDetailFragment= fragmentManager.findFragmentById(R.id.fragContactDetail) as ContactDetailFragment
    }


}
