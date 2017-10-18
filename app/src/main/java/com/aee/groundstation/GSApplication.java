package com.aee.groundstation;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

import org.winplus.serial.utils.SerialPort;
import java.io.File;

/**
 * Created by fuhz on 2016/12/2.
 */
public class GSApplication extends Application{
    SerialPort serialPort=null;
    private static Context sContext;
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        //创建全局的串口
        try{
            serialPort=new SerialPort(new File("/dev/ttyS0"),57600,0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Context getContext(){
        return sContext;
    }
}
