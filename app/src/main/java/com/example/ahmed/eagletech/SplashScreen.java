package com.example.ahmed.eagletech;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread t=new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("nameSharedPref",0);
                    String token=sharedPreferences.getString("accessToken","");
                    if(token.equals("")){
                        Intent i=new Intent(SplashScreen.this,Login.class);
                        startActivity(i);
                        finish();
                    }else {
                        Intent i=new Intent(SplashScreen.this,Navigation.class);
                        startActivity(i);
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        t.start();
    }
}
