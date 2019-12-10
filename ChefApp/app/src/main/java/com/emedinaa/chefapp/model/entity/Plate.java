package com.emedinaa.chefapp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class Plate extends OrderViewType implements Serializable{

    @SerializedName("_id")
    private String id;

    @SerializedName("cantidad")
    private int amount;

    @SerializedName("nombre")
    private String name;

    @SerializedName("plato_id")
    private String dishId;

    @SerializedName("precio")
    private Double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getTotal() {
        return this.price*this.amount;
    }

    @Override
    public boolean isItem() {
        return true;
    }
}
