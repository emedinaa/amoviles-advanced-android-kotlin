package com.emedinaa.chefapp.data.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/4/18
 */
public class APIClient {
    private final String apiUrl;
    private Retrofit retrofit;
    private KitchenEndPoint endPoint;

    public APIClient(String mApiUrl) {
        this.apiUrl = mApiUrl;
        init();
    }

    private void init(){
        Retrofit.Builder builder =new Retrofit.Builder()
                .baseUrl(this.apiUrl)
                .addConverterFactory(GsonConverterFactory.create());
        OkHttpClient.Builder  httpClient=new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor());

        retrofit = builder.client(httpClient.build()).build();
    }

    public KitchenEndPoint restaurantEndPoint(){
        endPoint= retrofit.create(KitchenEndPoint.class);
        return endPoint;
    }

    private  static HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return httpLoggingInterceptor;
    }
}
