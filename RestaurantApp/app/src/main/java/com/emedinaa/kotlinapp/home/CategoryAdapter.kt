package com.emedinaa.kotlinapp.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emedinaa.kotlinapp.model.Category
import com.emedinaa.kotlinapp.R

class CategoryAdapter(var categoryList:List<Category>):RecyclerView.Adapter<CategoryAdapter.Companion.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_category,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = categoryList[position]
        holder?.textViewName?.text=entity.name
    }

    override fun getItemCount(): Int = categoryList.size

    fun update(mCategoryList: List<Category>){
        this.categoryList= mCategoryList
        notifyDataSetChanged()
    }

    companion object {
        class ViewHolder(v:View ):RecyclerView.ViewHolder(v){
            val textViewName:TextView=v.findViewById(R.id.textViewName)
        }
    }
}