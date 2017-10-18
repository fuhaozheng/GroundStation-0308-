package com.aee.groundstation.activity.messageOfAirlineandAirpoint;

import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by fuhz on 2017/2/28.
 */
public class InterestPoint {
    public int id;
    public Marker mMarker;
    public LatLng mLatLng;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setMarker(Marker marker) {
        mMarker = marker;
    }

    public Marker getMarker() {
        return mMarker;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }
}
