package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;



import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task.R;
import com.example.task.Request.BillsRequest;
import com.example.task.Request.LoginRequest;
import com.example.task.response.LoginResponse;
import com.example.task.utils.Constants;
import com.example.task.viewmodels.MainViewModel;

import java.io.IOException;
import java.security.GeneralSecurityException;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_user_id, et_password;
    Button login;

    Dialog connectionDialog, loadingDialog;


    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_user_id = findViewById(R.id.et_user_id);
        et_password = findViewById(R.id.et_password);
        login = findViewById(R.id.login);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        loadingDialog = new Dialog(LoginActivity.this);
        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
        loadingDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        loadingDialog.setContentView(R.layout.dialog_loading);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setCancelable(false);







        login.setOnClickListener(this);

    }

    public void checkLogin(LoginRequest loginRequest){
        viewModel.checkDeliveryLogin(loginRequest);

        viewModel.getConnectionState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean){
                    Toast.makeText(getApplicationContext(),"No Internet Connection", Toast.LENGTH_LONG).show();
                    loadingDialog.dismiss();
                }  }
        });

        viewModel.onSaveEvent().observe(this, booleanEvent -> {
            if (booleanEvent != null){
                final Boolean shouldSave = booleanEvent.getContentIfNotHandled();
                if (shouldSave != null && shouldSave){
            viewModel.getLoginResponseList().observe(this, new Observer<LoginResponse>() {
                @Override
                public void onChanged(LoginResponse loginResponse) {

                    loadingDialog.dismiss();
                    Toast.makeText(getApplicationContext(),loginResponse.getResult().getMessage(),Toast.LENGTH_LONG).show();

                    if (loginResponse.getResult().getError_number().equals("0")
                            && loginResponse.getLoginData().getDeliveryName() != null){
                        try {
                            Constants.getEncryptedSharedPreferences(getApplicationContext()).edit()
                                    .putString("delivery_name",loginResponse.getLoginData().getDeliveryName())
                                    .putString("delivery_no",et_user_id.getText().toString())
                                    .putBoolean("is_logged_in", true).apply();

                            Intent intent = new Intent(getApplicationContext(),AllOrdersActivity.class);
                            startActivity(intent);


                        } catch (GeneralSecurityException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
                }
            }

        });
    }



    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.login){
            String u_id = et_user_id.getText().toString();
            String u_password = et_password.getText().toString();
            if (u_id.equals("")){
                Toast.makeText(getApplicationContext(),"Enter a valid UserID", Toast.LENGTH_LONG).show();
            } else if (u_password.matches("")){
                Toast.makeText(getApplicationContext(),"Enter a valid Password", Toast.LENGTH_LONG).show();
            }
            else {
                LoginRequest.LoginCredentials credentials = new LoginRequest.LoginCredentials("2",u_id,u_password);
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setCredentials(credentials);

                loadingDialog.show();
                checkLogin(loginRequest);
            }
        }
    }
}
