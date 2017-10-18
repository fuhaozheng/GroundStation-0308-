package com.aee.groundstation;

import com.aee.groundstation.activity.messageOfAirlineandAirpoint.Airpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuhz on 2017/2/9.
 */
public class GlobalValue {
    //定义一个静态的航点，用于保存所有设置好的航点信息
    public static Airpoint staticAirpoint=new Airpoint();
    //全局设置时的变量
    public static int returnHeight;//返航高度
    public static int limitedHeight;//限高
    public static int maxSpeed;//最大速度
    public static int cameraPitchLimit;//相机俯仰限位
    public static String cameraStandard;//相机制式
    public static int imageSize;//图像尺寸
    public static int imageParameter;//摄像参数
    public static boolean isCycleCover;//循环覆盖
    public static int cameraWatermark=0;//摄像机水印显示
    public static String oneOfThree;//三挡1
    public static String twoOfThree;//三挡2
    public static String keyA1;//A1键
    public static String keyA2;//A2键
    public static String keyA3;//A3键
    public static String keyA4;//A4键
    public static String keyB1;//B1键
    public static String keyB2;//B2键
    public static String keyB3;//B3键
    public static String keyB4;//B4键
    public static String alertMode;//报警方式
    //航点设置过程中需要的全局 变量
    public static int heightOfAirPoint;//航点对应的设置飞行高度
    public static String rotationDirection;//航点对应的旋转方向
    public static int heightOfAltitude;//航点对应的海拔高度
    public static int heightOfGround;//航点对应的地面高度
    public static int upSpeed;//航点对应的上升速度
    public static int downSpeed;//航点对应的下降速度
    public static int flyingSpeed;//航点对应的飞行速度
    public static String yawMode;//航点对应的偏航角模式
    public static double latitudeOfAirpoint;//航点对应的纬度
    public static double longitudeOfAirpoint;//航点对应的经度
    public static String typeOfCamera;//航点对应的相机类型
    public static String numberOfCamera;//航点对应的相机编号
    public static String yawModeOfCamera;//航点对应的相机偏航模式
    public static int pitchOfCamera;//航点对应的相机俯仰角度
    public static int rollOfCamera;//航点对应的相机横滚角度
    //航点设置过程中需要的全局 变量(添加的动作)
    public static List<String> listOfAction=new ArrayList<>();
    public static int actionOfDelay;//延时
    public static int actionOfYaw;//偏航角
    public static String actionOfInterest;//兴趣点
    public static double actionOfLongitude;//经度
    public static double actionOfLatitude;//纬度
    public static int actionOfFlyingheight;//飞行高度
    public static int actionOfUpspeed;//上升速度
    public static int actionOfDownspeed;//下降速度
    public static int actionOfFlyingspeed;//飞行速度
    public static boolean actionOfTakephoto;//是否拍照
    public static boolean actionOfRecordvideo;//是否录像
    public static int actionOfDelaytakePhoto;//相机自动间隔严实拍照
    public static int actionOfCamerayaw;//相机偏航角
    public static int actionOfCamerapitch;//相机俯仰角
    public static boolean actionOfFormatcamera=false;//相机格式化
    public static String actionOfCameranumber;//相机编号
    public static String actionOfCameraInterest;//相机兴趣点
    public static double actionOfCameraInterestLongitude;//相机兴趣点经度
    public static double actionOfCameraInterestLatitude;//相机兴趣点纬度
    public static String actionOfSaving;//救生投放装置
    public static boolean actionOfHook;//挂钩
    public static boolean actionOfDiaocang;//吊舱
    public static boolean actionOfHanhuaqi;//喊话器
    public static boolean actionOfSearchlight;//探照灯
    public static boolean actionOfJiguangzhimang;//激光致盲
    public static boolean actionOfWirelessrelay;//无线中继
    /*
    1、31~24位保留，直接存为0；
    2、22~23镜头缩放；00不动作；01缩小；10放大；11保留
    3、21时间显示 0隐藏1显示
    4、20 恢复出厂 0不动作1恢复
    5、19 循环覆盖 0关闭1启用
    6、18 拍照 0无动作1拍照
    7、17 录像 0无动作1录像
    8、16 飞行模式 0手动1自动
    9、15~14 图像尺寸 00:3M 01:5M 10:8M 11：保留
    10、13 摄像比特率 0：N 1：F
    11、12~11 摄像视角选择 00；宽 01：中 10：窄 11：保留
    12、10~8 RPD 000：1920 1080I
                001：1920 1080P
                110：1280 960
                011：1280 720/50Hz
                100：1280 720/25Hz
                101：848 480/100Hz
    13、7 格式化 0：无 1：格式化
    14、6~5 测光模式 00：平均测光 01:中央测光 10：中心点测光 11保留
    15、4 电视输出模式 0PAL 1NTSC
    16、3~1 持续连拍模式 000关闭001:1 010:3 011:5 100:20
    17、0 连拍模式 0：单张 1：三张
     */
    public static String shm="0"; //连拍模式
    public static String csm="000";//持续连拍模式
    public static String tof="0";//电视输出模式
    public static String fmm="00";//测光模式
    public static String fmt="0";//格式化
    public static String rpd="000";//RPD
    public static String rvs="00";//摄像视角选择
    public static String rbr="0";//摄像比特率
    public static String phs="00";//图像尺寸
    public static String flm="0";//飞行模式
    public static String rcd="0";//录像
    public static String tkp="0";//拍照
    public static String cyc="0";//循环覆盖
    public static String rst="0";//恢复出厂
    public static String tmd="0";//显示时间
    public static String zom="00";//镜头缩放
    public static String reserved="00000000";//保留
}
