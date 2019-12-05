package com.emedinaa.kotlinapp.contacts

import com.emedinaa.kotlinapp.model.ContactEntity

interface OnContactListener {

    fun onSendMessage(msg: String)
    fun selectedItemContact(contactEntity: ContactEntity)
    fun renderFirst(contactEntity: ContactEntity?)
}