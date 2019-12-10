package com.emedinaa.chefapp.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emedinaa.chefapp.R;
import com.emedinaa.chefapp.data.remote.model.OrderResponse;
import com.emedinaa.chefapp.model.entity.OrderViewHeader;
import com.emedinaa.chefapp.model.entity.OrderViewType;
import com.emedinaa.chefapp.model.entity.Plate;
import com.emedinaa.chefapp.ui.adapter.PlateAdapter;
import com.emedinaa.core.ui.BasicActivity;
import com.emedinaa.core.ui.decorators.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends BasicActivity {

    private RecyclerView recyclerViewPlates;
    private TextView textViewClient;
    private List<Plate> plates;

    protected RecyclerView.LayoutManager mLayoutManager;
    private OrderResponse orderResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        checkExtras();
        ui();
        populate();
    }

    private void ui(){
        enableBack();
        recyclerViewPlates= findViewById(R.id.recyclerViewPlates);
        textViewClient= findViewById(R.id.textViewClient);

        mLayoutManager = new LinearLayoutManager(this);

        recyclerViewPlates.setLayoutManager(mLayoutManager);
        recyclerViewPlates.addItemDecoration(new DividerItemDecoration(
                ContextCompat.getDrawable(this,R.drawable.line_order)));
    }

    private void checkExtras(){
        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle= getIntent().getExtras();
            orderResponse= (OrderResponse) bundle.getSerializable("ORDER");
        }
    }

    private void populate(){
        if(orderResponse!=null){
            textViewClient.setText("Cliente :\n"+orderResponse.getClientName());
            plates= orderResponse.getPlates();

            List<OrderViewType> tmp= new ArrayList<>();
            tmp.add(new OrderViewHeader());
            tmp.addAll(plates);

            recyclerViewPlates.setAdapter(new PlateAdapter(tmp));
        }
    }
}
