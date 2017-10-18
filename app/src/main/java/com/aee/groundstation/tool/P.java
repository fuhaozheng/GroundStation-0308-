package com.aee.groundstation.tool;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by fuhz on 2017/3/4.
 */
public class P {
    private double x;
    private double y;
    public P(){}
    public P(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
