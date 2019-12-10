package com.emedinaa.kotlinapp.dish

import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emedinaa.kotlinapp.model.Category
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.model.Dish
import com.emedinaa.kotlinapp.utils.ImageLoader
import com.squareup.picasso.Picasso

class DishAdapter(var categoryList:List<Dish>,val imageLoader: ImageLoader):RecyclerView.Adapter<DishAdapter.Companion.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent?.context)
            .inflate(R.layout.row_dish,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = categoryList[position]
        holder?.textViewTitle?.text=entity.name
        holder?.textViewDesc?.text=entity.desc
        holder?.textViewPrice?.text=formatPrice(entity.price)

        //imagen imageViewDish
        //dominiobackend.com/images/platos/nombreimagen
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
        //"https://diplomado-restaurant-backend.herokuapp.com/images/platos/${entity.photo}"

        imageLoader.load("https://diplomado-restaurant-backend.herokuapp.com/images/platos/${entity.photo}",
            holder?.imageViewDish)
    }

    override fun getItemCount(): Int = categoryList.size

    fun update(data:List<Dish>){
        categoryList= data
        notifyDataSetChanged()
    }

    private fun formatPrice(price:String):SpannableString{
        val fPrice = SpannableString("S/.$price")
        fPrice.setSpan(SuperscriptSpan(), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        fPrice.setSpan(RelativeSizeSpan(0.5f), 0, 3, 0) // set size
        return fPrice
    }

    companion object {
        class ViewHolder(v:View ):RecyclerView.ViewHolder(v){
            val imageViewDish:ImageView=v.findViewById(R.id.imageViewDish)
            val textViewTitle:TextView=v.findViewById(R.id.textViewTitle)
            val textViewDesc:TextView=v.findViewById(R.id.textViewDesc)
            val textViewPrice:TextView=v.findViewById(R.id.textViewPrice)
        }
    }
}