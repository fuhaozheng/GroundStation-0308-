package com.aee.groundstation.activity.airbone_camera_choose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/20.
 */
public class TakePhotoSetting extends Activity implements View.OnClickListener{
    private Button back,btn_size;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_set);
        back=(Button)findViewById(R.id.back);
        btn_size=(Button)findViewById(R.id.size);
        back.setOnClickListener(this);
        btn_size.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.size:
                startActivity(new Intent(TakePhotoSetting.this,
                                                 ImagesizeSet.class));
                break;
        }
    }
}
