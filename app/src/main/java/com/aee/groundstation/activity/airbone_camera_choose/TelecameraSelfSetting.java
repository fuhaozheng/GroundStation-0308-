package com.aee.groundstation.activity.airbone_camera_choose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/20.
 */
public class TelecameraSelfSetting extends Activity implements View.OnClickListener{
    private Button back,btn_cycle;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telecamera_set);

        back=(Button)findViewById(R.id.back);
        btn_cycle=(Button)findViewById(R.id.cycle);
        back.setOnClickListener(this);
        btn_cycle.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.cycle:
                startActivity(new Intent(TelecameraSelfSetting.this,
                                                       CycleCover.class));
                break;
        }
    }
}
