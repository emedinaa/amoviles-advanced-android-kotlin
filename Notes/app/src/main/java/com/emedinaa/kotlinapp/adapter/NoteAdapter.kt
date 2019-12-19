package com.emedinaa.kotlinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emedinaa.kotlinapp.model.Note
import com.emedinaa.kotlinapp.R

class NoteAdapter(private var notes:List<Note>, val itemAction:(note: Note)->Unit ): RecyclerView.Adapter<NoteAdapter.Companion.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.row_note,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title= notes[position].name
        holder.tviName.text=title?.capitalize()
        holder.itemView.setOnClickListener {
            itemAction(notes[position])
        }
    }

    fun update( noteList:List<Note>){
        this.notes= noteList
        notifyDataSetChanged()
    }

    companion object {
        class ViewHolder(v:View ):RecyclerView.ViewHolder(v){
            val iviNote:ImageView=v.findViewById(R.id.imageViewNote)
            val tviName:TextView= v.findViewById(R.id.tviName)
        }
    }
}