package com.aee.groundstation;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aee.groundstation.CommonInfo;
import com.aee.groundstation.R;
import com.aee.groundstation.communication.CommandEnum;
import com.aee.groundstation.communication.SendingSerialportMessage;

import org.winplus.serial.utils.SerialPort;

import java.io.File;

/**
 * Created by fuhz on 2016/11/28.
 */
public class AcceptMessageActivity extends Activity{
    private EditText tv;
    private Button button;
    public static final int UPDATE_TEXT=2;
    private static int count=0;
    private Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATE_TEXT:
                    CommonInfo.process();
                    String ss="卫星数量为："+CommonInfo.countOfSateite+","+
                            "经度为："+CommonInfo.longitude+","+
                            "纬度为："+CommonInfo.latitude+","+
                            "速度为："+CommonInfo.speed+","+
                            "高度为："+CommonInfo.height+","+
                            "电量为："+CommonInfo.dianliang+","+
                            "横滚为："+CommonInfo.roll+","+
                            "俯仰为："+CommonInfo.pitch+","+
                            "偏航为："+CommonInfo.yaw+","+
                            "俯仰数据为："+CommonInfo.valueOfPitch+","+
                            "横滚数据为："+CommonInfo.valueOfRoll+","+
                            "油门数据位："+CommonInfo.valueOfThroutle+","+
                            "航向数据位："+CommonInfo.valueOfYaw;
                    tv.setText(ss);

            }
        }
    };
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acceptmessage);
        tv=(EditText) findViewById(R.id.acceptmessage);
        button=(Button)findViewById(R.id.receivemessage);
        new Thread(new MyThread()).start();
    }
    class MyThread implements Runnable{
        public void run(){
            while(true){
                try{
                    Thread.sleep(100);
                    Message message=new Message();
                    message.what=UPDATE_TEXT;
                    handler.sendMessage(message);
                }catch (InterruptedException e){
                   e.printStackTrace();
                }
            }
        }
    }
}
