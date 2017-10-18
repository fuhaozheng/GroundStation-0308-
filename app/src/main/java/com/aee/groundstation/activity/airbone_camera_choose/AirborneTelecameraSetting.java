package com.aee.groundstation.activity.airbone_camera_choose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.IntToByte;
import com.aee.groundstation.R;
import com.aee.groundstation.communication.CommandEnum;
import com.aee.groundstation.communication.SendingSerialportMessage;

/**
 * Created by fuhz on 2016/9/20.
 */
public class AirborneTelecameraSetting extends Activity implements View.OnClickListener{
    private Button telecamera_set,takephoto_set,telecamera_self_set;
    private Button back;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airborne_camera_set);

        telecamera_set=(Button)findViewById(R.id.telecamra_set);
        takephoto_set=(Button)findViewById(R.id.takephoto_set);
        telecamera_self_set=(Button)findViewById(R.id.telecamera_self_set);
        back=(Button)findViewById(R.id.back);

        telecamera_set.setOnClickListener(this);
        takephoto_set.setOnClickListener(this);
        telecamera_self_set.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.telecamra_set:
                Intent intent1=new Intent(AirborneTelecameraSetting.this,
                                                          TelecameraSetting.class);
                startActivity(intent1);
                break;
            case R.id.takephoto_set:
                CommandEnum.command= CommandEnum.command|0x200000;
                byte[] b= IntToByte.getByte(CommandEnum.command);
                new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b ).send();
                Intent intent2=new Intent(AirborneTelecameraSetting.this,
                                                     TakePhotoSetting.class);
                startActivity(intent2);
                break;
            case R.id.telecamera_self_set:
                Intent intent3=new Intent(AirborneTelecameraSetting.this,
                                                                TelecameraSelfSetting.class);
                startActivity(intent3);
                break;
            case R.id.back:
                finish();
                break;

        }
    }

}
