package com.aee.groundstation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuhz on 2016/11/23.
 */
public class CommonInfo {
    public static int count=0;
    public static  int failCount=0;//接收返回指令失败的次数
    public static byte[] receiveByte;//接受到的反馈指令
    public static float roll;//横滚
    public static float pitch;//俯仰
    public static float yaw;//偏航
    public static double longitude;//当前经度
    public static double latitude;//当前纬度
    public static int height;//当前高度
    public static int speed;//当前速度
    public static int dianliang;//当前电量
    public static int countOfSateite;//当前卫星数量
    public static Map cmdMap=new HashMap();
    public static int valueOfPitch;//俯仰数据
    public static int valueOfRoll;//横滚数据
    public static int valueOfThroutle;//油门数据
    public static int valueOfYaw;//航向数据
    public static int mode1;
    public static int mode2;
    public static String s;
    public static double longitudeOfPoint;//航点经度
    public static double latitudeOfPoint;//航点纬度
    public static int heihtOfPoint;//航点高度
    public static byte[] cameraCMD;
    static{
        cmdMap.put((byte)0xDA,false);
        cmdMap.put((byte)0xBA,false);
    }
    public static int get(byte b){
        return b& 0xFF;
    }
    public static byte intToByte(int x){
        return (byte)x;
    }
    public static byte[] int2ByteArray(int a ){
        return new byte[]{
                (byte)((a>>24)&0xFF),
                (byte)((a>>16)&0xFF),
                (byte)((a>>8)&0xFF),
                (byte)(a&0xFF)
        };
    }
    //byteArray to int
    public static int byteArrayToInt(byte[] b){
        return (b[3]&0xFF) |(b[2]&0xFF)<<8 |(b[1]&0xFF)<<16 |(b[0]&0xFF)<<24;
    }
    public static void getMessage(byte[] b){
        if (b!=null) {
            //解析飞机的状态数据
            if(b.length==35&&get(b[2])==193){
                int i=byteArrayToInt(new byte[]{(0x00),(0x00),b[5],b[4]});
                int j=byteArrayToInt(new byte[]{(0x00),(0x00),b[7],b[6]});
                int k=byteArrayToInt(new byte[]{(0x00),(0x00),b[9],b[8]});
                if(i>32767){
                    roll=(i-65536)/10;
                }else{
                    roll=i/10;
                }
                if(j>32767){
                    pitch=(i-65536)/10;
                }else{
                    pitch=j/10;
                }
                if(k>32767){
                    yaw=(k-65536)/10;
                }else{
                    yaw=k/10;
                }
                longitude=(byteArrayToInt(new byte[]{b[13],b[12],b[11],b[10]}))/(Math.pow(10,7));
                latitude=byteArrayToInt(new byte[]{b[17],b[16],b[15],b[14]})/(Math.pow(10,7));
                height=byteArrayToInt(new byte[]{(0x00),(0x00),b[19],b[18]});
                speed=byteArrayToInt(new byte[]{(0x00),(0x00),b[21],b[20]})/100;
                dianliang=byteArrayToInt(new byte[]{(0x00),(0x00),(0x00),b[22]});
                countOfSateite=byteArrayToInt(new byte[]{(0x00),(0x00),(0x00),b[23]});
            }else if(b.length==12&& get(b[2])==51){
                int i=get(b[4]);
                int j=get(b[5]);
                int k=get(b[6]);
                int l=get(b[7]);
                if(i>127){
                    valueOfPitch=i-256;
                }else{
                    valueOfPitch=i;
                }
                if(j>127){
                    valueOfRoll=j-256;
                }else{
                    valueOfRoll=j;
                }
                if(k>127){
                    valueOfThroutle=k-256;
                }else{
                    valueOfThroutle=k;
                }
                if(l>127){
                    valueOfYaw=l-256;
                }else{
                    valueOfYaw=l;
                }
            }
        }
    }
    public static void process(){
        getMessage(CommonInfo.receiveByte);
    }
    //将经纬度转化为byte数组
    public static byte[] messageProcess(double message){
        int number=(int)(message*Math.pow(10,7));
        return new byte[]{
                (byte)(number&0xFF),
                (byte)((number>>8)&0xFF),
                (byte)((number>>16)&0xFF),
                (byte)((number>>24)&0xFF)
        };
    }
    //生成相机指令并发送
    public static void cameraCmd(){
        String s=GlobalValue.shm+ GlobalValue.csm+
                 GlobalValue.tof+ GlobalValue.fmm+
                 GlobalValue.fmt+ GlobalValue.rpd+
                 GlobalValue.rvs+ GlobalValue.rbr+
                 GlobalValue.phs+ GlobalValue.flm+
                 GlobalValue.rcd+ GlobalValue.tkp+
                 GlobalValue.cyc+ GlobalValue.rst+
                 GlobalValue.tmd+ GlobalValue.zom+
                 GlobalValue.reserved;
        int i=Integer.parseInt(s,2);//将二进制的指令转化为整数
        cameraCMD=int2ByteArray(i);
    }
}
