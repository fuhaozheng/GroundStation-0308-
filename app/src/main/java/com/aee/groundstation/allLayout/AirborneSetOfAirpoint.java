package com.aee.groundstation.allLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.aee.groundstation.GlobalValue;
import com.aee.groundstation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuhz on 2017/2/12.
 */
public class AirborneSetOfAirpoint extends LinearLayout{
    Spinner spinnerOftypeOfCamera,spinnerOfnumberOfCamera,spinnerOfyawmodeOfCamera;
    SeekBar seekbarOfCamerapitch,seekbarOfCameraroll;
    TextView valueOfCamerapitch,valueOfCameraroll;
    List<String> list1=new ArrayList<>();
    List<String> list2=new ArrayList<>();
    List<String> list3=new ArrayList<>();
    ArrayAdapter<String> adapter1,adapter2,adapter3;
    public AirborneSetOfAirpoint(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_of_airpoint_airborne_setting,this);
        spinnerOftypeOfCamera=(Spinner)findViewById(R.id.spinner_of_camera_type);
        String[] s1=getResources().getStringArray(R.array.camera_type);
        for(String s:s1){list1.add(s);}
        adapter1=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOftypeOfCamera.setAdapter(adapter1);
        spinnerOftypeOfCamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.typeOfCamera=list1.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfnumberOfCamera=(Spinner)findViewById(R.id.spinner_of_camera_serial_number);
        String[] s2=getResources().getStringArray(R.array.camera_serial_number);
        for(String s:s2){list2.add(s);}
        adapter2=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,list2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfnumberOfCamera.setAdapter(adapter2);
        spinnerOfnumberOfCamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.numberOfCamera=list2.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfyawmodeOfCamera=(Spinner)findViewById(R.id.spinner_of_camera_yaw_angle_mode);
        String[] s3=getResources().getStringArray(R.array.camera_yaw_angle_mode);
        for(String s:s3){list3.add(s);}
        adapter3=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,list3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfyawmodeOfCamera.setAdapter(adapter3);
        spinnerOfyawmodeOfCamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.yawModeOfCamera=list3.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekbarOfCamerapitch=(SeekBar) findViewById(R.id.seekbar_camera_pitch_angle_value);
        seekbarOfCameraroll=(SeekBar)findViewById(R.id.seekbar_camera_roll_angle_value);
        valueOfCamerapitch=(TextView)findViewById(R.id.value_of_camera_pitch_angle_value);
        valueOfCameraroll=(TextView)findViewById(R.id.value_of_camera_roll_angle_value);
        seekbarOfCamerapitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfCamerapitch.setText(""+seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               GlobalValue.pitchOfCamera=seekBar.getProgress();
            }
        });
        seekbarOfCameraroll.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfCameraroll.setText(""+seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.rollOfCamera=seekBar.getProgress();
            }
        });
    }
}
