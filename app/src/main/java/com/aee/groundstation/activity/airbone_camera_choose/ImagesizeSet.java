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
public class ImagesizeSet extends Activity implements View.OnClickListener{
    private Button save,cancel;
    private RadioButton imagesize;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.is_12m_16m);
        save=(Button)findViewById(R.id.save);
        cancel=(Button)findViewById(R.id.cancel);
        imagesize=(RadioButton)findViewById(R.id.imagesize);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }
    public void onClick(View view){
        byte[] b;
        Intent intent=new Intent(ImagesizeSet.this,AirborneTelecameraSetting.class);
        switch (view.getId()){
            case R.id.imagesize:
                break;
            case R.id.save:
                if(imagesize.isChecked()){
                     CommandEnum.command= CommandEnum.command&0xfffdff;
                     CommandEnum.command= CommandEnum.command|0x000100;
                     b= IntToByte.getByte(CommandEnum.command);
                     new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b).send();
                }else{
                     CommandEnum.command= CommandEnum.command&0xfffdff;
                     CommandEnum.command= CommandEnum.command&0xfffeff;
                     b= IntToByte.getByte(CommandEnum.command);
                     new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b).send();
                }
                startActivity(intent);
                break;
            case R.id.cancel:
                startActivity(intent);
                finish();
                break;
        }
    }
}
