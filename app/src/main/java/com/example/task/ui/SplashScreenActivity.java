package com.example.task.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.task.R;
import com.example.task.utils.Constants;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SplashScreenActivity extends AppCompatActivity {

    private  static  int splash_time_out = 2500;

    String delivery_name;
    String delivery_no;
    boolean is_logged_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        try {
            delivery_name = Constants.getEncryptedSharedPreferences(getApplicationContext()).getString("delivery_name","none");
            delivery_no    = Constants.getEncryptedSharedPreferences(getApplicationContext()).getString("delivery_no","none");
             is_logged_in = Constants.getEncryptedSharedPreferences(getApplicationContext()).getBoolean("is_logged_in",false);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (delivery_no.equals("none")&& !is_logged_in){
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
                else {
                    //todo : go to orders activity
                    Intent intent = new Intent(SplashScreenActivity.this, AllOrdersActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
            }
        },splash_time_out);



    }

    public SharedPreferences getEncryptedSharedPreferences() throws GeneralSecurityException, IOException {
        MasterKey masterKey = new MasterKey.Builder(getApplicationContext(), MasterKey.DEFAULT_MASTER_KEY_ALIAS)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build();

        return EncryptedSharedPreferences.create(
                getApplicationContext(),
                "PREFS",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
    }
}
