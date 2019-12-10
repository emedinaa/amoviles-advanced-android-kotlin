package com.emedinaa.chefapp.data.remote;

import com.emedinaa.chefapp.data.remote.model.LogInRaw;
import com.emedinaa.chefapp.data.remote.model.LogInResponse;
import com.emedinaa.chefapp.data.remote.model.PlateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/4/18
 */
public interface KitchenEndPoint {

    @POST("/auth/usuarios-login")
    Call<LogInResponse> logIn(@Body LogInRaw logInRaw);

    //https://diplomado-restaurant-backend.herokuapp.com/ordenes-de-compra
    @GET("/api/ordenes-de-compra")
    Call<PlateResponse> orders_(@Header("Authorization") String authorization);

    //@GET("/api/ordenes-de-compra/{cliente_id}")
    //@GET("/ordenes-de-compra/{cliente_id}")
    @GET("/ordenes-de-compra")
    Call<PlateResponse> orders();
    //Call<PlateResponse> orders(@Path("cliente_id") String clientId);
}

