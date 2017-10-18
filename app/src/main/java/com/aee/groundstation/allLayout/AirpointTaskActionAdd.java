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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuhz on 2017/2/12.
 */
public class AirpointTaskActionAdd extends LinearLayout{
    SeekBar seekbarOfDelay,seekbarOfActionyaw,seekabrOfActionFlyingheight,seekbarOfActionUpspeed,
            seekbarOfActionDownspeed,seekbarOfActionFlyingspeed,seekbarOfActionTakephotoDelay,seekbarOfCameraYaw
            ,seekbarOfCameraPitch;
    TextView valueOfDelay,valueOfActionyaw,valueOfActionFlyingheight, valueOfActionUpspeed,valueOfActionDownspeed,
            valueOfActionFlyingspeed,valueOfActionTakephotoDelay,valueOfCameraYaw,valueOfCameraPitch;
    Spinner spinnerOfActionInterest,spinnerOfActionTakeptoto,spinnerOfActionRecordVidio,spinnerOfFormatcamera,
            spinnerOfCameraNumber,spinnerOfCameraInterest,spinnerOfSaving,spinnerOfHook,spinnerOfDiaocang,spinnerOfHanhuaqi,
            spinnerOfSearchLight,spinnerOfJiguang,spinnerOfWirelessRelay;
    List<String> listOfActionInterest=new ArrayList<>();
    List<String> listOfActionTakeptoto=new ArrayList<>();
    List<String> listOfActionRecordVidio=new ArrayList<>();
    List<String> listOfFormatcamera=new ArrayList<>();
    List<String> listOfCameraNumber=new ArrayList<>();
    List<String> listOfCameraInterest=new ArrayList<>();
    List<String> listOfSaving=new ArrayList<>();
    List<String> listOfHook=new ArrayList<>();
    List<String> listOfDiaocang=new ArrayList<>();
    List<String> listOfHanhuaqi=new ArrayList<>();
    List<String> listOfSearchLight=new ArrayList<>();
    List<String> listOfJiguang=new ArrayList<>();
    List<String> listOfWirelessRelay=new ArrayList<>();
    ArrayAdapter<String> adapterOfActionInterest,adapterOfActionTakeptoto,adapterOfActionRecordVidio,adapterOfFormatcamera,
            adapterOfCameraNumber,adapterOfCameraInterest,adapterOfSaving,adapterOfHook,adapterOfDiaocang,
            adapterOfHanhuaqi,adapterOfSearchLight,adapterOfJiguang,adapterOfWirelessRelay;
    public AirpointTaskActionAdd(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_of_airpoint_task_action_adding_list,this);

        seekbarOfDelay=(SeekBar) findViewById(R.id.action_seekbar_of_delay);
        valueOfDelay=(TextView) findViewById(R.id.action_value_of_delay);
        seekbarOfDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfDelay.setText(seekBar.getProgress()+"s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.actionOfDelay=seekBar.getProgress();
                GlobalValue.listOfAction.add("延时"+GlobalValue.actionOfDelay);
            }
        });

        seekbarOfActionyaw=(SeekBar) findViewById(R.id.action_seekbar_of_yaw);
        valueOfActionyaw=(TextView)findViewById(R.id.action_value_of_yaw);
        seekbarOfActionyaw.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfActionyaw.setText(""+seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.actionOfYaw=seekBar.getProgress();
                GlobalValue.listOfAction.add("偏航角"+GlobalValue.actionOfYaw);
            }
        });

        spinnerOfActionInterest=(Spinner) findViewById(R.id.action_spinner_of_intrest);
        String[] s1=getResources().getStringArray(R.array.action_interest);
        for(String s:s1){listOfActionInterest.add(s);}
        adapterOfActionInterest=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfActionInterest);
        adapterOfActionInterest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfActionInterest.setAdapter(adapterOfActionInterest);
        spinnerOfActionInterest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.actionOfInterest="兴趣点"+listOfActionInterest.get(position);
                GlobalValue.listOfAction.add(GlobalValue.actionOfInterest);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekabrOfActionFlyingheight=(SeekBar) findViewById(R.id.action_seekbar_of_flyingHeight);
        valueOfActionFlyingheight=(TextView)findViewById(R.id.action_value_of_flyingHeight);
        seekabrOfActionFlyingheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfActionFlyingheight.setText(seekBar.getProgress()+"m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.actionOfFlyingheight=seekBar.getProgress();
                GlobalValue.listOfAction.add("飞行高度"+GlobalValue.actionOfFlyingheight);
            }
        });

        seekbarOfActionUpspeed=(SeekBar)findViewById(R.id.action_seekbar_of_upspeed);
        valueOfActionUpspeed=(TextView)findViewById(R.id.action_value_of_upspeed);
        seekbarOfActionUpspeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfActionUpspeed.setText(seekBar.getProgress()+"m/s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                   GlobalValue.actionOfUpspeed=seekBar.getProgress();
                   GlobalValue.listOfAction.add("上升速度"+GlobalValue.actionOfUpspeed);
            }
        });

        seekbarOfActionDownspeed=(SeekBar)findViewById(R.id.action_seekbar_of_downspeed);
        valueOfActionDownspeed=(TextView)findViewById(R.id.action_value_of_downspeed);
        seekbarOfActionDownspeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfActionDownspeed.setText(seekBar.getProgress()+"m/s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.actionOfDownspeed=seekBar.getProgress();
                GlobalValue.listOfAction.add("下降速度"+GlobalValue.actionOfDownspeed);
            }
        });

        seekbarOfActionFlyingspeed=(SeekBar)findViewById(R.id.action_seekbar_of_flyingspeed);
        valueOfActionFlyingspeed=(TextView)findViewById(R.id.action_value_of_flyingspeed);
        seekbarOfActionFlyingspeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfActionFlyingspeed.setText(seekBar.getProgress()+"m/s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.actionOfFlyingspeed=seekBar.getProgress();
                GlobalValue.listOfAction.add("飞行速度"+GlobalValue.actionOfFlyingspeed);
            }
        });

        spinnerOfActionTakeptoto=(Spinner) findViewById(R.id.action_takephoto);
        String[] s2=getResources().getStringArray(R.array.action_takephoto);
        for(String s:s2){listOfActionTakeptoto.add(s);}
        adapterOfActionTakeptoto=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfActionTakeptoto);
        adapterOfActionTakeptoto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfActionTakeptoto.setAdapter(adapterOfActionTakeptoto);
        spinnerOfActionTakeptoto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfActionTakeptoto.get(position);
                if(s.equals("空")){

                }else if(s.equals("拍照开始")){
                    GlobalValue.actionOfTakephoto=true;
                    GlobalValue.listOfAction.add("拍照"+GlobalValue.actionOfTakephoto);
                }else if(s.equals("拍照结束")){
                    GlobalValue.actionOfTakephoto=false;
                    GlobalValue.listOfAction.add("拍照"+GlobalValue.actionOfTakephoto);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfActionRecordVidio=(Spinner)findViewById(R.id.action_recordvideo);
        String[] s3=getResources().getStringArray(R.array.action_recordvideo);
        for(String s:s3){listOfActionRecordVidio.add(s);}
        adapterOfActionRecordVidio=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfActionRecordVidio);
        adapterOfActionRecordVidio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfActionRecordVidio.setAdapter(adapterOfActionRecordVidio);
        spinnerOfActionRecordVidio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfActionRecordVidio.get(position);
                if(s.equals("空")){

                }else if(s.equals("录像开始")){
                    GlobalValue.actionOfRecordvideo=true;
                    GlobalValue.listOfAction.add("录像"+GlobalValue.actionOfRecordvideo);
                }else if(s.equals("录像结束")){
                    GlobalValue.actionOfRecordvideo=false;
                    GlobalValue.listOfAction.add("录像"+GlobalValue.actionOfRecordvideo);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekbarOfActionTakephotoDelay=(SeekBar)findViewById(R.id.action_seekbar_of_delay_takephoto);
        valueOfActionTakephotoDelay=(TextView)findViewById(R.id.action_value_of_delay_takephoto);
        seekbarOfActionTakephotoDelay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfActionTakephotoDelay.setText(seekBar.getProgress()+"s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.actionOfDelaytakePhoto=seekBar.getProgress();
                GlobalValue.listOfAction.add("延时拍照"+GlobalValue.actionOfDelaytakePhoto);
            }
        });

        seekbarOfCameraYaw=(SeekBar)findViewById(R.id.action_seekbar_of_camera_yaw);
        valueOfCameraYaw=(TextView)findViewById(R.id.action_value_of_camera_yaw);
        seekbarOfCameraYaw.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfCameraYaw.setText(seekBar.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.actionOfCamerayaw=seekBar.getProgress();
                GlobalValue.listOfAction.add("相机偏航角"+GlobalValue.actionOfCamerayaw);
            }
        });

        seekbarOfCameraPitch=(SeekBar)findViewById(R.id.action_seekbar_of_camera_pitch);
        valueOfCameraPitch=(TextView)findViewById(R.id.action_value_of_camera_pitch);
        seekbarOfCameraPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfCameraPitch.setText(seekBar.getProgress()+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                GlobalValue.actionOfCamerapitch=seekBar.getProgress();
                GlobalValue.listOfAction.add("相机俯仰角"+GlobalValue.actionOfCamerapitch);
            }
        });

        spinnerOfFormatcamera=(Spinner) findViewById(R.id.action_camera_format);
        String[] s4=getResources().getStringArray(R.array.action_cameraformat);
        for(String s:s4){listOfFormatcamera.add(s);}
        adapterOfFormatcamera=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfFormatcamera);
        adapterOfFormatcamera.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfFormatcamera.setAdapter(adapterOfFormatcamera);
        spinnerOfFormatcamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfFormatcamera.get(position);
                if(s.equals("空")){
                }else if(s.equals("格式化")){
                    GlobalValue.actionOfFormatcamera=true;
                    GlobalValue.listOfAction.add("格式化"+GlobalValue.actionOfFormatcamera);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfCameraNumber=(Spinner) findViewById(R.id.action_camera_number);
        String[] s5=getResources().getStringArray(R.array.action_cameranumber);
        for(String s:s5){listOfCameraNumber.add(s);}
        adapterOfCameraNumber=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfCameraNumber);
        adapterOfCameraNumber.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfCameraNumber.setAdapter(adapterOfCameraNumber);
        spinnerOfCameraNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfCameraNumber.get(position);
                if(s.equals("空")){

                }else{
                    GlobalValue.actionOfCameranumber=s;
                    GlobalValue.listOfAction.add("相机编号"+GlobalValue.actionOfCameranumber);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfCameraInterest=(Spinner)findViewById(R.id.action_camera_interest);
        String[] s6=getResources().getStringArray(R.array.action_camerainterest);
        for(String s:s6){listOfCameraInterest.add(s);}
        adapterOfCameraInterest=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfCameraInterest);
        adapterOfCameraInterest.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfCameraInterest.setAdapter(adapterOfCameraInterest);
        spinnerOfCameraInterest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfCameraInterest.get(position);
                if(s.equals("空")){

                }else {
                    GlobalValue.actionOfCameraInterest=s;
                    GlobalValue.listOfAction.add("相机兴趣点"+GlobalValue.actionOfCameraInterest);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfSaving=(Spinner)findViewById(R.id.action_putdown_location);
        String[] s7=getResources().getStringArray(R.array.action_putdownlocation);
        for(String s:s7){listOfSaving.add(s);}
        adapterOfSaving=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfSaving);
        adapterOfSaving.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfSaving.setAdapter(adapterOfSaving);
        spinnerOfSaving.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfSaving.get(position);
                if(s.equals("空")){

                }else {
                    GlobalValue.actionOfSaving=s;
                    GlobalValue.listOfAction.add("救生"+GlobalValue.actionOfSaving);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfHook=(Spinner)findViewById(R.id.action_hook);
        String[] s8=getResources().getStringArray(R.array.action_hook);
        for(String s:s8){listOfHook.add(s);}
        adapterOfHook=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfHook);
        adapterOfHook.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfHook.setAdapter(adapterOfHook);
        spinnerOfHook.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfHook.get(position);
                if(s.equals("空")){

                }else if(s.equals("挂钩投放")){
                    GlobalValue.actionOfHook=true;
                    GlobalValue.listOfAction.add("挂钩"+GlobalValue.actionOfHook);
                }else if(s.equals("挂钩回收")){
                    GlobalValue.actionOfHook=false;
                    GlobalValue.listOfAction.add("挂钩"+GlobalValue.actionOfHook);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfDiaocang=(Spinner)findViewById(R.id.action_diaocang);
        String[] s9=getResources().getStringArray(R.array.action_diaocang);
        for(String s:s9){listOfDiaocang.add(s);}
        adapterOfDiaocang=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfDiaocang);
        adapterOfDiaocang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfDiaocang.setAdapter(adapterOfDiaocang);
        spinnerOfDiaocang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfDiaocang.get(position);
                if(s.equals("空")){

                }else if(s.equals("吊舱投放")){
                    GlobalValue.actionOfDiaocang=true;
                    GlobalValue.listOfAction.add("吊舱"+GlobalValue.actionOfDiaocang);
                }else if(s.equals("吊舱回收")){
                    GlobalValue.actionOfDiaocang=false;
                    GlobalValue.listOfAction.add("吊舱"+GlobalValue.actionOfDiaocang);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfHanhuaqi=(Spinner)findViewById(R.id.action_hanhuaqi);
        String[] s10=getResources().getStringArray(R.array.action_hanhuaqi);
        for(String s:s10){listOfHanhuaqi.add(s);}
        adapterOfHanhuaqi=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfHanhuaqi);
        adapterOfHanhuaqi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfHanhuaqi.setAdapter(adapterOfHanhuaqi);
        spinnerOfHanhuaqi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfHanhuaqi.get(position);
                if(s.equals("空")){

                }else if(s.equals("喊话器打开")){
                    GlobalValue.actionOfHanhuaqi=true;
                    GlobalValue.listOfAction.add("喊话器"+GlobalValue.actionOfHanhuaqi);
                }else if(s.equals("喊话器关闭")){
                    GlobalValue.actionOfHanhuaqi=false;
                    GlobalValue.listOfAction.add("喊话器"+GlobalValue.actionOfHanhuaqi);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfSearchLight=(Spinner)findViewById(R.id.action_searchlight);
        String[] s11=getResources().getStringArray(R.array.action_searchlight);
        for(String s:s11){listOfSearchLight.add(s);}
        adapterOfSearchLight=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfSearchLight);
        adapterOfSearchLight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfSearchLight.setAdapter(adapterOfSearchLight);
        spinnerOfSearchLight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfSearchLight.get(position);
                if(s.equals("空")){

                }else if(s.equals("探照灯打开")){
                    GlobalValue.actionOfSearchlight=true;
                    GlobalValue.listOfAction.add("探照灯"+GlobalValue.actionOfSearchlight);
                }else if(s.equals("探照灯关闭")){
                    GlobalValue.actionOfSearchlight=false;
                    GlobalValue.listOfAction.add("探照灯"+GlobalValue.actionOfSearchlight);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfJiguang=(Spinner)findViewById(R.id.action_jiguangzhimang);
        String[] s12=getResources().getStringArray(R.array.action_jiguangzhimang);
        for(String s:s12){listOfJiguang.add(s);}
        adapterOfJiguang=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfJiguang);
        adapterOfJiguang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfJiguang.setAdapter(adapterOfJiguang);
        spinnerOfJiguang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfHook.get(position);
                if(s.equals("空")){

                }else if(s.equals("激光打开")){
                    GlobalValue.actionOfJiguangzhimang=true;
                    GlobalValue.listOfAction.add("激光"+GlobalValue.actionOfJiguangzhimang);
                }else if(s.equals("激光关闭")){
                    GlobalValue.actionOfJiguangzhimang=false;
                    GlobalValue.listOfAction.add("激光"+GlobalValue.actionOfJiguangzhimang);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerOfWirelessRelay=(Spinner)findViewById(R.id.action_wirelessrelay);
        String[] s13=getResources().getStringArray(R.array.action_wirelessrelay);
        for(String s:s13){listOfWirelessRelay.add(s);}
        adapterOfWirelessRelay=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listOfWirelessRelay);
        adapterOfWirelessRelay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfWirelessRelay.setAdapter(adapterOfWirelessRelay);
        spinnerOfWirelessRelay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=listOfWirelessRelay.get(position);
                if(s.equals("空")){

                }else if(s.equals("中继打开")){
                    GlobalValue.actionOfWirelessrelay=true;
                    GlobalValue.listOfAction.add("中继"+GlobalValue.actionOfWirelessrelay);
                }else if(s.equals("中继关闭")){
                    GlobalValue.actionOfWirelessrelay=false;
                    GlobalValue.listOfAction.add("中继"+GlobalValue.actionOfWirelessrelay);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
