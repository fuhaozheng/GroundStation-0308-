package com.aee.groundstation;

import android.graphics.Bitmap;

/**
 * Created by fuhz on 2016/11/8.
 */
public class VideoInfo {
    private String name;
    private String path;
    private Bitmap resource;

    public String getName(){
        return name;
    }
    public String getPath(){
        return path;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPath(String path){
        this.path =path;
    }
    public Bitmap getResource(){return resource;}
    public void setResource(Bitmap resource){this.resource=resource;}
}
