package com.emedinaa.kotlinapp.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emedinaa.kotlinapp.R

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val iviNote= view.findViewById<ImageView>(R.id.imageViewNote)
    val tviName= view.findViewById<TextView>(R.id.tviName)
}