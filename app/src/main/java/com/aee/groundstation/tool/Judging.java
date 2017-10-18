package com.aee.groundstation.tool;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by fuhz on 2017/3/6.
 */
public class Judging {
    private LatLng p1,p2,p3,p4;
    public Judging(LatLng p1,LatLng p2,LatLng p3,LatLng p4){
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.p4=p4;
    }
    public double det(LatLng pi,LatLng pj){
        return pi.latitude*pj.longitude-pj.latitude*pi.longitude;
    }
    public LatLng pipj(LatLng pi,LatLng pj){
        double latitude=pj.latitude-pi.latitude;
        double longitude=pj.longitude-pi.longitude;
        LatLng p=new LatLng(latitude,longitude);
        return p;
    }
    public double Direction(LatLng pi,LatLng pj,LatLng pk){
        return det(pipj(pk,pi),pipj(pj,pi));
    }
    public boolean On_Segment(LatLng pi,LatLng pj,LatLng pk){
        double max_x=(pi.latitude-pj.latitude)>0?pi.latitude:pj.latitude;
        double min_x=(pi.latitude-pj.latitude)<0?pi.latitude:pj.latitude;
        double max_y=(pi.longitude-pj.longitude)>0?pi.longitude:pj.longitude;
        double min_y=(pi.longitude-pj.longitude)<0?pi.longitude:pj.longitude;
        if((min_x<pk.latitude)&&(pk.latitude<=max_x)&&(min_y<=pk.longitude)&&(pk.longitude<=max_y)){
            return true;
        }else {
            return false;
        }
    }
    public boolean Segment_Intersect(){
        double d1=Direction(this.p3,this.p4,this.p1);
        double d2=Direction(this.p3,this.p4,this.p2);
        double d3=Direction(this.p1,this.p2,this.p3);
        double d4=Direction(this.p1,this.p2,this.p4);
        if(((d1>0&&d2<0)||(d1<0&&d2>0))&&((d3>0&&d4<0)||(d3<0&&4>0))){
            return true;
        }else if(d1==0&&On_Segment(p3,p4,p1)){
            return true;
        }else if(d2==0&&On_Segment(p3,p4,p2)){
            return true;
        }else if(d3==0&&On_Segment(p1,p2,p3)){
            return true;
        }else if(d4==0&&On_Segment(p1,p2,p4)){
            return true;
        }else {
            return false;
        }
    }
}
