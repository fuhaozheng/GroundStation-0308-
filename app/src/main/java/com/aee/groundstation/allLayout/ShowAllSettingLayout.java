package com.aee.groundstation.allLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2017/2/9.
 */
public class ShowAllSettingLayout extends LinearLayout{
    Button btnOfTaskParameterSet,btnOfAttitudeParameterSet,btnOfAirbornParameterSet
                                  ,btnOfMountParameterSet,btnOfPressKey,btnOfAllSettingGone;
    LinearLayout layoutOfTaskParameterSet,layoutOfAttitudeParameterSet,
                      layoutOfAirbornParameterSet,layoutOfPressKey;
    int flag1=1,flag2=1,flag3=1,flag4=1;
    public ShowAllSettingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.all_setting,this);

        btnOfTaskParameterSet=(Button) findViewById(R.id.setting_of_task_parameter);
        btnOfAttitudeParameterSet=(Button) findViewById(R.id.setting_of_attitude_parameter);
        btnOfAirbornParameterSet=(Button)findViewById(R.id.setting_of_airborne_parameter);
        btnOfMountParameterSet=(Button)findViewById(R.id.setting_of_mount_parameter);
        btnOfPressKey=(Button)findViewById(R.id.setting_of_press_key);

        layoutOfTaskParameterSet=(LinearLayout)findViewById(R.id.father_of_setting_of_task_parameter);
        layoutOfAttitudeParameterSet=(LinearLayout)findViewById(R.id.father_of_setting_of_attitude_parameter);
        layoutOfAirbornParameterSet=(LinearLayout) findViewById(R.id.father__of_setting_of_airborne_parameter);
        layoutOfPressKey=(LinearLayout) findViewById(R.id.father__of_setting_of_press_key);

        btnOfTaskParameterSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag1==1){
                    layoutOfTaskParameterSet.setVisibility(View.VISIBLE);
                    flag1=0;
                }else if(flag1==0){
                    layoutOfTaskParameterSet.setVisibility(View.GONE);
                    flag1=1;
                }
            }
        });
        btnOfAttitudeParameterSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag2==1){
                    layoutOfAttitudeParameterSet.setVisibility(View.VISIBLE);
                    flag2=0;
                }else if(flag2==0){
                    layoutOfAttitudeParameterSet.setVisibility(View.GONE);
                    flag2=1;
                }
            }
        });
        btnOfAirbornParameterSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag3==1){
                    layoutOfAirbornParameterSet.setVisibility(View.VISIBLE);
                    flag3=0;
                }else if(flag3==0){
                    layoutOfAirbornParameterSet.setVisibility(View.GONE);
                    flag3=1;
                }
            }
        });
        btnOfPressKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag4==1){
                    layoutOfPressKey.setVisibility(View.VISIBLE);
                    flag4=0;
                }else if(flag4==0){
                    layoutOfPressKey.setVisibility(View.GONE);
                    flag4=1;
                }
            }
        });
        btnOfMountParameterSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
