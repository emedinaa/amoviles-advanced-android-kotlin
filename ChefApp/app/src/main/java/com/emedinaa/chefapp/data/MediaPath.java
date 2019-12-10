package com.emedinaa.chefapp.data;


import com.emedinaa.chefapp.BuildConfig;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/17/18
 */
public class MediaPath {

    private final String basePath= BuildConfig.DOMAIN+BuildConfig.IMAGESPATH;
    private static MediaPath instance;

    public MediaPath() {}
    public static synchronized MediaPath getInstance(){
        if(instance == null){
            instance = new MediaPath();
        }
        return instance;
    }

    public String dishes(){
        return basePath+"platos/";
    }

    public String users(){
        return basePath+"usuarios/";
    }

    public String clients(){
        return basePath+"clientes/";
    }
}
