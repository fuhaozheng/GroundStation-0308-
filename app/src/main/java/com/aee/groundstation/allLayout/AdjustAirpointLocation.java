package com.aee.groundstation.allLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.aee.groundstation.GlobalValue;
import com.aee.groundstation.R;

import java.text.DecimalFormat;

/**
 * Created by fuhz on 2017/2/13.
 */
public class AdjustAirpointLocation extends RelativeLayout{
    int flag=1;
    DecimalFormat format=new DecimalFormat("0.000000");
    Button btnUnit,btnAddLati,btnSubLati,btnAddLongi,btnSubLongi;
    public AdjustAirpointLocation(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.adjust_location,this);
        btnUnit=(Button) findViewById(R.id.adjust_unit);
        btnAddLati=(Button) findViewById(R.id.latidude_add);
        btnSubLati=(Button) findViewById(R.id.latidude_subtract);
        btnAddLongi=(Button) findViewById(R.id.longitude_add);
        btnSubLongi=(Button) findViewById(R.id.longitude_subtract);
        //改变单位
        btnUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=btnUnit.getText().toString();
                if(s.equals("1")){
                    btnUnit.setText("10");
                    flag=10;
                }else if(s.equals("10")){
                    btnUnit.setText("100");
                    flag=100;
                }else if(s.equals("100")){
                    btnUnit.setText("1");
                    flag=1;
                }
            }
        });
        btnAddLati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1){
                    GlobalValue.latitudeOfAirpoint+=0.000001;
                }else if(flag==10){
                    GlobalValue.latitudeOfAirpoint+=0.00001;
                }else if(flag==100){
                    GlobalValue.latitudeOfAirpoint+=0.0001;
                }
            }
        });
        btnSubLati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1){
                    GlobalValue.latitudeOfAirpoint-=0.000001;
                }else if(flag==10){
                    GlobalValue.latitudeOfAirpoint-=0.00001;
                }else if(flag==100){
                    GlobalValue.latitudeOfAirpoint-=0.0001;
                }
            }
        });
        btnAddLongi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1){
                    GlobalValue.longitudeOfAirpoint+=0.000001;
                }else if(flag==10){
                    GlobalValue.longitudeOfAirpoint+=0.00001;
                }else if(flag==100){
                    GlobalValue.longitudeOfAirpoint+=0.0001;
                }
                //t1.setText(format.format(GlobalValue.longitudeOfAirpoint));
            }
        });
        btnSubLongi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==1){
                    GlobalValue.longitudeOfAirpoint-=0.000001;
                }else if(flag==10){
                    GlobalValue.longitudeOfAirpoint-=0.00001;
                }else if(flag==100){
                    GlobalValue.longitudeOfAirpoint-=0.0001;
                }
            }
        });
    }

}
