package com.aee.groundstation.activity.airbone_camera_choose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;

import com.aee.groundstation.IntToByte;
import com.aee.groundstation.R;
import com.aee.groundstation.communication.CommandEnum;
import com.aee.groundstation.communication.SendingSerialportMessage;


/**
 * Created by fuhz on 2016/10/17.
 */
public class AirboneCameraChoose extends Activity implements View.OnClickListener{
    private RadioButton camera1,camera2;
    private Button button_ok,back;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.airborne_camera_choose);
        camera1=(RadioButton)findViewById(R.id.camera1);
        camera2=(RadioButton)findViewById(R.id.camera2);
        button_ok=(Button)findViewById(R.id.choose_ok);
        back=(Button)findViewById(R.id.back);
        button_ok.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    public void onClick(View view){
        Intent intent3 = new Intent(AirboneCameraChoose.this,
                AirborneTelecameraSetting.class);
        switch(view.getId()){
            case R.id.choose_ok:
                byte[] b;
                if(camera1.isChecked()) {
                     CommandEnum.command= CommandEnum.command|0x400000;
                    b= IntToByte.getByte(CommandEnum.command);
                    new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b).send();
                    startActivity(intent3);
                }else if(camera2.isChecked()){
                     CommandEnum.command= CommandEnum.command|0x400000;
                    b= IntToByte.getByte(CommandEnum.command);
                    new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b).send();
                    startActivity(intent3);
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
