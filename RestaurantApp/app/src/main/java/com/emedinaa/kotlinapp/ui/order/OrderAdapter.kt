package com.emedinaa.kotlinapp.order

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.emedinaa.kotlinapp.model.OrderViewType
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.emedinaa.kotlinapp.R
import com.emedinaa.kotlinapp.model.OrderViewFooter
import com.emedinaa.kotlinapp.model.Item


class OrderAdapter(var data:List<OrderViewType>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
       const val HEADER = 0
       const val ITEM = 1
        const val FOOTER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  when(viewType){
            HEADER -> HeaderViewHolder(inflater.inflate(R.layout.row_cart_header,parent,false))
            ITEM -> ItemViewHolder(inflater.inflate(R.layout.row_cart_item,parent,false))
            FOOTER -> FooterViewHolder(inflater.inflate(R.layout.row_cart_footer,parent,false))
            else -> ItemViewHolder(inflater.inflate(R.layout.row_cart_item,parent,false))
        }
    }

    override fun onBindViewHolder(vh: RecyclerView.ViewHolder, position: Int) {
        when(vh.itemViewType){
            HEADER -> renderHeader(vh as HeaderViewHolder,position)
            ITEM -> renderItem(vh as ItemViewHolder,position)
            FOOTER -> renderFooter(vh as FooterViewHolder,position)
            else -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            data[position].isHeader() -> HEADER
            data[position].isItem() -> ITEM
            data[position].isFooter() -> FOOTER
            else -> -1
        }
    }
    override fun getItemCount(): Int = data.size

    private fun renderFooter(footerViewHolder: FooterViewHolder, position: Int) {
        val orderViewFooter = data[position] as OrderViewFooter
        orderViewFooter?.let {
            val total= "%.2f".format(it.total)
            footerViewHolder.textViewTotal.text = "S/.$total"
        }
    }

    private fun renderHeader(headerViewHolder: HeaderViewHolder, position: Int){}

    private fun renderItem(itemViewHolder: ItemViewHolder, position: Int) {
        val item = data[position] as Item
        item?.let {
            val price= "%.2f".format(it.price)
            val total= "%.2f".format(it.total())

            itemViewHolder.textViewAmount.text=it.amount.toString()
            itemViewHolder.textViewName.text= it.name
            itemViewHolder.textViewPrice.text = "S/.$price"
            itemViewHolder.textViewTotal.text = "S/.$total"
        }
    }

    fun update(nData:List<OrderViewType>){
        data= nData
        notifyDataSetChanged()
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textViewAmount: TextView =view.findViewById(R.id.textViewAmount)
        val textViewName: TextView =view.findViewById(R.id.textViewName)
        val textViewPrice: TextView =view.findViewById(R.id.textViewPrice)
        val textViewTotal: TextView =view.findViewById(R.id.textViewTotal)
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTotal: TextView =view.findViewById(R.id.textViewTotal)
    }
}