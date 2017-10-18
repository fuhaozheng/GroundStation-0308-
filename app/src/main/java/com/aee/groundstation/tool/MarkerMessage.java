package com.aee.groundstation.tool;

/**
 * Created by fuhz on 2017/3/7.
 */
//Marker具有唯一性，当两个链表里面保存了同一个marker时，修改其中一个的信息，
    //那么另外一个的也会随之改变，因此，新建此类，用以保存Marker的原始信息
public class MarkerMessage {
    public double latM;//纬度信息
    public double lonM;//经度信息

    public double getLatM() {
        return latM;
    }

    public double getLonM() {
        return lonM;
    }

    public void setLatM(double latM) {
        this.latM = latM;
    }

    public void setLonM(double lonM) {
        this.lonM = lonM;
    }
}
