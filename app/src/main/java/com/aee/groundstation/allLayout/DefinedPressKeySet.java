package com.aee.groundstation.allLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.aee.groundstation.GlobalValue;
import com.aee.groundstation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuhz on 2017/2/9.
 */
public class DefinedPressKeySet extends LinearLayout{
    Spinner spinnerOfOneofthree,spinnerOfTwoofThree,spinnerA1,spinnerA2,spinnerA3,spinnerA4,
                                                           spinnerB1,spinnerB2,spinnerB3,spinnerB4;
    ArrayAdapter<String> adapter1,adapter2,adapter3;
    List<String> l1,l2,l3;
    public DefinedPressKeySet(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_of_setting_of_press_key,this);
        spinnerOfOneofthree=(Spinner)findViewById(R.id.spinner_to_oneOfThree);
        String[] s1=getResources().getStringArray(R.array.oneOfThree);
        l1=new ArrayList<>();
        for(String s:s1){
            l1.add(s);
        }
        adapter1=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,l1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfOneofthree.setAdapter(adapter1);
        spinnerOfOneofthree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.oneOfThree=l1.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerOfTwoofThree=(Spinner)findViewById(R.id.spinner_to_twoOfThree);
        String[] s2=getResources().getStringArray(R.array.twoOfThree);
        l2=new ArrayList<>();
        for(String s:s2){
            l2.add(s);
        }
        adapter2=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,l2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOfTwoofThree.setAdapter(adapter2);
        spinnerOfTwoofThree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.twoOfThree=l2.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerA1=(Spinner)findViewById(R.id.spinner_to_A1);
        spinnerA2=(Spinner)findViewById(R.id.spinner_to_A2);
        spinnerA3=(Spinner)findViewById(R.id.spinner_to_A3);
        spinnerA4=(Spinner)findViewById(R.id.spinner_to_A4);
        spinnerB1=(Spinner)findViewById(R.id.spinner_to_B1);
        spinnerB2=(Spinner)findViewById(R.id.spinner_to_B2);
        spinnerB3=(Spinner)findViewById(R.id.spinner_to_B3);
        spinnerB4=(Spinner)findViewById(R.id.spinner_to_B4);
        String[] s3=getResources().getStringArray(R.array.defined_presskey);
        l3=new ArrayList<>();
        for(String s:s3){
            l3.add(s);
        }
        adapter3=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,l3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerA1.setAdapter(adapter3);
        spinnerA2.setAdapter(adapter3);
        spinnerA3.setAdapter(adapter3);
        spinnerA4.setAdapter(adapter3);
        spinnerB1.setAdapter(adapter3);
        spinnerB2.setAdapter(adapter3);
        spinnerB3.setAdapter(adapter3);
        spinnerB4.setAdapter(adapter3);

        spinnerA1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.keyA1=l3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerA2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.keyA2=l3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerA3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.keyA3=l3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerA4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.keyA4=l3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerB1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.keyB1=l3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerB2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.keyB2=l3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerB3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.keyB3=l3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerB4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalValue.keyB4=l3.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
