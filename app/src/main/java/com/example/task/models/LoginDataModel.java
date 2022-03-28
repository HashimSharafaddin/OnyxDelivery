package com.example.task.models;

import com.google.gson.annotations.SerializedName;

public class LoginDataModel {

    @SerializedName("DeliveryName")
    private String deliveryName;

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }
}
