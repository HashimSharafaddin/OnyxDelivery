package com.example.task.response;

import com.example.task.models.BillData;
import com.example.task.models.ResultModel;
import com.google.gson.annotations.SerializedName;

public class BillResponse {

    @SerializedName("Data")
    private BillData data;

    @SerializedName("Result")
    private ResultModel result;


    public BillData getData() {
        return data;
    }

    public void setData(BillData data) {
        this.data = data;
    }

    public ResultModel getResult() {
        return result;
    }

    public void setResult(ResultModel result) {
        this.result = result;
    }
}
