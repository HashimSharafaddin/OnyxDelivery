package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.adapters.OrdersAdapter;
import com.example.task.databinding.ActivityAllOrdersBinding;
import com.example.task.models.BillData;
import com.example.task.response.BillResponse;
import com.example.task.Request.BillsRequest;
import com.example.task.utils.Constants;
import com.example.task.viewmodels.MainViewModel;

import java.io.IOException;
import java.security.GeneralSecurityException;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class AllOrdersActivity extends AppCompatActivity implements  OrdersAdapter.onOrderClick, SwipeRefreshLayout.OnRefreshListener {


    MainViewModel viewModel;
    OrdersAdapter ordersAdapter;
    Dialog filter_dialog, connectionDialog;

    String delivery_name, delivery_no;
    ActivityAllOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         binding = DataBindingUtil.setContentView(this,R.layout.activity_all_orders);


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        try {
             delivery_name = Constants.getEncryptedSharedPreferences(getApplicationContext())
                    .getString("delivery_name","none");
            delivery_no = Constants.getEncryptedSharedPreferences(getApplicationContext())
                    .getString("delivery_no","none");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        binding.tvName.setText(delivery_name );
        binding.swipeLayout.setOnRefreshListener(this);


        filter_dialog = new Dialog(AllOrdersActivity.this);
        filter_dialog.setContentView(R.layout.dialog_filter);
        filter_dialog.setCanceledOnTouchOutside(true);

        connectionDialog = new Dialog(AllOrdersActivity.this);
        connectionDialog.setContentView(R.layout.dialog_no_connection);
        Button retry_connect_dlg_btn = connectionDialog.findViewById(R.id.retry_connect);
        Button close = connectionDialog.findViewById(R.id.close);
        connectionDialog.setCanceledOnTouchOutside(true);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        binding.ordersRecyclerview.setLayoutManager(linearLayoutManager);
        ordersAdapter = new OrdersAdapter(getApplicationContext(),this);
        binding.ordersRecyclerview.setAdapter(ordersAdapter);


        BillsRequest.Bill bill = new BillsRequest.Bill(delivery_no,"2","","");
        BillsRequest billsRequest = new BillsRequest();
        billsRequest.setBill(bill);

        getOrders(billsRequest);


                binding.cardFilter.setOnClickListener(v -> filter_dialog.show());

                retry_connect_dlg_btn.setOnClickListener(v -> {
                    binding.progressOrders.setVisibility(View.VISIBLE);
                    connectionDialog.dismiss();

                    getOrders(billsRequest);

                });

                close.setOnClickListener(v -> {
                    connectionDialog.dismiss();
                });

    }



    public void getOrders(BillsRequest value){

        viewModel.getOrders(value);
        viewModel.getConnectionState().observe(this, aBoolean -> {
            if (!aBoolean){

                    binding.progressOrders.setVisibility(View.GONE);
                    connectionDialog.show();
                binding.swipeLayout.setRefreshing(false);
            }  });


        viewModel.getBillsList().observe(this, response -> {
            binding.swipeLayout.setRefreshing(false);

            connectionDialog.dismiss();
            binding.progressOrders.setVisibility(View.GONE);
            if (response.getResult().getError_number().equals("0")){
                if (response.getData().getBillsList().size()!=0){
                    ordersAdapter.setList(response.getData().getBillsList());
                    binding.layoutNoOrders.setVisibility(View.GONE);
                } else {
                    binding.layoutNoOrders.setVisibility(View.VISIBLE);
                } }
        });
    }

    @Override
    public void onClickListener(BillData.BillsModel billsModel, int position, String action) {

        Intent intent = new Intent(getApplicationContext(), OrderDetailsActivity.class);

        intent.putExtra("bill_serial", billsModel.getBill_serial());
        intent.putExtra("customer_name", billsModel.getCustomer_name());
        intent.putExtra("bill_num", billsModel.getBill_number());
        intent.putExtra("bill_date", billsModel.getBill_date());
        intent.putExtra("bill_type", "Cash");
        intent.putExtra("sub_total", billsModel.getBill_amount());
        intent.putExtra("taxes", billsModel.getTax_amount());
        intent.putExtra("delivery", billsModel.getDelivery_amount());
        intent.putExtra("address",billsModel.getCustomer_address());
        intent.putExtra("mobile", billsModel.getCustomer_mobile_number());
        intent.putExtra("status_flag", billsModel.getDelivery_status_flag());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }



    @Override
    public void onRefresh() {
        BillsRequest.Bill bill = new BillsRequest.Bill(delivery_no,"2","","");
        BillsRequest billsRequest = new BillsRequest();
        billsRequest.setBill(bill);

        binding.progressOrders.setVisibility(View.VISIBLE);
        getOrders(billsRequest);
    }

}
