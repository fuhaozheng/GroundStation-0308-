package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/20.
 */
public class Setting2 extends Activity implements View.OnClickListener{
    private Button back;
    private Button format_bt,resume_default_bt,time_set_bt,software_message_bt;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting2);
        back=(Button)findViewById(R.id.returnback);
        back.setOnClickListener(this);

        format_bt=(Button)findViewById(R.id.format);
        format_bt.setOnClickListener(this);

        resume_default_bt=(Button)findViewById(R.id.resume_default);
        resume_default_bt.setOnClickListener(this);

        time_set_bt=(Button)findViewById(R.id.time_set);
        time_set_bt.setOnClickListener(this);

        software_message_bt=(Button)findViewById(R.id.software_message);
        software_message_bt.setOnClickListener(this);

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.returnback:
                Intent intent=new Intent(Setting2.this,Setting1.class);
                startActivity(intent);
                break;
            case R.id.format:
                Intent intent1=new Intent(Setting2.this,Format.class);
                startActivity(intent1);
                break;
            case R.id.resume_default:
                Intent intent2=new Intent(Setting2.this,ResumeDefault.class);
                startActivity(intent2);
                break;
            case R.id.time_set:
                Intent intent3=new Intent(Setting2.this,TimeSetting.class);
                startActivity(intent3);
                break;
            case R.id.software_message:
                Intent intent4=new Intent(Setting2.this,SoftwareEditionMessage.class);
                startActivity(intent4);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
