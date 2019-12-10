package com.emedinaa.chefapp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/4/18
 */
public class User implements Serializable{

    @SerializedName("_id")
    private String id;

    @SerializedName("nombres")
    private String name;

    @SerializedName("apellido_paterno")
    private String lastName;

    @SerializedName("apellido_materno")
    private String mLastName;

    private String email;

    private String socketId;

    private UserType type;

    @SerializedName("imagen")
    private String image;

    private String token;

    public String fullName(){
        return this.name+" "+this.lastName+" "+this.mLastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSocketId() {
        return socketId;
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
