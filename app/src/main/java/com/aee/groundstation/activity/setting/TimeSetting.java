package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aee.groundstation.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fuhz on 2016/11/29.
 */
public class TimeSetting extends Activity implements View.OnClickListener{
    private Button add,subtract;
    private Button save,back;
    private EditText year,month,day,hour,minute;
    private int focus=-1;
    private boolean isRunnian=false;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timesetting);
        init();
    }
    private void init(){
        save=(Button) findViewById(R.id.saving);
        save.setOnClickListener(this);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(this);
        add=(Button)findViewById(R.id.add);
        add.setOnClickListener(this);
        subtract=(Button)findViewById(R.id.subtract);
        subtract.setOnClickListener(this);
        year=(EditText)findViewById(R.id.year);
        year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    focus=0;
                }else{
                    focus=-1;
                }
            }
        });
        month=(EditText)findViewById(R.id.month);
        month.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    focus=1;
                }else{
                    focus=-1;
                }
            }
        });
        day=(EditText)findViewById(R.id.day);
        day.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    focus=2;
                }else{
                    focus=-1;
                }
            }
        });
        hour=(EditText)findViewById(R.id.hour);
        hour.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    focus=3;
                }else{
                    focus=-1;
                }
            }
        });
        minute=(EditText)findViewById(R.id.minute);
        minute.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    focus=4;
                }else{
                    focus=-1;
                }
            }
        });
        //为时间赋初始值
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date=new Date(System.currentTimeMillis());
        String str=format.format(date);
        String[] string=str.split("-");
        year.setText(string[0]);
        month.setText(string[1]);
        day.setText(string[2]);
        hour.setText(string[3]);
        minute.setText(string[4]);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add:
                switch (focus){
                    case 0:
                        int yearV=Integer.parseInt(year.getText().toString());
                        yearV++;
                        year.setText(""+yearV);
                        break;
                    case 1:
                        int monthV=Integer.parseInt(month.getText().toString());
                        if(monthV==12){
                            monthV=1;
                        }else{
                            monthV++;
                        }
                        judge(monthV,month);
                        break;
                    case 2:
                        boolean b=judgeRunnian(Integer.parseInt(year.getText().toString()));
                        int dayV=Integer.parseInt(day.getText().toString());
                        //闰年的情况下
                        if(b){
                            //月份为31天的情况
                            if((Integer.parseInt(month.getText().toString())==1)||
                                    (Integer.parseInt(month.getText().toString())==3)||
                                    (Integer.parseInt(month.getText().toString())==5)||
                                    (Integer.parseInt(month.getText().toString())==7)||
                                    (Integer.parseInt(month.getText().toString())==8)||
                                    (Integer.parseInt(month.getText().toString())==10)||
                                    (Integer.parseInt(month.getText().toString())==12)){
                                if(dayV==31){
                                    dayV=1;
                                }else{
                                    dayV++;
                                }
                                judge(dayV,day);
                                //月份为30天的情况
                            }else if((Integer.parseInt(month.getText().toString())==4)||
                                    (Integer.parseInt(month.getText().toString())==6)||
                                    (Integer.parseInt(month.getText().toString())==9)||
                                    (Integer.parseInt(month.getText().toString())==11)){
                                if(dayV==30){
                                    dayV=1;
                                }else{
                                    dayV++;
                                }
                                judge(dayV,day);
                                //2月为29天
                            }else {
                                if(dayV>=29){
                                    dayV=1;
                                }else{
                                    dayV++;
                                }
                                judge(dayV,day);
                            }
                            //不是闰年
                        }else{
                            //月份为31天的情况
                            if((Integer.parseInt(month.getText().toString())==1)||
                                    (Integer.parseInt(month.getText().toString())==3)||
                                    (Integer.parseInt(month.getText().toString())==5)||
                                    (Integer.parseInt(month.getText().toString())==7)||
                                    (Integer.parseInt(month.getText().toString())==8)||
                                    (Integer.parseInt(month.getText().toString())==10)||
                                    (Integer.parseInt(month.getText().toString())==12)){
                                if(dayV==31){
                                    dayV=1;
                                }else{
                                    dayV++;
                                }
                                judge(dayV,day);
                                //月份为30天的情况
                            }else if((Integer.parseInt(month.getText().toString())==4)||
                                    (Integer.parseInt(month.getText().toString())==6)||
                                    (Integer.parseInt(month.getText().toString())==9)||
                                    (Integer.parseInt(month.getText().toString())==11)){
                                if(dayV==30){
                                    dayV=1;
                                }else{
                                    dayV++;
                                }
                                judge(dayV,day);
                                //2月为28天
                            }else {
                                if(dayV>=28){
                                    dayV=1;
                                }else{
                                    dayV++;
                                }
                                judge(dayV,day);
                            }
                        }
                        break;
                    case 3:
                        int hourV=Integer.parseInt(hour.getText().toString());
                        if(hourV==23){
                            hourV=0;
                        }else{
                            hourV++;
                        }
                        judge(hourV,hour);
                        break;
                    case 4:
                        int minuteV=Integer.parseInt(minute.getText().toString());
                        if(minuteV==59){
                            minuteV=0;
                        }else{
                            minuteV++;
                        }
                        judge(minuteV,minute);
                        break;
                }
                break;
            case R.id.subtract:
                switch (focus){
                    case 0:
                        int yearV=Integer.parseInt(year.getText().toString());
                        yearV--;
                        year.setText(""+yearV);
                        break;
                    case 1:
                        int monthV=Integer.parseInt(month.getText().toString());
                        if(monthV==1){
                            monthV=12;
                        }else{
                            monthV--;
                        }
                        judge(monthV,month);
                        break;
                    case 2:
                        boolean b=judgeRunnian(Integer.parseInt(year.getText().toString()));
                        int dayV=Integer.parseInt(day.getText().toString());
                        //闰年的情况下
                        if(b){
                            //月份为31天的情况
                            if((Integer.parseInt(month.getText().toString())==1)||
                                    (Integer.parseInt(month.getText().toString())==3)||
                                    (Integer.parseInt(month.getText().toString())==5)||
                                    (Integer.parseInt(month.getText().toString())==7)||
                                    (Integer.parseInt(month.getText().toString())==8)||
                                    (Integer.parseInt(month.getText().toString())==10)||
                                    (Integer.parseInt(month.getText().toString())==12)){
                                if(dayV==1){
                                    dayV=31;
                                }else{
                                    dayV--;
                                }
                                judge(dayV,day);
                                //月份为30天的情况
                            }else if((Integer.parseInt(month.getText().toString())==4)||
                                    (Integer.parseInt(month.getText().toString())==6)||
                                    (Integer.parseInt(month.getText().toString())==9)||
                                    (Integer.parseInt(month.getText().toString())==11)){
                                if(dayV==1){
                                    dayV=30;
                                }else{
                                    dayV--;
                                }
                                judge(dayV,day);
                                //2月为29天
                            }else {
                                if(dayV==1){
                                    dayV=29;
                                }else{
                                    dayV--;
                                }
                                judge(dayV,day);
                            }
                            //不是闰年
                        }else{
                            //月份为31天的情况
                            if((Integer.parseInt(month.getText().toString())==1)||
                                    (Integer.parseInt(month.getText().toString())==3)||
                                    (Integer.parseInt(month.getText().toString())==5)||
                                    (Integer.parseInt(month.getText().toString())==7)||
                                    (Integer.parseInt(month.getText().toString())==8)||
                                    (Integer.parseInt(month.getText().toString())==10)||
                                    (Integer.parseInt(month.getText().toString())==12)){
                                if(dayV==1){
                                    dayV=31;
                                }else{
                                    dayV--;
                                }
                                judge(dayV,day);
                                //月份为30天的情况
                            }else if((Integer.parseInt(month.getText().toString())==4)||
                                    (Integer.parseInt(month.getText().toString())==6)||
                                    (Integer.parseInt(month.getText().toString())==9)||
                                    (Integer.parseInt(month.getText().toString())==11)){
                                if(dayV==1){
                                    dayV=30;
                                }else{
                                    dayV--;
                                }
                                judge(dayV,day);
                                //2月为28天
                            }else {
                                if(dayV==1){
                                    dayV=28;
                                }else{
                                    dayV--;
                                }
                                judge(dayV,day);
                            }
                        }
                        break;
                    case 3:
                        int hourV=Integer.parseInt(hour.getText().toString());
                        if(hourV==0){
                            hourV=23;
                        }else{
                            hourV--;
                        }
                        judge(hourV,hour);
                        break;
                    case 4:
                        int minuteV=Integer.parseInt(minute.getText().toString());
                        if(minuteV==0){
                            minuteV=59;
                        }else{
                            minuteV--;
                        }
                        judge(minuteV,minute);
                        break;
                }
                break;
            case R.id.saving:
                long result=timeToInt();
                Toast.makeText(TimeSetting.this,""+result,Toast.LENGTH_SHORT).show();
                break;
            case R.id.back:
                finish();
            default:
                break;
        }
    }
    //判断该年是否是闰年
    private boolean judgeRunnian(int year){
        if((year%100!=0)&&(year%4==0)){
            isRunnian=true;
            return isRunnian;
        }else if((year%100==0)&&(year%400==0)){
            isRunnian=true;
            return isRunnian;
        }else{
            return false;
        }
    }
    //将时间转化成Integer
    private long timeToInt(){
        long i=0;
        String s=year.getText().toString()+month.getText().toString()+
                day.getText().toString()+hour.getText().toString()+minute.getText().toString();
        i=Long.parseLong(s);
        return i;
    }
    //当值小于10时
    private void judge(int i,EditText editText){
        if(i>=0&&i<=9){
            editText.setText("0"+i);
        }else{
            editText.setText(""+i);
        }
    }
}
