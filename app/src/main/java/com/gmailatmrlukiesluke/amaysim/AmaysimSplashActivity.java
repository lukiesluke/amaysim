package com.gmailatmrlukiesluke.amaysim;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class AmaysimSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaysim_splash);

        SplashAmaysim();
    }

    private void SplashAmaysim() {
        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                    Intent i = new Intent(AmaysimSplashActivity.this, MainActivity.class);
                    startActivity(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        };
        logoTimer.start();
    }
}
