package com.aee.groundstation.activity.airbone_camera_choose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.aee.groundstation.IntToByte;
import com.aee.groundstation.R;
import com.aee.groundstation.communication.CommandEnum;
import com.aee.groundstation.communication.SendingSerialportMessage;

/**
 * Created by fuhz on 2016/10/17.
 */
public class CycleCover extends Activity implements View.OnClickListener{
    private Button btn_cover_off,btn_cover_on,save,cancel;
    private TextView message;
    private boolean flag=false;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.videotape_cycle_set);
        btn_cover_off=(Button)findViewById(R.id.cover_off);
        btn_cover_on=(Button)findViewById(R.id.cover_on);
        save=(Button)findViewById(R.id.save);
        cancel=(Button)findViewById(R.id.cancel);
        message=(TextView)findViewById(R.id.yes_no);
        btn_cover_off.setOnClickListener(this);
        btn_cover_on.setOnClickListener(this);
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }
    public void onClick(View v){
        byte[] b;
        Intent intent=new Intent(CycleCover.this,AirborneTelecameraSetting.class);
        switch (v.getId()){
            case R.id.cover_off:
                message.setText("否");
                flag=false;
                break;
            case R.id.cover_on:
                message.setText("是");
                flag=true;
                break;
            case R.id.save:
                if(flag){
                    CommandEnum.command= CommandEnum.command|0x100000;
                    b= IntToByte.getByte(CommandEnum.command);
                    new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b).send();
                    startActivity(intent);
                }else{
                     CommandEnum.command= CommandEnum.command&0xefffff;
                     b= IntToByte.getByte(CommandEnum.command);
                     new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b).send();
                     startActivity(intent);
                }
                break;
            case R.id.cancel:
                startActivity(intent);
                finish();
                break;

        }
    }

}
