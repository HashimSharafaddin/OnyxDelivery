package com.example.task.network;

import com.example.task.Request.LoginRequest;
import com.example.task.Request.UpdateStatusRequest;
import com.example.task.response.BillResponse;
import com.example.task.Request.BillsRequest;
import com.example.task.response.BillsItemsResponse;
import com.example.task.response.LoginResponse;
import com.example.task.response.UpdateStatusResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "User-Agent: okhttp/4.4.0"
    })
    @POST("CheckDeliveryLogin")
    Observable<LoginResponse> checkDeliveryLogin(
            @Body LoginRequest value
    );



    @Headers("Content-Type: application/json")
    @POST("GetDeliveryBillsItems")
    Observable<BillResponse> getDeliveryBills(
            @Body BillsRequest value
//            @Body Map<String, String> value
    );


    @POST("GetDeliveryBillsItems")
    Observable<BillsItemsResponse> getDeliveryBillsItems(
            @Body BillsRequest value
    );

    @POST("UpdateDeliveryBillStatus")
    Observable<UpdateStatusResponse> updateDeliveryBillStatus(
            @Body UpdateStatusRequest value
    );
}
