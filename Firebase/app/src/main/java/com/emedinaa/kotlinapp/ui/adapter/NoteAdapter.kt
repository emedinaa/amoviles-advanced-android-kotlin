package com.emedinaa.kotlinapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.model.NoteEntity
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class NoteAdapter(options: FirebaseRecyclerOptions<NoteEntity>) : FirebaseRecyclerAdapter<NoteEntity, NoteViewHolder>(options) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        return NoteViewHolder(inflater.inflate(R.layout.row_note, viewGroup, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int, model: NoteEntity) {
        //val item= getRef(position)
        holder.tviName.text= model.name
    }
}