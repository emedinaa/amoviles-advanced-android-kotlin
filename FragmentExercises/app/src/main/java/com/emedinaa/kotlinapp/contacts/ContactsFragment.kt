package com.emedinaa.kotlinapp.contacts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.adapters.ContactAdapter
import com.emedinaa.kotlinapp.model.ContactEntity
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment: Fragment(){

    private var listener :OnContactListener?=null

    private  var contacts = mutableListOf<ContactEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnContactListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnContactListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setData()
        context?.let {
            lstContacts.adapter= ContactAdapter(it,contacts)
        }

        lstContacts.setOnItemClickListener { _, _, i, _ ->
            listener?.let {
                it.selectedItemContact(contacts[i])
            }
        }

        listener?.renderFirst(first())

    }

    private fun setData(){
        val contact1 = ContactEntity(1,"Pedro Palotes","92835056","pedro@gmail.com",
            R.mipmap.img001,"Personal")

        val contact2 = ContactEntity(2,"Carlos Palotes","96859685","carlos@gmail.com",
            R.mipmap.img002,"Familia")

        val contact3 = ContactEntity(3,"Jose Mendoza","97457434","jose@gmail.com",
            R.mipmap.img003,"Trabajo")

        contacts.add(contact1)
        contacts.add(contact2)
        contacts.add(contact3)
    }


    private fun first(): ContactEntity? {
        contacts?.let {
            return it[0]
        }
        return null
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}