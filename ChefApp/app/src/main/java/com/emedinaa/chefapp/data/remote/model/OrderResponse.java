package com.emedinaa.chefapp.data.remote.model;

import com.emedinaa.chefapp.model.entity.Plate;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 8/18/18
 */

/*
_id: String,
             cliente_id: String,
             nombreCliente: String,
             fechaRegistro: Date ,
             orden: [
        {
plato_id: string,
nombre: string,
precio: number,
cantidad: number
}
]

 */
public class OrderResponse implements Serializable{

    @SerializedName("_id")
    private String id;

    @SerializedName("cliente_id")
    private String clientId;

    @SerializedName("nombreCliente")
    private String clientName;

    @SerializedName("fechaRegistro")
    private String orderDate;

    @SerializedName("orden")
    private List<Plate> plates;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id='" + id + '\'' +
                ", clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", plates=" + plates +
                '}';
    }
}
