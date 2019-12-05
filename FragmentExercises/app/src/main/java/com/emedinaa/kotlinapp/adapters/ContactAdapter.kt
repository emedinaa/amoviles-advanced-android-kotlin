package com.emedinaa.kotlinapp.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.emedinaa.kotlinapp.model.ContactEntity
import android.widget.TextView
import android.view.LayoutInflater
import android.widget.ImageView
import com.emedinaa.kotlinapp.R

class ContactAdapter(private val context:Context,private val contacts:List<ContactEntity>) :BaseAdapter(){

    override fun getCount(): Int= contacts.size

    override fun getItem(position: Int)= contacts[position]

    override fun getItemId(p0: Int):Long =0

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val container = inflater.inflate(R.layout.row_contact, null)
        val imgContact = container.findViewById<ImageView>(R.id.iviContact)
        val tviName = container.findViewById<TextView>(R.id.tviName)

        //Extraer la entidad
        val contactEntity = contacts[position]

        //Asociar la entidad con el XML
        tviName.text=contactEntity.name
        imgContact.setImageResource(contactEntity.photo)

        return container
    }
}