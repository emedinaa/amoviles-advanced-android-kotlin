package com.emedinaa.chefapp.model.interactors;


import androidx.annotation.NonNull;

import com.emedinaa.chefapp.data.callback.DataCallback;
import com.emedinaa.chefapp.data.remote.model.PlateResponse;
import com.emedinaa.chefapp.model.entity.Order;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class OrdersRemoteInteractor extends BaseRemoteInteractor<PlateResponse>{

    public void orders(@NonNull final DataCallback dataCallback){
        if(remote==null)return;

        currentCall= remote.orders();
        currentCall.enqueue(new Callback<PlateResponse>() {
            @Override
            public void onResponse(Call<PlateResponse> call, Response<PlateResponse> response) {
                if(response ==null){
                    dataCallback.onFailure(new Exception(DEFAULT_MSG_ERROR));
                }
                if(response.isSuccessful() && response.body()!=null){
                    PlateResponse plateResponse=response.body();
                    if(plateResponse!=null && plateResponse.getData()!=null){
                        List<Order> categories= plateResponse.getData();
                        dataCallback.onSuccess(categories);
                    }else{
                        dataCallback.onFailure(new Exception(DEFAULT_MSG_ERROR));
                    }
                }else{
                    dataCallback.onFailure(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<PlateResponse> call, Throwable t) {
                processFailure(call,t,dataCallback);
                /*if(call!=null){
                    if(call.isCanceled()){ }else{
                        dataCallback.onFailure(new Exception(t));
                    }
                }*/
            }
        });
    }
}
