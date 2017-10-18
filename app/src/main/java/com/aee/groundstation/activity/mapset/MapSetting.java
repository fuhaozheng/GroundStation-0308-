package com.aee.groundstation.activity.mapset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/20.
 */
public class MapSetting extends Activity implements View.OnClickListener {
    private Button hangdian,hangxian,self_fly,back;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_set);

        hangdian=(Button)findViewById(R.id.hangdian);
        hangdian.setOnClickListener(this);

        hangxian=(Button)findViewById(R.id.hangxian);
        hangxian.setOnClickListener(this);

        self_fly=(Button)findViewById(R.id.self_fly);
        self_fly.setOnClickListener(this);

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.hangdian:
                Intent intent1=new Intent();
                startActivity(intent1);
                break;

            case R.id.hangxian:
            Intent intent2=new Intent(MapSetting.this,AirRouteSetting.class);
            startActivity(intent2);
            break;

            case R.id.self_fly:
                Intent intent3=new Intent();
                startActivity(intent3);
                break;

            case R.id.back:
                finish();
                break;
        }
    }
}
