package com.example.task.models;

import com.google.gson.annotations.SerializedName;

public class ResultModel {

    @SerializedName("ErrMsg")
    private String message;

    @SerializedName("ErrNo")
    private String error_number;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_number() {
        return error_number;
    }

    public void setError_number(String error_number) {
        this.error_number = error_number;
    }
}
