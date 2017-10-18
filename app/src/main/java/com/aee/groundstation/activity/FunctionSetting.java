package com.aee.groundstation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.view.View.OnClickListener;
import com.aee.groundstation.R;
import com.aee.groundstation.activity.airbone_camera_choose.AirboneCameraChoose;
import com.aee.groundstation.activity.airbone_camera_choose.AirborneTelecameraSetting;
import com.aee.groundstation.activity.mapset.MapSetting;
import com.aee.groundstation.activity.setting.Setting1;
import com.aee.groundstation.activity.task_load.TaskLoadSetting;

/**
 * Created by fuhz on 2016/9/20.
 */
public class FunctionSetting extends Activity implements OnClickListener{
    private Button servo_check,telecamera,external_loads,map_set,playback,setting;
    private Button back;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gongnengshezhi);
        servo_check=(Button)findViewById(R.id.servo_check);
        telecamera=(Button)findViewById(R.id.shexiangji_set);
        external_loads=(Button)findViewById(R.id.external_loads);
        map_set=(Button)findViewById(R.id.map_set);
        playback=(Button)findViewById(R.id.playback_set);
        setting=(Button)findViewById(R.id.setting);
        back=(Button)findViewById(R.id.back);
        //为各个键添加监听事件
        servo_check.setOnClickListener(this);
        telecamera.setOnClickListener(this);
        external_loads.setOnClickListener(this);
        map_set.setOnClickListener(this);
        playback.setOnClickListener(this);
        setting.setOnClickListener(this);
        setting.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    public void onClick(View v){
        //设置各个键的监听事件内容
       switch(v.getId()){
           case R.id.servo_check:
               Intent intent1=new Intent();
               startActivity(intent1);
               break;
           case R.id.shexiangji_set:
               Intent intent2=new Intent(FunctionSetting.this,
                                             AirboneCameraChoose.class);
               startActivity(intent2);
               break;
           case R.id.external_loads:
               Intent intent3=new Intent(FunctionSetting.this,
                                                TaskLoadSetting.class);
               startActivity(intent3);
               break;
           case R.id.map_set:
               Intent intent4=new Intent(FunctionSetting.this,
                                                   MapSetting.class);
               startActivity(intent4);
               break;
           case R.id.playback_set:
               Intent intent5=new Intent(FunctionSetting.this,Playback.class);
               startActivity(intent5);
               break;
           case R.id.setting:
               Intent intent6=new Intent(FunctionSetting.this,Setting1.class);
               startActivity(intent6);
               break;
           case R.id.back:
               finish();
               break;
        }
    }
}
