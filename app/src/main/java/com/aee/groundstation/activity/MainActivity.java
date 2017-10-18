package com.aee.groundstation.activity;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;

import com.aee.groundstation.AcceptMessageActivity;
import com.aee.groundstation.MainInterfaceAnother6;
import com.aee.groundstation.MainInterfaceAnother7;
import com.aee.groundstation.MainInterfaceAnother8;
import com.aee.groundstation.R;
import com.aee.groundstation.communication.MainActivity1;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaijipicture);
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //startActivity(new Intent(MainActivity.this, MainInterface.class));
                        //startActivity(new Intent(MainActivity.this, AcceptMessageActivity.class));
                        startActivity(new Intent(MainActivity.this, MainInterfaceAnother8.class));
                        finish();
                    }
                });
            }
        }.start();
    }
}
