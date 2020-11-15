package com.example.eight.ViewClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.eight.R;
import com.kaiguanjs.SplashLietener;
import com.kaiguanjs.utils.YQCUtils;

public class Splash extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler=new Handler();

        YQCUtils.splashAction(this, new SplashLietener() {
            @Override
            public void startMySplash(int version, String downUrl) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(Splash.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },6000);
            }
        });
    }

}