package com.aee.groundstation.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButton;

import com.aee.groundstation.CommonInfo;
import com.aee.groundstation.IntToByte;
import com.aee.groundstation.R;
import com.aee.groundstation.activity.setting.VisualNavigation;
import com.aee.groundstation.activity.task_load.TaskLoadSetting;
import com.aee.groundstation.communication.CommandEnum;
import com.aee.groundstation.communication.SendingSerialportMessage;


/**
 * Created by fuhz on 2016/9/20.
 */
public class MainInterface extends Activity implements View.OnClickListener{
    private ImageView setting,taskload;
    private Button unfold,trackmode,pack_up,send_check;
    private ZoomButton add,subtract;
    private SurfaceView surfaceView;
    private int width, height;
    private RelativeLayout rl,r2;
    private boolean flag=false;
    private byte[] b;//发送到串口的数据
    private TextView showMessage;//显示一些信息
    public static final int CMD_FLYINGSTATUS=1;
    private SendingSerialportMessage sendingMessage;
    //异步消息处理
    private Handler handler=new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case CMD_FLYINGSTATUS:
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
                    showMessage.setText(ss);
                    break;
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_interface);
        init();
        new Thread(new MyThread()).start();
    }
    public void init(){
        setting=(ImageView)findViewById(R.id.setting);//设置
        unfold=(Button)findViewById(R.id.unfold);//打开画中画
        add=(ZoomButton)findViewById(R.id.zoom_add);//调焦变大
        subtract=(ZoomButton)findViewById(R.id.zoom_subtract);//调焦变小
        trackmode=(Button)findViewById(R.id.trackmode);//视觉导航
        pack_up=(Button)findViewById(R.id.pack_up);//关闭画中画
        send_check=(Button)findViewById(R.id.send_check); //发送校验
        taskload=(ImageView)findViewById(R.id.task_load);//打开任务载荷
        surfaceView=(SurfaceView)findViewById(R.id.surfaceView);
        rl=(RelativeLayout)findViewById(R.id.rl);
        r2=(RelativeLayout)findViewById(R.id.relativeLayout2);
        ViewGroup.LayoutParams lp=surfaceView.getLayoutParams();
        showMessage=(TextView)findViewById(R.id.warning_message);
        setting.setOnClickListener(this);
        unfold.setOnClickListener(this);
        add.setOnClickListener(this);
        subtract.setOnClickListener(this);
        trackmode.setOnClickListener(this);
        taskload.setOnClickListener(this);
        pack_up.setOnClickListener(this);
        send_check.setOnClickListener(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.setting:
                startActivity(new Intent(MainInterface.this,FunctionSetting.class));
                break;
            case R.id.unfold:
                unfold.setVisibility(View.GONE);
                r2.setVisibility(View.VISIBLE);
                break;
            case R.id.pack_up:
                r2.setVisibility(View.GONE);
                unfold.setVisibility(View.VISIBLE);
                break;
            case R.id.zoom_add:
                 CommandEnum.command=(CommandEnum.command)|0x020000;
                 CommandEnum.command=(CommandEnum.command)&0xfeffff;
                 b= IntToByte.getByte(CommandEnum.command);
                 sendingMessage=new SendingSerialportMessage(CommandEnum.CMD_CAMERA_SET, 3,b);
                 sendingMessage.send();
                 /*
                 *对反馈回来的数据进行判断
                 */
                //showMessage("您的命令已经发送,对方没有反馈回来信息");
                if(CommonInfo.receiveByte!=null){
                    showMessage(""+CommonInfo.receiveByte[2]);
                    if(CommonInfo.cmdMap.containsKey(CommonInfo.receiveByte[2])){
                        CommonInfo.failCount=0;
                        showMessage("数据已经正确发送，返回命令正确!"+CommonInfo.failCount);
                    }else{
                        if(CommonInfo.failCount>=20){
                            showMessage("对不起，该命令已经无效");
                        }else{
                            showMessage("请重新发送");
                            CommonInfo.failCount++;
                        }
                    }
                }else{
                    showMessage("我的命令已经发送,对方没有反馈回来信息");
                }
                 break;
            case R.id.zoom_subtract:
                CommandEnum.command=(CommandEnum.command)&0xfdffff;
                CommandEnum.command=(CommandEnum.command)|0x010000;
                b= IntToByte.getByte(CommandEnum.command);
                new SendingSerialportMessage(CommandEnum.CMD_CAMERA_SET, 3,b).send();
                //对反馈回来的数据进行处理
                if(CommonInfo.receiveByte!=null){
                    showMessage(""+CommonInfo.receiveByte[2]);
                    if(CommonInfo.cmdMap.containsKey(CommonInfo.receiveByte[2])){
                        CommonInfo.failCount=0;
                        showMessage("数据已经正确发送，返回命令正确!"+CommonInfo.failCount);
                    }else{
                        if(CommonInfo.failCount>=20){
                            showMessage("对不起，该命令已经无效");
                        }else{
                            showMessage("请重新发送");
                            CommonInfo.failCount++;
                        }
                    }
                }else{
                    showMessage("我的命令已经发送,对方没有反馈回来信息");
                }
                break;
            case R.id.trackmode:
                if(!flag) {
                    startActivityForResult(
                               new Intent(MainInterface.this, VisualNavigation.class), 1);
                    flag=true;
                }else{
                    Resources resources=getResources();
                    Drawable drawable=resources.getDrawable(R.drawable.icn_tracking_n);
                    trackmode.setBackground(drawable);
                    flag=false;
                }
                break;
            case R.id.task_load:
                startActivity(new Intent(MainInterface.this,TaskLoadSetting.class));
                break;
            case R.id.send_check:
                hahaha();
                /*
                int checknum=22;
                b= IntToByte.getByte(checknum);
                //发送校验命令
                new SendingSerialportMessage((byte)22, 3,b).send();
                //如果反馈正确，字体变色
                if(CommonInfo.receiveByte!=null){
                    if(CommonInfo.receiveByte[2]==22){
                        send_check.setTextColor(Color.RED);
                    }
                }else{
                    showMessage("我的命令已经发送,对方没有反馈回来信息");
                }
                */
                break;
            default:
                break;

        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    Resources resources=getResources();
                    Drawable drawable=resources.getDrawable(R.drawable.icn_tracking_on);
                    trackmode.setBackground(drawable);
                }
                break;
            default:
        }
    }
    private void showMessage(String s){
        Toast.makeText(MainInterface.this,s,Toast.LENGTH_SHORT).show();
    }
    //定义一个线程，不停地接收反馈回来的指令
    class MyThread implements Runnable{
        public void run(){
            while (true){
                try{
                    Thread.sleep(100);
                    Message message=new Message();
                    message.what=CMD_FLYINGSTATUS;
                    handler.sendMessage(message);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private void hahaha(){
        byte[] b=new byte[15];
        byte[] messageOfLatitude=CommonInfo.messageProcess(-22.655036);
        byte[] messageOfLongitude=CommonInfo.messageProcess(113.925548);
        b[0]=CommonInfo.intToByte(1);
        b[1]=messageOfLongitude[0];
        b[2]=messageOfLongitude[1];
        b[3]=messageOfLongitude[2];
        b[4]=messageOfLongitude[3];
        b[5]=messageOfLatitude[0];
        b[6]=messageOfLatitude[1];
        b[7]=messageOfLatitude[2];
        b[8]=messageOfLatitude[3];
        b[9]=CommonInfo.intToByte(1);
        //高度不大于256m，一个字节就可以表示，b[10]为高度的高位字节，为0
        b[10]=CommonInfo.intToByte(0);
        //先假设一个速度，为10
        b[11]=CommonInfo.intToByte(10);
        //速度不大于256，一个字节就可以表示，b[12]为速度的高位字节，为0
        b[12]=CommonInfo.intToByte(0);
        //假设一个停留时间，暂时以秒为单位，暂定为100秒
        b[13]=CommonInfo.intToByte(100);
        //时间不大于256，一个字节就可以表示，b[14]为高度的高位字节，为0
        b[14]=CommonInfo.intToByte(0);
        new SendingSerialportMessage(CommandEnum.CMD_FLIGHT_VEC_COORD,15,b).send();
    }
}
