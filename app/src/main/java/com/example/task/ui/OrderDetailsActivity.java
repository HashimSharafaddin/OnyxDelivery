package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.Request.BillsRequest;
import com.example.task.Request.UpdateStatusRequest;
import com.example.task.adapters.BillsAdapter;
import com.example.task.adapters.OrdersAdapter;
import com.example.task.databinding.ActivityOrderDetailsBinding;
import com.example.task.response.BillsItemsResponse;
import com.example.task.response.UpdateStatusResponse;
import com.example.task.viewmodels.MainViewModel;
import com.google.android.material.textfield.TextInputLayout;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderDetailsActivity extends AppCompatActivity {

    BillsAdapter billsAdapter;
    MainViewModel viewModel;

    ActivityOrderDetailsBinding binding;
    Dialog loadingDialog;

    boolean cd_shown = true;
    boolean od_shown = true;
    boolean ld_shown = true;

    String bill_serial;
    String current_status = "1";
    String reason ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_order_details);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        Intent intent = getIntent();
         bill_serial = intent.getStringExtra("bill_serial");
        String c_name = intent.getStringExtra("customer_name");
        String c_mobile  = intent.getStringExtra("mobile");
        String c_address  = intent.getStringExtra("address");
        String order_no  = intent.getStringExtra("bill_num");
        String order_date  = intent.getStringExtra("bill_date");
        String pay_type  = intent.getStringExtra("bill_type");
        String status_flag  = intent.getStringExtra("status_flag");

        Float sub_total  = Float.valueOf(intent.getStringExtra("sub_total"));
        Float taxes  = Float.valueOf(intent.getStringExtra("taxes"));
        Float delivery  = Float.valueOf(intent.getStringExtra("delivery"));
        Float discount = 0.0f;

        Float total = sub_total+taxes+delivery;



        binding.tvCustomerName.setText(c_name);
       binding.tvOrderNum.setText("#"+order_no);
       binding.tvOrderDate.setText(order_date);
       binding.tvPaymentOption.setText(pay_type);
       binding.tvSubTotal.setText(String.format("%.2f",sub_total));
       binding.tvTaxes.setText(String.format("%.2f",taxes));
       binding.tvDelivery.setText(String.format("%.2f",delivery));
       binding.tvDiscount.setText(String.format("%.1f",discount));
       binding.tvTotal.setText(String.format("%.1f",total));

       binding.tvAddress.setText(c_address);
       binding.tvMobile.setText(c_mobile);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        binding.billItemsRecyclerview.setLayoutManager(linearLayoutManager);
        binding.billItemsRecyclerview.setNestedScrollingEnabled(false);
        billsAdapter = new BillsAdapter(getApplicationContext());
        binding.billItemsRecyclerview.setAdapter(billsAdapter);

        loadingDialog = new Dialog(OrderDetailsActivity.this);
        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
        loadingDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        loadingDialog.setContentView(R.layout.dialog_loading);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);

        ArrayAdapter<CharSequence> order_status_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.array_spinner_status, android.R.layout.simple_spinner_item);
        order_status_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerOrderStatus.setAdapter(order_status_adapter);

        ArrayAdapter<CharSequence> return_reasons_adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.array_spinner_return_reasons, android.R.layout.simple_spinner_item);
        return_reasons_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerReturnReason.setAdapter(return_reasons_adapter);



        BillsRequest.Bill bill = new BillsRequest.Bill("1010","2",bill_serial,"");
        BillsRequest billsRequest = new BillsRequest();
        billsRequest.setBill(bill);

        getBillItems(billsRequest);


        binding.back.setOnClickListener(v -> {
            finish();
            getParent();
        });

        binding.customerDetails.setOnClickListener(v -> {
            if (cd_shown){
                cd_shown = false;

                binding.layoutCustomerDetails.animate().alpha(0.0f);
                binding.layoutCustomerDetails.clearAnimation();

                binding.layoutCustomerDetails.setVisibility(View.GONE);
                binding.imgCdShow.setImageResource(R.drawable.ic_arrow_down_black);
            } else {

                binding.layoutCustomerDetails.animate().alpha(1.0f);
                binding.layoutCustomerDetails.clearAnimation();

                binding.imgCdShow.setImageResource(R.drawable.ic_arrow_up_black);
                binding.layoutCustomerDetails.setVisibility(View.VISIBLE);

                cd_shown = true;

            }
        });

        binding.orderDetails.setOnClickListener(v -> {
            if (od_shown){

                binding.layoutOrdDetails.animate().alpha(0.0f);
                binding.layoutOrdDetails.clearAnimation();

                binding.layoutOrdDetails.setVisibility(View.GONE);
                binding.imgOdShow.setImageResource(R.drawable.ic_arrow_down_black);
                od_shown = false;

            } else {

                binding.layoutOrdDetails.animate().alpha(1.0f);
                binding.layoutOrdDetails.clearAnimation();

                binding.imgOdShow.setImageResource(R.drawable.ic_arrow_up_black);
                binding.layoutOrdDetails.setVisibility(View.VISIBLE);

                od_shown = true;

            }
        });

        binding.locationDetails.setOnClickListener(v -> {
            if (ld_shown){

                binding.layoutLocationDetails.animate().alpha(0.0f);
                binding.layoutLocationDetails.clearAnimation();

                binding.layoutLocationDetails.setVisibility(View.GONE);
                binding.imgLdShow.setImageResource(R.drawable.ic_arrow_down_black);
                ld_shown = false;

            } else {

                binding.layoutLocationDetails.animate().alpha(1.0f);
                binding.layoutLocationDetails.clearAnimation();

                binding.imgLdShow.setImageResource(R.drawable.ic_arrow_up_black);
                binding.layoutLocationDetails.setVisibility(View.VISIBLE);

                ld_shown = true;

            }
        });

        binding.spinnerReturnReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.spinnerReturnReason.getSelectedItem().toString()) {
                    case "Reason1":
                        binding.layoutTypeReturnReason.setVisibility(View.GONE);

                        reason = "bad";
                        break;

                    case "Manufacturing defect":
                        binding.layoutTypeReturnReason.setVisibility(View.GONE);
                        reason = "Manufacturing defect";

                        break;

                    case "Other":
                        binding.layoutTypeReturnReason.setVisibility(View.VISIBLE);
                        reason = binding.etReturnReason.getEditText().getText().toString();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.spinnerOrderStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (binding.spinnerOrderStatus.getSelectedItem().toString()) {

                    case "Delivered":
                        current_status = "1";
                        binding.layoutReturnReason.setVisibility(View.GONE);
                        binding.layoutTypeReturnReason.setVisibility(View.GONE);
                        break;

                    case "New":

                        binding.layoutReturnReason.setVisibility(View.GONE);
                        binding.layoutTypeReturnReason.setVisibility(View.GONE);
                        break;


                    case "Returned":
                        current_status = "3";

                        binding.layoutReturnReason.setVisibility(View.VISIBLE);
                        break;

                    case "Partial Return":
                        current_status = "2";

                        binding.layoutReturnReason.setVisibility(View.VISIBLE);

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.btnSaveChanges.setOnClickListener(v -> {
            loadingDialog.show();
            updateDeliveryBillStatus("2",bill_serial, current_status, reason);
        });
    }



    public void getBillItems(BillsRequest billsRequest){
        viewModel.getBillsItems(billsRequest);

        viewModel.getBillItemsList().observe(this, new Observer<BillsItemsResponse>() {
            @Override
            public void onChanged(BillsItemsResponse response) {
                billsAdapter.setList(response.getData().getBillsList());
                binding.progressBillItems.setVisibility(View.GONE);
            }
        });
    }




    public void updateDeliveryBillStatus(String P_LANG_NO, String bill_serial, String status_flag, String reason){

        UpdateStatusRequest.StatusValues statusValues =
                new UpdateStatusRequest.StatusValues("2", bill_serial,status_flag,reason);
        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setStatusValues(statusValues);

        viewModel.updateDeliveryBillStatus(request);



        viewModel.getConnectionState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean){
                    Toast.makeText(getApplicationContext(),"No Internet Connection", Toast.LENGTH_LONG).show();
                    loadingDialog.dismiss();
                }  }
        });

        viewModel.getUpdateStatusList().observe(this, new Observer<UpdateStatusResponse>() {
            @Override
            public void onChanged(UpdateStatusResponse updateStatusResponse) {
                loadingDialog.dismiss();
                Toast.makeText(getApplicationContext(),updateStatusResponse.getResultModel().getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
