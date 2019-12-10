package com.emedinaa.chefapp.model.interactors;


import androidx.annotation.NonNull;

import com.emedinaa.chefapp.data.DataInjector;
import com.emedinaa.chefapp.data.callback.DataCallback;
import com.emedinaa.chefapp.data.remote.KitchenEndPoint;

import retrofit2.Call;

/**
 * @author : Eduardo Medina
 * @see : https://futurestud.io/tutorials/retrofit-2-cancel-requests
 * @since : 8/10/18
 */
public class BaseRemoteInteractor<T> {

    protected final String DEFAULT_MSG_ERROR="Ocurrió un error";
    protected Call<T> currentCall=null;
    protected KitchenEndPoint remote;

    public BaseRemoteInteractor() {
        remote= DataInjector.getInstance().remote().restaurantEndPoint();
    }

    protected void cancel(){
        if(currentCall!=null){
            currentCall.cancel();
        }
    }

    protected void processFailure(Call<T> call, Throwable t,
                                  @NonNull DataCallback dataCallback){
        if(call!=null){
            if(call.isCanceled()){ }else{
                dataCallback.onFailure(new Exception(t));
            }
        }
    }
}
