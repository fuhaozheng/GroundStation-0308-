package com.aee.groundstation.activity.task_load;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/20.
 */
public class SearchlightSetting extends Activity implements View.OnClickListener{
    private Button back,ensure;
    private RadioButton open,close;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlight_set);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        ensure=(Button)findViewById(R.id.ensure);
        ensure.setOnClickListener(this);
        open=(RadioButton)findViewById(R.id.open);
        close=(RadioButton)findViewById(R.id.close);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.ensure:
                if(open.isChecked()) {

                }else if(close.isChecked()){}
                break;
            case R.id.back:
                finish();
                break;

        }
    }
}
