package com.emedinaa.chefapp.data.remote.model;


import com.emedinaa.chefapp.model.entity.User;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/4/18
 */
public class LogInResponse extends BaseResponse {

    private User data;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
