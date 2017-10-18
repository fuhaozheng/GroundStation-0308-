package com.aee.groundstation.activity.airbone_camera_choose;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aee.groundstation.IntToByte;
import com.aee.groundstation.R;
import com.aee.groundstation.communication.CommandEnum;
import com.aee.groundstation.communication.SendingSerialportMessage;
import com.aee.groundstation.widget.CustomViewGroup;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by fuhz on 2016/10/17.
 */
public class PNScale extends Activity implements View.OnClickListener {
    private Button save, cancel;
    private RadioButton button_pal, button_nstc;
    private RelativeLayout pal_r1, pal_r2, nstc_r1, nstc_r2;
    private CustomViewGroup pal50, pal25, nstc60, nstc30;
    private TextView tv;
    private boolean b=true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scale_pn);

        button_pal = (RadioButton) findViewById(R.id.pal);
        button_nstc = (RadioButton) findViewById(R.id.nstc);
        pal50 = (CustomViewGroup) findViewById(R.id.pal50);
        pal25 = (CustomViewGroup) findViewById(R.id.pal25);
        nstc60 = (CustomViewGroup) findViewById(R.id.nstc60);
        nstc30 = (CustomViewGroup) findViewById(R.id.nstc30);
        tv = (TextView) findViewById(R.id.content);
        save=(Button)findViewById(R.id.save11);
        cancel = (Button) findViewById(R.id.cancel);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
        button_pal.setOnClickListener(this);
        button_nstc.setOnClickListener(this);
        pal50.setOnClickListener(this);
        pal25.setOnClickListener(this);
        nstc60.setOnClickListener(this);
        nstc30.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent=new Intent(PNScale.this,AirborneTelecameraSetting.class);
        switch (v.getId()) {
            case R.id.pal:
                CommandEnum.command= CommandEnum.command&0xfffff7;
                pal50.set("1080/50", Color.parseColor("#00FFFC"), Color.parseColor("#f7f7f7"), R.drawable.white, true);
                pal25.set("1080/25", Color.parseColor("#00FFFC"), Color.parseColor("#f7f7f7"), R.drawable.white, true);
                nstc60.set("1080/60", Color.GRAY, Color.GRAY, R.drawable.grey, false);
                nstc30.set("1080/30", Color.GRAY, Color.GRAY, R.drawable.grey, false);
                break;
            case R.id.nstc:
                 CommandEnum.command= CommandEnum.command|0x000008;
                nstc60.set("1080/60", Color.parseColor("#00FFFC"), Color.parseColor("#f7f7f7"), R.drawable.white, true);
                nstc30.set("1080/30", Color.parseColor("#00FFFC"), Color.parseColor("#f7f7f7"), R.drawable.white, true);
                pal50.set("1080/50", Color.GRAY, Color.GRAY, R.drawable.grey, false);
                pal25.set("1080/25", Color.GRAY, Color.GRAY, R.drawable.grey, false);
                break;
            case R.id.pal50:
                if(b){
                     CommandEnum.command= CommandEnum.command&0xff7fff;
                     CommandEnum.command= CommandEnum.command&0xffbfff;
                     CommandEnum.command= CommandEnum.command&0xffdfff;
                    pal50.setBackground(R.drawable.blue);
                    pal25.setBackground(R.drawable.white);
                    tv.setText("1920x1080p 50f");
                    b=false;
                }else{
                     CommandEnum.command= CommandEnum.command&0xff7fff;
                     CommandEnum.command= CommandEnum.command&0xffbfff;
                     CommandEnum.command= CommandEnum.command|0x002000;
                    pal50.setBackground(R.drawable.white);
                    pal25.setBackground(R.drawable.white);
                    tv.setText("1920x1080p 25f");
                    b=true;
                }

                break;
            case R.id.pal25:
                if(!b){
                     CommandEnum.command= CommandEnum.command&0xff7fff;
                     CommandEnum.command= CommandEnum.command&0xffbfff;
                     CommandEnum.command= CommandEnum.command|0x002000;
                    pal25.setBackground(R.drawable.blue);
                    pal50.setBackground(R.drawable.white);
                    tv.setText("1920x1080p 25f");
                    b=true;
                }else{
                     CommandEnum.command= CommandEnum.command&0xff7fff;
                     CommandEnum.command= CommandEnum.command&0xffbfff;
                     CommandEnum.command= CommandEnum.command|0x002000;
                    pal25.setBackground(R.drawable.white);
                    pal50.setBackground(R.drawable.white);
                    tv.setText("1920x1080p 25f");
                    b=false;
                }
                break;
            case R.id.nstc60:
                if(b){
                     CommandEnum.command= CommandEnum.command&0xff7fff;
                     CommandEnum.command= CommandEnum.command|0x004000;
                     CommandEnum.command= CommandEnum.command&0xffdfff;
                    nstc60.setBackground(R.drawable.blue);
                    nstc30.setBackground(R.drawable.white);
                    tv.setText("1920x1080p 60f");
                    b=false;
                }else{
                     CommandEnum.command= CommandEnum.command&0xff7fff;
                     CommandEnum.command= CommandEnum.command&0xffbfff;
                     CommandEnum.command= CommandEnum.command|0x002000;
                    nstc60.setBackground(R.drawable.white);
                    nstc30.setBackground(R.drawable.white);
                    tv.setText("1920x1080p 25f");
                    b=true;
                }
                break;
            case R.id.nstc30:
                if(!b){
                     CommandEnum.command= CommandEnum.command|0x008000;
                     CommandEnum.command= CommandEnum.command&0xffbfff;
                     CommandEnum.command= CommandEnum.command&0xffdfff;
                    nstc30.setBackground(R.drawable.blue);
                    nstc60.setBackground(R.drawable.white);
                    tv.setText("1920x1080p 30f");
                    b=true;
                }else{
                     CommandEnum.command= CommandEnum.command&0xff7fff;
                     CommandEnum.command= CommandEnum.command&0xffbfff;
                     CommandEnum.command= CommandEnum.command|0x002000;
                    nstc60.setBackground(R.drawable.white);
                    nstc30.setBackground(R.drawable.white);
                    tv.setText("1920x1080p 25f");
                    b=false;
                }
                break;
            case R.id.save11:
                new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_ONEKEY_FLY, 3, IntToByte.getByte(CommandEnum.command)).send();
                startActivity(intent);
                break;
            case R.id.cancel:
                startActivity(intent);
                finish();
                break;


        }
    }


}
