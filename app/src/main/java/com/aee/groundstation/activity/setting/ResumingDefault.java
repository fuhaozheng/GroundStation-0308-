package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/21.
 */
public class ResumingDefault extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumeing_default);
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(5000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ResumingDefault.this, ResumeDefaultFinished.class));
                        finish();
                    }
                });
            }
        }.start();
    }
}
