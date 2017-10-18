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
public class Setting1 extends Activity implements View.OnClickListener{
    private Button navigation_bt,language_bt,course_set,comunication_set,visual_navigation_bt;
    private Button forward,back;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting1);
        forward=(Button)findViewById(R.id.forward);
        forward.setOnClickListener(this);

        navigation_bt=(Button)findViewById(R.id.navigation_light);
        navigation_bt.setOnClickListener(this);

        language_bt=(Button)findViewById(R.id.language_set);
        language_bt.setOnClickListener(this);

        course_set=(Button)findViewById(R.id.course_set);
        course_set.setOnClickListener(this);

        comunication_set=(Button)findViewById(R.id.communicate_config);
        comunication_set.setOnClickListener(this);

        visual_navigation_bt=(Button)findViewById(R.id.visual_navigation);
        visual_navigation_bt.setOnClickListener(this);

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.forward:
                Intent intent=new Intent(Setting1.this,Setting2.class);
                startActivity(intent);
                break;
            case R.id.navigation_light:
                Intent intent1=new Intent(Setting1.this,
                                            NavigationLightSwitch.class);
                startActivity(intent1);
                break;
            case R.id.language_set:
                Intent intent2=new Intent(Setting1.this,
                                            LanguageSetting.class);
                startActivity(intent2);
                break;
            case R.id.course_set:
                Intent intent3=new Intent(Setting1.this,
                                              CourseSetting.class);
                startActivity(intent3);
                break;
            case R.id.communicate_config:
                Intent intent4=new Intent(Setting1.this,
                                                ConfigureCommunicate.class);
                startActivity(intent4);
                break;

            case R.id.visual_navigation:
                Intent intent5=new Intent(Setting1.this,
                                         VisualNavigation.class);
                startActivity(intent5);
                break;

            case R.id.back:
                finish();
                break;

        }
    }
}
