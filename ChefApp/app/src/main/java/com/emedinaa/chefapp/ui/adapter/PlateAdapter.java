package com.emedinaa.chefapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emedinaa.chefapp.R;
import com.emedinaa.chefapp.model.entity.OrderViewType;
import com.emedinaa.chefapp.model.entity.Plate;

import java.util.List;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class PlateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderViewType> objects;

    private final int HEADER = 0;
    private final int ITEM = 1;

    public PlateAdapter(List<OrderViewType> objects) {
        this.objects = objects;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case HEADER:
                View rowHeaderView = inflater.inflate(R.layout.row_cart_header, parent, false);
                viewHolder = new HeaderViewHolder(rowHeaderView);
                break;
            case ITEM:
                View rowItemView = inflater.inflate(R.layout.row_cart_item, parent, false);
                viewHolder = new ItemViewHolder(rowItemView);
                break;
            default:
                View defaultView = inflater.inflate(R.layout.row_cart_item, parent, false);
                viewHolder = new ItemViewHolder(defaultView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                renderHeader(headerViewHolder, position);
                break;
            case ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                renderItem(itemViewHolder, position);
                break;
            default:
                ItemViewHolder defaultViewHolder = (ItemViewHolder) holder;
                renderItem(defaultViewHolder, position);
                break;
        }
    }

    private void renderHeader(HeaderViewHolder headerViewHolder, int position){

    }

    private void renderItem(ItemViewHolder itemViewHolder, int position){
        Plate item= (Plate) objects.get(position);
        if(item!=null){
            itemViewHolder.textViewAmount.setText(String.valueOf(item.getAmount()));
            itemViewHolder.textViewName.setText(item.getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (objects.get(position).isHeader()) {
            return HEADER;
        } else if (objects.get(position).isItem()) {
            return ITEM;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return this.objects.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewAmount;
        public TextView textViewName;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textViewAmount=  itemView.findViewById(R.id.textViewAmount);
            textViewName=  itemView.findViewById(R.id.textViewName);
        }
    }
    class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
