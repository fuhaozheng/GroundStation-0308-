package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/21.
 */
public class CourseSetting extends Activity implements View.OnClickListener{
    private Button back;
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.heading_set);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
