package com.emedinaa.chefapp.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.emedinaa.chefapp.R;
import com.emedinaa.chefapp.model.entity.Order;
import com.emedinaa.chefapp.model.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author : Eduardo Medina
 * @see : https://github.com/googlesamples/android-RecyclerView/blob/master/Application/src/main/java/com/example/android/recyclerview/CustomAdapter.java
 * @since : 8/10/18
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private final static String TAG="CATEGORYADAP";

    private List<Order> orders;
    private SimpleDateFormat stringFormat= new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");

    public OrderAdapter(List<Order> mOrders) {
        this.orders = mOrders;
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_orders, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Log.d(TAG, "Element " + position + " set.");

        final Order order= orders.get(position);
        User user= order.getClientId();
        if(user!=null){
            viewHolder.getTextViewName().setText(user.fullName());
            viewHolder.getTextViewDate().setText(format(order.getRegDate()));
        }else{
            viewHolder.getTextViewName().setText("");
        }
    }

    private String format(String date){
        Date ndate= new Date();
        try {
            ndate =stringFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sDate= dateFormat.format(ndate);
        return sDate;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewClientName;
        private final TextView textViewDate;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            /*v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });*/
            textViewClientName =  v.findViewById(R.id.textViewClientName);
            textViewDate =  v.findViewById(R.id.textViewDate);
        }

        public TextView getTextViewName() {
            return textViewClientName;
        }
        public TextView getTextViewDate() {
            return textViewDate;
        }
    }
}
