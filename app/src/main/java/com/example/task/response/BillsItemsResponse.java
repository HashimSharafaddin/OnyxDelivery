package com.example.task.response;

import com.example.task.models.BillItemsData;
import com.example.task.models.ResultModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BillsItemsResponse {

    @SerializedName("Data")
    private BillItemsData data;

    @SerializedName("Result")
    private ResultModel result;

    public BillItemsData getData() {
        return data;
    }

    public void setData(BillItemsData data) {
        this.data = data;
    }

    public ResultModel getResult() {
        return result;
    }

    public void setResult(ResultModel result) {
        this.result = result;
    }
}
