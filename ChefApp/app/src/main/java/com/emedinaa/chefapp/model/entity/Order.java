package com.emedinaa.chefapp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */
public class Order {

    @SerializedName("_id")
    private String id;

    @SerializedName("cliente_id")
    private User clientId;

    @SerializedName("detalle")
    private List<Plate> plates;

    @SerializedName("fechaRegistro")
    private String regDate;

    private Double total;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getClientId() {
        return clientId;
    }

    public void setClientId(User clientId) {
        this.clientId = clientId;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
