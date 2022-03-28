package com.example.task.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task.Request.LoginRequest;
import com.example.task.Request.UpdateStatusRequest;
import com.example.task.repository.Repository;
import com.example.task.response.BillResponse;
import com.example.task.Request.BillsRequest;
import com.example.task.response.BillsItemsResponse;
import com.example.task.response.LoginResponse;
import com.example.task.response.UpdateStatusResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private Repository repository;
    private final io.reactivex.rxjava3.disposables.CompositeDisposable disposables = new CompositeDisposable();


    MutableLiveData<BillResponse> BillsList = new MutableLiveData<>();
    MutableLiveData<BillsItemsResponse> BillItemsList = new MutableLiveData<>();

    MutableLiveData<LoginResponse> LoginResponseList = new MutableLiveData<>();
    MutableLiveData<UpdateStatusResponse> UpdateStatusList = new MutableLiveData<>();

    MutableLiveData<Boolean> isConnected = new MutableLiveData<Boolean>();





    @ViewModelInject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    MutableLiveData<Event<Boolean>> saveEvent = new MutableLiveData<>();

    public void newSaveEvent(){
        saveEvent.setValue(new Event<>(true));
    }


    public LiveData<Event<Boolean>> onSaveEvent(){
        return saveEvent;
    }


    public MutableLiveData<Boolean> getConnectionState   () {
        return isConnected;
    }


    public MutableLiveData<BillResponse> getBillsList() {

        return BillsList;
    }

    public MutableLiveData<BillsItemsResponse> getBillItemsList() {

        return BillItemsList;
    }

    public MutableLiveData<LoginResponse> getLoginResponseList() {

        return LoginResponseList;
    }


    public MutableLiveData<UpdateStatusResponse> getUpdateStatusList() {

        return UpdateStatusList;
    }



//////////////////////////////////////////////////////////////////////


    public void checkDeliveryLogin(LoginRequest value){
        isConnected.setValue(true);

        repository.
                checkDeliveryLogin(value)
                .subscribeOn(Schedulers.io() )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(@NonNull LoginResponse response) {
                        newSaveEvent();
                        LoginResponseList.setValue(response);
                        Log.i("login_delivery",response.getResult().getMessage());
                        isConnected.setValue(true);

                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.i("login_delivery",e.getMessage());

                        if (e.getMessage().startsWith("Failed to connect to") || e.getMessage().equals("timeout")  || e.getMessage().startsWith("Unable to resolve host")
                        ){
                            isConnected.setValue(false);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



 public void getOrders(BillsRequest value){
     isConnected.setValue(true);

     repository.
             getDeliveryBills(value)
             .subscribeOn(Schedulers.io() )
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Observer<BillResponse>() {
                 @Override
                 public void onSubscribe(@NonNull Disposable d) {
                     disposables.add(d);
                 }

                 @Override
                 public void onNext(@NonNull BillResponse response) {

                     isConnected.setValue(true);

                     BillsList.setValue(response);
                     Log.i("all_bills",response.getResult().getMessage());
                 }
                 @Override
                 public void onError(@NonNull Throwable e) {
                     e.printStackTrace();
                     Log.i("all_bills",e.getMessage());

                     if (e.getMessage().startsWith("Failed to connect to") || e.getMessage().startsWith("Unable to resolve host") ){
                         isConnected.setValue(false);
                     }
                 }

                 @Override
                 public void onComplete() {

                 }
             });
 }


 public void getBillsItems(BillsRequest value){
     isConnected.setValue(true);

     repository.
             getDeliveryBillsItems(value)
             .subscribeOn(Schedulers.io() )
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Observer<BillsItemsResponse>() {
                 @Override
                 public void onSubscribe(@NonNull Disposable d) {
                     disposables.add(d);
                 }

                 @Override
                 public void onNext(@NonNull BillsItemsResponse response) {

                     isConnected.setValue(true);

                     BillItemsList.setValue(response);
                     Log.i("bill_items",response.getResult().getMessage());
                 }
                 @Override
                 public void onError(@NonNull Throwable e) {
                     e.printStackTrace();
                     Log.i("bill_items",e.getMessage());

                     if (e.getMessage().startsWith("Failed to connect to") || e.getMessage().equals("timeout") || e.getMessage().startsWith("Unable to resolve host") ){
                         isConnected.setValue(false);
                     }

                 }

                 @Override
                 public void onComplete() {

                 }
             });
 }


 public void updateDeliveryBillStatus(UpdateStatusRequest value){
     isConnected.setValue(true);

     repository.
             updateDeliveryBillStatus(value)
             .subscribeOn(Schedulers.io() )
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(new Observer<UpdateStatusResponse>() {
                 @Override
                 public void onSubscribe(@NonNull Disposable d) {
                     disposables.add(d);
                 }

                 @Override
                 public void onNext(@NonNull UpdateStatusResponse response) {


                     UpdateStatusList.setValue(response);
                     Log.i("update_status",response.getResultModel().getMessage());
                 }
                 @Override
                 public void onError(@NonNull Throwable e) {
                     e.printStackTrace();
                     Log.i("update_status",e.getMessage());
                     if (e.getMessage().startsWith("Failed to connect to") || e.getMessage().equals("timeout")  || e.getMessage().startsWith("Unable to resolve host") ){
                         isConnected.setValue(false);
                     }
                 }

                 @Override
                 public void onComplete() {

                 }
             });
 }




    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
