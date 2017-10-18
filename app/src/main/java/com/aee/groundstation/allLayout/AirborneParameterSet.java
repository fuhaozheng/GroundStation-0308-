package com.aee.groundstation.allLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.aee.groundstation.GlobalValue;
import com.aee.groundstation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuhz on 2017/2/9.
 */
public class AirborneParameterSet extends LinearLayout{
    Spinner spinnerOfCameraPitch,spinnerOfCameraStandard,spinnerOfImageSize,spinnerOfCameraParameter;
    ArrayAdapter<String> adapter1,adapter2,adapter3,adapter4,adapter5;
    List<String> l1,l2,l3,l4;
    RadioGroup rgOfCyclecover;
    RadioButton cycleCoverYes,cycleCoverNo;
    CheckBox timeOfwatermark,latilongiOfwatermark,hangxianOfwatermark;
    public AirborneParameterSet(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_of_setting_of_airborne_parameter,this);

        spinnerOfCameraPitch=(Spinner) findViewById(R.id.spinner_of_camera_pitch);
        String[] s1=getResources().getStringArray(R.array.camera_pitch_limit);
        l1=new ArrayList<>();
        for(String s:s1){
            l1.add(s);
        }
        adapter1=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,l1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfCameraPitch.setAdapter(adapter1);
        spinnerOfCameraPitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                }else if(position==1){
                    GlobalValue.cameraPitchLimit=90;
                }else if(position==2){
                    GlobalValue.cameraPitchLimit=120;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfCameraStandard=(Spinner)findViewById(R.id.spinner_of_camera_standard);
        String[] s2=getResources().getStringArray(R.array.camera_standard);
        l2=new ArrayList<>();
        for(String s:s2){
            l2.add(s);
        }
        adapter2=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,l2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfCameraStandard.setAdapter(adapter2);
        spinnerOfCameraStandard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.cameraStandard=l2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfImageSize=(Spinner)findViewById(R.id.spinner_of_image_size);
        String[] s3=getResources().getStringArray(R.array.image_size);
        l3=new ArrayList<>();
        for(String s:s3){
            l3.add(s);
        }
        adapter3=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,l3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfImageSize.setAdapter(adapter3);
        spinnerOfImageSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(l3.get(position).equals("空")){

                }else {
                    GlobalValue.imageSize=Integer.parseInt(l3.get(position));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfCameraParameter=(Spinner) findViewById(R.id.spinner_of_camera_parameter);
        String[] s4=getResources().getStringArray(R.array.camera_parameter);
        l4=new ArrayList<>();
        for(String s:s4){
            l4.add(s);
        }
        adapter4=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,l4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfCameraParameter.setAdapter(adapter4);
        spinnerOfCameraParameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(l4.get(position).equals("空")){

                }else {
                    GlobalValue.imageParameter=Integer.parseInt(l4.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rgOfCyclecover=(RadioGroup) findViewById(R.id.radiogroup_of_cycle_cover);
        cycleCoverYes=(RadioButton) findViewById(R.id.yes_of_cycle_cover);
        cycleCoverNo=(RadioButton)findViewById(R.id.no_of_cycle_cover);
        rgOfCyclecover.setOnCheckedChangeListener(new OnCheckedChangeListenerImp());

        timeOfwatermark=(CheckBox)findViewById(R.id.time_of_watermark);
        latilongiOfwatermark=(CheckBox)findViewById(R.id.longitude_latidude__of_watermark);
        hangxianOfwatermark=(CheckBox)findViewById(R.id.name_hangxian__of_watermark);
        timeOfwatermark.setOnCheckedChangeListener(new CheckBoxImp());
    }
    class OnCheckedChangeListenerImp implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(cycleCoverYes.getId()==checkedId){
                GlobalValue.isCycleCover=true;
            }else if(cycleCoverNo.getId()==checkedId){
                GlobalValue.isCycleCover=false;
            }
        }
    }
    class CheckBoxImp implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
           if(timeOfwatermark.isChecked()){
               GlobalValue.cameraWatermark+=1;
           } if(latilongiOfwatermark.isChecked()){
               GlobalValue.cameraWatermark+=2;
           } if(hangxianOfwatermark.isChecked()){
               GlobalValue.cameraWatermark+=3;
           }
        }
    }
}
