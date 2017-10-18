package com.aee.groundstation.allLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.aee.groundstation.GlobalValue;
import com.aee.groundstation.R;

/**
 * Created by fuhz on 2017/2/9.
 */
public class TaskParameterSet extends LinearLayout{
    SeekBar seekbarOfReturnHeight,seekbarOfLimitHeight,seekbarOfMaxSpeed;
    EditText edittextOfReturnHeight,edittextOfLimitHeight,edittextOfMaxSpeed;
    public TaskParameterSet(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_of_setting_of_task_parameter,this);
        seekbarOfReturnHeight=(SeekBar) findViewById(R.id.seekbar_return_height);
        seekbarOfLimitHeight=(SeekBar) findViewById(R.id.seekbar_limit_height);
        seekbarOfMaxSpeed=(SeekBar) findViewById(R.id.seekbar_max_speed);

        edittextOfReturnHeight=(EditText)findViewById(R.id.edittext_return_height);
        edittextOfLimitHeight=(EditText) findViewById(R.id.edittext_limit_height);
        edittextOfMaxSpeed=(EditText) findViewById(R.id.edittext_max_speed);

        seekbarOfReturnHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edittextOfReturnHeight.setText(progress+"m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                edittextOfReturnHeight.setText(seekBar.getProgress()+"m/s");
                GlobalValue.returnHeight=seekBar.getProgress();
            }
        });
        seekbarOfLimitHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edittextOfLimitHeight.setText(progress+"m");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                edittextOfLimitHeight.setText(seekBar.getProgress()+"m");
                GlobalValue.limitedHeight=seekBar.getProgress();
            }
        });
        seekbarOfMaxSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edittextOfMaxSpeed.setText(progress+"m/s");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                edittextOfMaxSpeed.setText(seekBar.getProgress()+"m/s");
                GlobalValue.maxSpeed=seekBar.getProgress();
            }
        });
    }
}
