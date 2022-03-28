package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.task.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.security.GeneralSecurityException;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint

public class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);




    }




}
