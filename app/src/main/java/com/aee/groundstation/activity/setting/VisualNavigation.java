package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/21.
 */
public class VisualNavigation extends Activity implements View.OnClickListener{
    private Button back,follow_btn;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visual_navigation_mode);
        back=(Button)findViewById(R.id.back);
        follow_btn=(Button)findViewById(R.id.follow_monitor);
        follow_btn.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.follow_monitor:
                Intent intent=new Intent();
                intent.putExtra("data_return","Hello");
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
