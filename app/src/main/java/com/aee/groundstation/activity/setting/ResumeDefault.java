package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aee.groundstation.IntToByte;
import com.aee.groundstation.R;
import com.aee.groundstation.broadcast.MyAdmin;
import com.aee.groundstation.communication.CommandEnum;
import com.aee.groundstation.communication.SendingSerialportMessage;

/**
 * Created by fuhz on 2016/9/21.
 */
public class ResumeDefault extends Activity implements View.OnClickListener{
    private Button resuming_bt,unresume_bt;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_message);
        resuming_bt=(Button)findViewById(R.id.resume_yes);
        unresume_bt=(Button)findViewById(R.id.resume_no);
        resuming_bt.setOnClickListener(this);
        unresume_bt.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.resume_yes:
                CommandEnum.command= CommandEnum.command|0x080000;
                new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3, IntToByte.getByte(CommandEnum.command)).send();
                Intent intent1=new Intent(ResumeDefault.this,ResumingDefault.class);
                startActivity(intent1);
                break;
            case R.id.resume_no:
                finish();
                break;
        }
    }

}
