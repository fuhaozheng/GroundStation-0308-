package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.R;
import com.aee.groundstation.activity.MainInterface;

/**
 * Created by fuhz on 2016/9/21.
 */
public class ResumeDefaultFinished  extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_true);
        Button back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResumeDefaultFinished.this,Setting2.class));
            }
        });
    }

}
