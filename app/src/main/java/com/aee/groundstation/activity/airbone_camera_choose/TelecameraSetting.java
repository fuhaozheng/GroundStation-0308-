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
public class TelecameraSetting extends Activity implements View.OnClickListener{
    private Button btn_resolution,back;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup_setting);

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        btn_resolution=(Button)findViewById(R.id.resolution);
        btn_resolution.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.resolution:
                Intent intent=new Intent(TelecameraSetting.this,
                        PNScale.class);
                startActivity(intent);
                break;
        }
    }
}
