package com.example.task.response;

import com.example.task.models.ResultModel;
import com.google.gson.annotations.SerializedName;

public class UpdateStatusResponse {

    @SerializedName("Result")
    ResultModel resultModel;

    public ResultModel getResultModel() {
        return resultModel;
    }

    public void setResultModel(ResultModel resultModel) {
        this.resultModel = resultModel;
    }
}
