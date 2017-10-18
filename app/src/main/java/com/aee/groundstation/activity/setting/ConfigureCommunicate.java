package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/9/21.
 */
public class ConfigureCommunicate extends Activity implements View.OnClickListener{
    private Button back;
    String[] serialport={"COM1","COM2","COM3"};
    String[] baudrate={"50","75","110","134","150","200","300","600","1200","1800","2400","4800","9600",
            "19200","38400","57600","115200","230400","460800","500000","576000","921600"
            ,"1000000","1152000","1500000","2000000","2500000","3000000","3500000","4000000"};
    String[] databit={"5","6","7","8"};
    String[] checkbit={"None","Even","Odd","Mark","Space"};
    String[] stopbit={"1","1.5","2"};
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.built_conmunication_configure);
        init();
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
    private void init(){
        setCursListener(R.id.serialport,serialport, 0, true);
        setCursListener(R.id.baudrate,baudrate, 0, true);
        setCursListener(R.id.databit,databit, 0, true);
        setCursListener(R.id.checkbit,checkbit, 0, true);
        setCursListener(R.id.stopbit,stopbit, 0, true);
    }
    private void setCursListener(int id,String[] curs, int select, boolean monitor){
        Spinner spinner=(Spinner) findViewById(id);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,curs);
        spinner.setAdapter(adapter);
        spinner.setSelection(select);
        if(monitor){
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    TextView tv=(TextView)view;
                    tv.setTextColor(getResources().getColor(R.color.colorAccent));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}
