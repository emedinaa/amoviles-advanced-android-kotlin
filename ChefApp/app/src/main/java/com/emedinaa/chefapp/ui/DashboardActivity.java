package com.emedinaa.chefapp.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emedinaa.chefapp.R;
import com.emedinaa.chefapp.data.DataInjector;
import com.emedinaa.chefapp.data.callback.DataCallback;
import com.emedinaa.chefapp.data.local.PreferencesHelper;
import com.emedinaa.chefapp.data.remote.model.OrderResponse;
import com.emedinaa.chefapp.data.socket.SocketConstant;
import com.emedinaa.chefapp.data.socket.SocketManager;
import com.emedinaa.chefapp.model.entity.Order;
import com.emedinaa.chefapp.model.entity.User;
import com.emedinaa.chefapp.model.interactors.OrdersRemoteInteractor;
import com.emedinaa.chefapp.ui.adapter.OrderAdapter;
import com.emedinaa.core.helpers.GsonHelper;
import com.emedinaa.core.ui.BasicActivity;
import com.emedinaa.core.ui.decorators.DividerItemDecoration;
import com.emedinaa.core.ui.events.RecyclerClickListener;
import com.emedinaa.core.ui.events.RecyclerTouchListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.socket.emitter.Emitter;

public class DashboardActivity extends BasicActivity {

    private final String APP_PACKAGE="com.emedinaa.chefapp";
    private final String ORDER_CHANNEL_ID=APP_PACKAGE + ".ORDER_CHANNEL";
    private final String APP_CHANEL_ID=APP_PACKAGE + ".ChefApp_CHANNEL";

    protected RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerViewOrders;

    private List<Order> orders;
    private OrdersRemoteInteractor ordersRemoteInteractor;
    private SocketManager socketManager;
    private View rlayLoading;
    private OrderAdapter orderAdapter=null;
    private NotificationChannel notificationChannel=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        socketManager= DataInjector.getInstance().socketManager();
        setUpInteractors();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel= new NotificationChannel(ORDER_CHANNEL_ID,"Order",   NotificationManager.IMPORTANCE_HIGH);
        }

        ui();
        //retrieveOrders();
        startSocket();
    }

    private void setUpInteractors(){
        ordersRemoteInteractor= new OrdersRemoteInteractor();
    }

    private void retrieveOrders(){
        clearAdapter();
        showLoading();
        User user= PreferencesHelper.session(this);
        String clientId = user.getId();
        ordersRemoteInteractor.orders(new DataCallback() {
            @Override
            public void onSuccess(Object object) {
                hideLoading();
                if(object !=null && object instanceof List<?>){
                    List<Order> mOrders= (List<Order>) object;
                    if(mOrders.isEmpty()){
                        empty();
                    }else {
                        renderOrders(mOrders);
                    }
                }else{}
            }

            @Override
            public void onFailure(Exception exception) {
                hideLoading();
                showMessage(exception.getMessage());
            }
        });
    }

    private void renderOrders(List<Order> mOrders){
        this.orders=mOrders;
        orderAdapter= new OrderAdapter(orders);
        recyclerViewOrders.setAdapter(orderAdapter);
    }

    private void empty(){ }

    private void ui(){
        recyclerViewOrders= findViewById(R.id.recyclerViewOrders);
        rlayLoading= findViewById(R.id.rlayLoading);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerViewOrders.setLayoutManager(mLayoutManager);

        recyclerViewOrders.addItemDecoration(new DividerItemDecoration(
                ContextCompat.getDrawable(this,R.drawable.line)));

        //events
        recyclerViewOrders.addOnItemTouchListener(new RecyclerTouchListener(
                this, recyclerViewOrders, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(orders!=null){
                    Order order= orders.get(position);
                    selectOrder(order);
                }
            }

            @Override
            public void onLongClick(View view, int position) { }
        }
        ));
    }

    private void selectOrder(Order order) {
        OrderResponse orderResponse= new OrderResponse();
        orderResponse.setClientId(order.getClientId().getId());
        orderResponse.setClientName(order.getClientId().fullName());
        orderResponse.setPlates(order.getPlates());

        Bundle bundle= new Bundle();
        bundle.putSerializable("ORDER",orderResponse);
        nextActivity(new Intent(this,OrderDetailsActivity.class),bundle,false);
    }

    private void startSocket(){
        if(socketManager.isConnected()){
            socketManager.on(SocketConstant.ON_ORDERS, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject jsonObject= (JSONObject) args[0];
                    final OrderResponse orderResponse=
                            new GsonHelper().jsonToObject(jsonObject.toString(),OrderResponse.class);
                    Log.v("CONSOLE", "orderResponse "+orderResponse.toString());
                    if(orderResponse!=null){
                       runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onSuccess(orderResponse);
                            }
                        });
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onFailure();
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(socketManager!=null){
            socketManager.clearSession();
        }
    }

    private void onSuccess(OrderResponse orderResponse){
        Toast.makeText(this,"Nuevo Pedido :\n"+orderResponse.toString(),Toast.LENGTH_LONG).show();
        String id= orderResponse.getId();
        String title= "Nuevo pedido "+id;
        String content= "De "+orderResponse.getClientName();
        addNotification(orderResponse,id,title,content);
    }

    private void onFailure(){
        Toast.makeText(this,"Ocurrió un error",Toast.LENGTH_LONG).show();
    }

    private void addNotification(OrderResponse orderResponse,String id,String title, String content){
        // Create an explicit intent for an Activity in your app
        Bundle bundle= new Bundle();
        bundle.putSerializable("ORDER",orderResponse);
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, ORDER_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(100, mBuilder.build());
    }

    private void clearAdapter(){
        orderAdapter= new OrderAdapter(new ArrayList<Order>());
        recyclerViewOrders.setAdapter(orderAdapter);
    }
    private void showLoading(){
        this.rlayLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        this.rlayLoading.setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //overridePendingTransition(0,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveOrders();
    }
}
