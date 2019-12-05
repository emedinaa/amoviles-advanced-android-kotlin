package com.emedinaa.kotlinapp.contacts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.model.ContactEntity
import kotlinx.android.synthetic.main.fragment_contact_detail.*


class ContactDetailFragment: Fragment() {

    private var listener :OnContactListener?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false)
    }


    fun renderContact(contactEntity: ContactEntity) {
        val name = contactEntity.name
        val phone = contactEntity.phone
        val email = contactEntity.email
        val group = contactEntity.group

        tviName.text=name
        tviPhone.text=phone
        tviEmail.text=email
        tviGroup.text =group
        iviContact.setImageResource(contactEntity.photo)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnContactListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnContactListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}