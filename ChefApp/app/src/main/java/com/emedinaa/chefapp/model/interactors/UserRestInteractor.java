package com.emedinaa.chefapp.model.interactors;


import androidx.annotation.NonNull;

import com.emedinaa.chefapp.data.callback.DataCallback;
import com.emedinaa.chefapp.data.remote.model.LogInRaw;
import com.emedinaa.chefapp.data.remote.model.LogInResponse;
import com.emedinaa.chefapp.model.entity.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/11/18
 */
public class UserRestInteractor extends BaseRemoteInteractor<LogInResponse> {

    public void logIn(@NonNull String email, @NonNull String  password,
                      @NonNull final DataCallback dataCallback) {
        if (remote == null) return;

        currentCall = remote.logIn(new LogInRaw(email, password));

        currentCall.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if(response ==null){
                    dataCallback.onFailure(new Exception(DEFAULT_MSG_ERROR));
                }
                if(response.isSuccessful() && response.body()!=null){
                    LogInResponse logInResponse=response.body();
                    if(logInResponse!=null && logInResponse.getData()!=null){
                        User user= logInResponse.getData();
                        dataCallback.onSuccess(user);
                    }else{

                    }
                }else {
                    dataCallback.onFailure(new Exception(response.message()));
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                processFailure(call, t, dataCallback);
            }
        });
    }
}
