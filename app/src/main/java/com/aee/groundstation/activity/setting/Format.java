package com.aee.groundstation.activity.setting;

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
 * Created by fuhz on 2016/9/21.
 */
public class Format extends Activity  implements View.OnClickListener{
    private Button formating_bt,unformat_bt;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.format);
        formating_bt=(Button)findViewById(R.id.formating);
        unformat_bt=(Button)findViewById(R.id.unformat);
        formating_bt.setOnClickListener(this);
        unformat_bt.setOnClickListener(this);

    }

    public void onClick(View v){
        byte[] b;
        switch(v.getId()){
            case R.id.formating:
                new CommandEnum().command=new CommandEnum().command|0x000001;
                b= IntToByte.getByte(CommandEnum.command);
                new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b).send();
                Intent intent1=new Intent(Format.this,Formating.class);
                startActivity(intent1);
                break;
            case R.id.unformat:
                new CommandEnum().command=new CommandEnum().command&0xfffffe;
                b= IntToByte.getByte(CommandEnum.command);
                new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3,b).send();
                finish();
                break;
        }
    }
}
