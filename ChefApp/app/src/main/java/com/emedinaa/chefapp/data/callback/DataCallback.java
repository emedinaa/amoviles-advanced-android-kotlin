package com.emedinaa.chefapp.data.callback;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/4/18
 */
public interface DataCallback {

    void onSuccess(Object object);
    void onFailure(Exception exception);
}
