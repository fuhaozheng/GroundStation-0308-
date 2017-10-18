package com.aee.groundstation.activity.task_load;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/20.
 */
public class TaskLoadSetting extends Activity implements View.OnClickListener{
    private Button saving_set,searchlight_set,back;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_load_set);
        saving_set=(Button)findViewById(R.id.saving_set);
        saving_set.setOnClickListener(this);
        searchlight_set=(Button)findViewById(R.id.searchlight_set);
        searchlight_set.setOnClickListener(this);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.saving_set:
                Intent intent1=new Intent(TaskLoadSetting.this,SavingSetting.class);
                startActivity(intent1);
                break;
            case R.id.searchlight_set:
                Intent intent2=new Intent(TaskLoadSetting.this,SearchlightSetting.class);
                startActivity(intent2);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
