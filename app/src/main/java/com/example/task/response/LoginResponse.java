package com.example.task.response;

import com.example.task.models.LoginDataModel;
import com.example.task.models.ResultModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponse {

    @SerializedName("Data")
    private LoginDataModel loginData;

    @SerializedName("Result")
    private ResultModel result;


    public LoginDataModel getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginDataModel loginData) {
        this.loginData = loginData;
    }

    public ResultModel getResult() {
        return result;
    }

    public void setResult(ResultModel result) {
        this.result = result;
    }
}
