package com.aee.groundstation.allLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.aee.groundstation.GlobalValue;
import com.aee.groundstation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuhz on 2017/2/22.
 */
public class AlertSet extends LinearLayout{
    Spinner spinnerOfAlertSet;
    List<String> list=new ArrayList<>();
    ArrayAdapter<String> adapter;
    public AlertSet(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_of_alert_set,this);
        spinnerOfAlertSet=(Spinner) findViewById(R.id.spinner_of_alert_set);
        String[] strings=getResources().getStringArray(R.array.alert_set);
        for(String string:strings){list.add(string);}
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfAlertSet.setAdapter(adapter);
        spinnerOfAlertSet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(list.get(position).equals("空")){
                    GlobalValue.alertMode="";
                }else {
                    GlobalValue.alertMode=list.get(position);
                }
                Toast.makeText(getContext(),"报警方式为 "+GlobalValue.alertMode,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
