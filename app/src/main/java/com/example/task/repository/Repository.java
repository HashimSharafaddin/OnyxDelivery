package com.example.task.repository;

import com.example.task.Request.LoginRequest;
import com.example.task.Request.UpdateStatusRequest;
import com.example.task.network.ApiInterface;
import com.example.task.response.BillResponse;
import com.example.task.Request.BillsRequest;
import com.example.task.response.BillsItemsResponse;
import com.example.task.response.LoginResponse;
import com.example.task.response.UpdateStatusResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private ApiInterface apiInterface;

    @Inject
    public Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }



    public Observable<LoginResponse> checkDeliveryLogin(LoginRequest value) {
        return apiInterface.checkDeliveryLogin(value);
    }

    public Observable<BillResponse> getDeliveryBills(BillsRequest value) {
        return apiInterface.getDeliveryBills(value);
    }

    public Observable<BillsItemsResponse> getDeliveryBillsItems(BillsRequest value) {
        return apiInterface.getDeliveryBillsItems(value);
    }

    public Observable<UpdateStatusResponse> updateDeliveryBillStatus(UpdateStatusRequest value) {
        return apiInterface.updateDeliveryBillStatus(value);
    }
}
