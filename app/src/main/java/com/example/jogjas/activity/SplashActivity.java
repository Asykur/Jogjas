package com.example.jogjas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.jogjas.R;
import com.example.jogjas.utils.AppPreference;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (AppPreference.getPreferenceBoolean(SplashActivity.this, AppPreference.LOGIN)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginRegActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }
}
