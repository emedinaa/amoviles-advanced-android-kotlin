package com.emedinaa.chefapp;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.emedinaa.chefapp.data.DataInjector;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class ChefApplication extends Application {

    private final DataInjector dataInjector= DataInjector.getInstance();
    private final String imagesPath= BuildConfig.DOMAIN+BuildConfig.IMAGESPATH;

    @Override
    public void onCreate() {
        super.onCreate();
        dataInjector.setUp(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public String getImagesPath() {
        return imagesPath;
    }

}
