package com.aee.groundstation.activity.messageOfAirlineandAirpoint;

import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;

/**
 * Created by fuhz on 2017/2/10.
 */
public class Airpoint {
    public int id;//特征码
    public int heightOfFlying;//飞行高度
    public String rotationDirection=" ";//旋转方向
    public int heightOfAltitudeFly;//飞行海拔高度
    public int heightOfGroundFly;//飞行地面高度
    public int speedOfUp;//上升速度
    public int speedOfDown;//下降速度
    public int speedOfFlying;//飞行速度
    public Marker marker;//Mark
    public LatLng latLng;//经纬度
    public String modeOfYaw=" ";//偏航角模式
    public String typeOfCamera=" ";//相机类型
    public String numberOfCamera=" ";//相机编号
    public String modeOfCameraYaw=" ";//相机偏航角模式
    public int pitchOfCamera;//相机俯仰角度
    public int rollOfCamera;//相机横滚角度
    public ArrayList<String> listOfairpointAction=new ArrayList<>();//航点对应的动作列表
    public ArrayList<TaskofAirpoint> listOfairpointAction1=new ArrayList<>();//航点对应的动作列表
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getHeightOfFlying(){return heightOfFlying;}
    public void setHeightOfFlying(int heightOfFlying){this.heightOfFlying=heightOfFlying;}

    public String getRotationDirection(){return rotationDirection;}
    public void setRotationDirection(String rotationDirection){this.rotationDirection=rotationDirection;}

    public int getHeightOfAltitudeFly() {return heightOfAltitudeFly;}
    public void setHeightOfAltitudeFly(int heightOfAltitudeFly){this.heightOfAltitudeFly=heightOfAltitudeFly;}

    public int getHeightOfGroundFly() {return heightOfGroundFly;}
    public void setHeightOfGroundFly(int heightOfGroundFly){this.heightOfGroundFly=heightOfGroundFly;}

    public int getSpeedOfUp() {return speedOfUp;}
    public void setSpeedOfUp(int speedOfUp){this.speedOfUp=speedOfUp;}

    public int getSpeedOfDown() {return speedOfDown;}
    public void setSpeedOfDown(int speedOfDown){this.speedOfDown=speedOfDown;}

    public int getSpeedOfFlying() {return speedOfFlying;}
    public void setSpeedOfFlying(int speedOfFlying){this.speedOfFlying=speedOfFlying;}

    public Marker getMarker() {return marker;}
    public void setMarker(Marker marker){this.marker=marker;}

    public LatLng getLatLng() {return latLng;}
    public void setLatLng(LatLng latLng){this.latLng=latLng;}

    public String getModeOfYaw() {return modeOfYaw;}
    public void setModeOfYaw(String modeOfYaw){this.modeOfYaw=modeOfYaw;}

    public String getTypeOfCamera() {return typeOfCamera;}
    public void setTypeOfCamera(String typeOfCamera){this.typeOfCamera=typeOfCamera;}

    public String getNumberOfCamera() {return numberOfCamera;}
    public void setNumberOfCamera(String numberOfCamera){this.numberOfCamera=numberOfCamera;}

    public String getModeOfCameraYaw() {return modeOfCameraYaw;}
    public void setModeOfCameraYaw(String modeOfCameraYaw){this.modeOfCameraYaw=modeOfCameraYaw;}

    public int getPitchOfCamera() {return pitchOfCamera;}
    public void setPitchOfCamera(int pitchOfCamera){this.pitchOfCamera=pitchOfCamera;}

    public int getRollOfCamera() {return rollOfCamera;}
    public void setRollOfCamera(int rollOfCamera){this.rollOfCamera=rollOfCamera;}

    public ArrayList<String> getListOfairpointAction() {return listOfairpointAction;}
    public void setListOfairpointAction(ArrayList<String> listOfairpointAction){this.listOfairpointAction=listOfairpointAction;}

    public ArrayList<TaskofAirpoint> getListOfairpointAction1() {return listOfairpointAction1;}
    public void setListOfairpointAction1(ArrayList<TaskofAirpoint> listOfairpointAction1){this.listOfairpointAction1=listOfairpointAction1;}


}
