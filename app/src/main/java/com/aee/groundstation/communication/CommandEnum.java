package com.aee.groundstation.communication;

/**
 * Created by dengheng on 2016/9/19.
 */
public class CommandEnum {
    /**
     * @摇杆数据命令
     */
    public static final byte CMD_FLIGHT_JS_CTRL = (byte)0xC2;
    /**
     * @俯仰反向命令
     */
    public static final byte CMD_FLIGHT_PITCH_REV = (byte)0xC3;
    /**
     * @横滚反向命令
     */
    public static final byte CMD_FLIGHT_ROW_REV = (byte)0xC4;
    /**
     * @油门反向命令
     */
    public  static final byte CMD_FLIGHT_GAS_REV = (byte)0xC5;
    /**
     * @航向反向命令
     */
    public static final byte CMD_FLIGHT_YAW_REV = (byte)0xC6;
    /**
     * @ 校准命令
     */
    public static final byte CMD_FLIGHT_CALIBRATE = (byte)0xC7;
    /**
     * @电压命令
     */
    public static final byte CMD_FLIGHT_VOLTAGE = (byte)0xC0;
    /**
     * @视频频点1
     */
    public static final byte FrequencyOne = 0x05;
    /**
     * @视频频点2
     */
    public static final byte FrequencyTwo = 0x06;
    /**
     * @视频频点3
     */
    public static final byte FrequencyThree = 0x07;
    /**
     * @视频频点4
     */
    public static final byte FrequencyFour = 0x08;
    /**
     * @if size == 0 then request send  orientation data
     * @if size == 1 then request pause send orientation data
     * @response orientation info for meter方位
     */
    public static final byte CMD_FLIGHT_ORIENTATION = (byte)0xC0;
    /**
     * @if size == 0 then request send RT watch data
     * @if size == 1 then request pause send RT watch data
     */
    public static final byte CMD_FLIGHT_PARAMS = (byte)0xC1 ;
    /**
     * @if size != 0 then send track coord (size == 0 reserved)
     * @vector coordinate params for flight航点坐标
     */
    public static final byte CMD_FLIGHT_VEC_COORD = (byte)0xC3;
    /**
     * @if size != 0 then send track coord (size == 0 reserved)
     * @vector coordinate params for photo定点拍照
     */
    public static final byte CMD_FLIGHT_PA_COORD = (byte)0xA6;
    /**
     * @定时拍照
     * @vector coordinate params for photo定时拍照
     */
    public static final byte CMD_FLIGHT_TIME_PHOTO = (byte)0xA7;
    /**
     * @if size == 0 then request driver
     * @response result for coord edit自动飞行命令
     */
    public static final byte CMD_FLIGHT_AUTO_FLY = (byte)0xC4;
    /**
     * @轨迹验证
     * 长度为0，响应
     * 长度为1，验证导航点，接收
     */
    public static final byte CMD_FLIGHT_TRACK_CHECK = (byte)0xC5;
    /**
     * @返回命令
     */
    public static final byte CMD_FLIGHT_RETURN = (byte)0xC6;
    /**
     * @降落命令
     */
    public static final byte CMD_FLIGHT_SITDOWN = (byte)0xC7;
    /**
     * @挂钩命令
     */
    public static final byte CMD_FLIGHT_COUPLE = (byte)0xF2;
    /**
     * @飞行器发送过来的信息
     */
    public static final byte CMD_FLIGHT_PRINTINFO = (byte)0xFD;
    /**
     * @unused预留
     */
    public static final byte CMD_FLIGHT_RESERVERD = (byte)0xFF;
    /**
     * @飞往命令
     */
    public static final byte CMD_WAYPOINT_FLY_TO = (byte)0xC8;
    /**
     * @清除所有航点
     */
    public static final byte CMD_WAYPOINT_CLEAR_ALL = (byte)0xC9;
    /**
     * @自动起飞
     */
    public static final byte CMD_FLIGHT_AUTO_TAKE_OFF = (byte)0xCa;
    /**
     * @一键飞行
     */
    public static final byte CMD_FLIGHT_ONEKEY_FLY = (byte)0xCB;
    /**
     * @自动降落
     */
    public static final byte CMD_FLIGHT_AUTO_LANDING = (byte)0xFD;
    /**
     * @摄像头设置命令
     */
    public static final byte CMD_CAMERA_SET = (byte)0xDA;
    /**
     * @载入摄像头默认设置1
     */
    public static final byte CMD_LOAD_DEFAULT = (byte)0xDB;
    /**
     * @载入摄像头默认设置2
     */
    public static final byte CMD_LOAD_DEFAULT2 = (byte)0xDC;
    /**
     * @载入TX20 GPS
     */
    public static final byte CMD_LOAD_TX20GPS = (byte)0xF9;
    /**
     * @时间设置命令
     */
    public static final byte CMD_TIME_SET = 0x34;
    /**
     * @F100默认设置1
     */
    public static final byte F100_D_DEFAULT_SAVE1 = 0x0B;
    /**
     * @F100默认设置
     */
    public static final byte F100_D_DEFAULT_SAVE2 = 0x0C;
    /**
     * @没有摄像头
     */
    public static final byte CMD_NO_CAMERA = (byte)0xD0;
    /**
     * @校准陀螺仪开始命令字
     */
    public static final byte CMD_Calibration_Gyro = 0x3F;
    /**
     * @校准指南针开始命令字
     */
    public static final byte CMD_Calibration_Compass = 0x42;
    /**
     * @校准加速度计开始命令字
     */
    public static final byte CMD_Calibration_Accelerometer = 0x3C;
    /**
     * @校准陀螺仪失败命令字
     */
    public static final byte CMD_Calibration_Gyro_Failure = 0x3D;
    /**
     * @校准陀螺仪成功命令字
     */
    public static final byte CMD_Calibration_Gyro_Success = 0x3E;
    /**
     * @校准指南针失败命令字
     */
    public static final byte CMD_Calibration_Compass_Failure = 0x43;
    /**
     * @校准指南针成功命令字
     */
    public static final byte CMD_Calibration_Compass_Success = 0x44;
    /**
     * @校准加速度计失败命令字
     */
    public static final byte CMD_Calibration_Accelerometer_Failure = 0x40;
    /**
     * @校准加速度计成功命令字
     */
    public static final byte CMD_Calibration_Accelerometer_Success = 0x41;
    /**
     * @发送测绘飞行时航线方向上曝光间距命令字
     */
    public static final byte CMD_Survey_Flight_Yaw_Exposure_Gap = (byte)0xB4;
    /**
     * @接收测绘相机参数命令字 （开关机状态、电池电量、曝光次数）
     */
    public static final byte CMD_Survey_Camera_Parameters = (byte)0xB5;
    /**
     * @记录测绘相机曝光点数据命令字 （经纬度、横滚、府仰、航向）
     */
    public static final byte CMD_Survey_Points_Record = (byte)0xB6;
    /**
     * @所有曝光点曝光完毕命令字
     */
    public static final byte CMD_Survey_All_Points_Complete = (byte)0xB7;
    /**
     * @发送保存测绘航点命令字
     */
    public static final byte CMD_Survey_Camera_Save_Points_To_Flight = (byte)0xB8;
    /**
     * @发送加载测绘航点命令字
     */
    public static final byte CMD_Survey_Camera_Reserve_Points_From_Flight = (byte)0xB9;
    /**
     * @接收测绘相机曝光命令字
     */
    public static final byte CMD_Survey_Camera_Exposure_Points_Count = (byte)0xBD;
    /**
     * @航线类型命令
     */
    public static final byte CMD_Survey_Camera_Common_Flight = (byte)0xA0;
    /**
     * @单兵终端 Data--->无人机---->地面终端系统---->单兵界面
     */
    public static final byte CMD_Single_Pawn_Receive_Data = (byte)0xBE;
    /**
     * @单兵界面 Data--->地面终端系统---->无人机---->单兵终端
     */
    public static final byte CMD_Single_Pawn_Send_Data = (byte)0xBF;
    /**
     * @PM2.5测试
     */
    public static final byte CMD_PM25 = 0x51;
    /**
     * @手机定位
     */
    public static final byte CMD_PhoneLocation = (byte)0xB6;
    /**
     * @导航灯
     */
    public static final byte CMD_NavigateLight = (byte)0xBA;
    /**
     * @主动报警
     */
    public static final byte CMD_AutoAlarm = (byte)191;
    /**
     * @航线点限制数
     */
    public static final int limitPointsCount = 100;
    /**
     * @定义一些指令集合
     * 第一位自动飞行或者手动飞行，第二位机载录像，第三位机载拍照，第四位循环覆盖，第五位表示恢复出厂，第六位时间显示，第七位放大，
     * 第八位缩小，9,10,11摄像图像分辨率；12、13摄像视角选择；14摄像比特；15、16图像尺寸；17拍照模式；
     * 18、19、20连拍模式；21电视输出格式；22、23测光模式；24格式化
     */
    public static int command=0x602100;
}
