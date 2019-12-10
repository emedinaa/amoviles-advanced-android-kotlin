package com.emedinaa.chefapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.emedinaa.chefapp.R;
import com.emedinaa.chefapp.data.remote.model.OrderResponse;
import com.emedinaa.core.ui.BasicActivity;

public class NotificationActivity extends BasicActivity {

    private OrderResponse orderResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        checkExtras();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToOrderDetails();
            }
        });

        populate();
    }


    private void populate(){
        if(orderResponse!=null){
            StringBuilder stringBuilder= new StringBuilder();
            stringBuilder.append("Orden "+orderResponse.getId()+"\n");
            stringBuilder.append("De :"+orderResponse.getClientName());
            ((TextView)findViewById(R.id.textView)).setText(stringBuilder.toString());
        }
    }
    private void goToOrderDetails() {

        Bundle bundle= new Bundle();
        bundle.putSerializable("ORDER",orderResponse);
        nextActivity(new Intent(this,OrderDetailsActivity.class),
                bundle,true);
    }

    private void checkExtras(){
        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle= getIntent().getExtras();
            orderResponse= (OrderResponse) bundle.getSerializable("ORDER");
        }
    }

}
