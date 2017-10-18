package com.aee.groundstation.operation;

/**
 * Created by fuhz on 2017/3/4.
 */
/***************/
//该类用于生成扫描区域，以及执行扫面区域的一系列动作

import com.aee.groundstation.tool.Judging;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/************/
public class ScanTaskOperation {
    //给定一个点，生成围绕其的四个坐标点
    public List<LatLng> generateLatLngList(LatLng latLng){
        List<LatLng> list=new ArrayList<>();
        double latitude=latLng.latitude;//获取给定坐标点的纬度
        double longitude=latLng.longitude;//获取给定坐标点的经度
        LatLng latLng1=new LatLng(latitude+0.01,longitude-0.01);
        LatLng latLng2=new LatLng(latitude+0.01,longitude+0.01);
        LatLng latLng3=new LatLng(latitude-0.01,longitude+0.01);
        LatLng latLng4=new LatLng(latitude-0.01,longitude-0.01);
        list.add(latLng1);
        list.add(latLng2);
        list.add(latLng3);
        list.add(latLng4);
        return list;
    }
    //初始生成4个标准的marker
    public List<Marker> generateMarker(LatLng latLng,BaiduMap map,BitmapDescriptor bdA){
        List<LatLng> list=generateLatLngList(latLng);
        List<Marker> listOfMarker=new ArrayList<>();
        for(LatLng latLng1:list){
            OverlayOptions ooA = new MarkerOptions().position(latLng1).icon(bdA)
                    .zIndex(5).draggable(true);
            Marker mMarkerA = (Marker) (map.addOverlay(ooA));
            listOfMarker.add(mMarkerA);
        }
        return listOfMarker;
    }
    //生成对应的中间marker
    public List<Marker> generatePreMarker(BaiduMap map,List<Marker> list,BitmapDescriptor bdA){
        List<Marker> listOfMarker=new ArrayList<>();
        LatLng latLng1,latLng2;
        for(int i=0;i<list.size();i++){
            latLng1=list.get(i).getPosition();
            if(i==list.size()-1){
                latLng2=list.get(0).getPosition();
            }else{
                latLng2=list.get(i+1).getPosition();
            }
            double latitude=(latLng1.latitude+latLng2.latitude)/2;
            double longitude=(latLng1.longitude+latLng2.longitude)/2;
            LatLng latLng=new LatLng(latitude,longitude);
            OverlayOptions ooA = new MarkerOptions().position(latLng).icon(bdA)
                    .zIndex(5).draggable(true);
            Marker mMarkerA = (Marker) (map.addOverlay(ooA));
            listOfMarker.add(mMarkerA);
        }
        return listOfMarker;
    }
    //根据Marker获取经纬度列表
    public List<LatLng> generateLatLngList(List<Marker> list){
        List<LatLng> list1=new ArrayList<>();
        for(Marker marker:list){
            list1.add(marker.getPosition());
        }
        return list1;
    }
    // 添加多边形(需要经纬度列表，百度地图实例作为参数)
    public Overlay generateScanZone(Overlay overlay,List<Marker> listForMarker, BaiduMap baiduMap){
        List<LatLng> list=generateLatLngList(listForMarker);
        if(overlay!=null){
            overlay.remove();
        }
        OverlayOptions ooPolygon = new PolygonOptions().points(list)
                .stroke(new Stroke(5, 0xAA00FF00)).fillColor(0x00000000);
        return baiduMap.addOverlay(ooPolygon);
    }
    //拖拽Marker,第一个参数为正式的Marker，第二个参数为存储正式Marker的列表，第三个参数为与之对应的准Marker的列表
    //第四个参数为被拖拽的marker结束后对应的经纬度
    public void dragMarker(Marker marker,List<Marker> listForMarker,
                           List<Marker> listForPreMarker,LatLng latLng){
        int position=listForMarker.indexOf(marker);//获取该marker的索引
        marker.setPosition(latLng);//更新该marker的位置
        listForMarker.set(position,marker);
        if(position==0){
            Marker marker1=listForMarker.get(1);
            Marker marker2=listForMarker.get(listForMarker.size()-1);

            Marker m1=listForPreMarker.get(0);
            double latitude1=(marker1.getPosition().latitude+marker.getPosition().latitude)/2;
            double longitude1=(marker1.getPosition().longitude+marker.getPosition().longitude)/2;
            LatLng latLng1=new LatLng(latitude1,longitude1);
            m1.setPosition(latLng1);
            listForPreMarker.set(0,m1);

            int size=listForPreMarker.size()-1;
            Marker m2=listForPreMarker.get(size);
            double latitude2=(marker2.getPosition().latitude+marker.getPosition().latitude)/2;
            double longitude2=(marker2.getPosition().longitude+marker.getPosition().longitude)/2;
            LatLng latLng2=new LatLng(latitude2,longitude2);
            m2.setPosition(latLng2);
            listForPreMarker.set(size,m2);
        }else if(position==listForMarker.size()-1){
            Marker marker1=listForMarker.get(0);
            Marker marker2=listForMarker.get(position-1);

            Marker m1=listForPreMarker.get(position);
            double latitude1=(marker1.getPosition().latitude+marker.getPosition().latitude)/2;
            double longitude1=(marker1.getPosition().longitude+marker.getPosition().longitude)/2;
            LatLng latLng1=new LatLng(latitude1,longitude1);
            m1.setPosition(latLng1);
            listForPreMarker.set(position,m1);

            int size=position-1;
            Marker m2=listForPreMarker.get(size);
            double latitude2=(marker2.getPosition().latitude+marker.getPosition().latitude)/2;
            double longitude2=(marker2.getPosition().longitude+marker.getPosition().longitude)/2;
            LatLng latLng2=new LatLng(latitude2,longitude2);
            m2.setPosition(latLng2);
            listForPreMarker.set(position-1,m2);
        }else {
            Marker marker1=listForMarker.get(position+1);
            Marker marker2=listForMarker.get(position-1);

            Marker m1=listForPreMarker.get(position);
            double latitude1=(marker1.getPosition().latitude+marker.getPosition().latitude)/2;
            double longitude1=(marker1.getPosition().longitude+marker.getPosition().longitude)/2;
            LatLng latLng1=new LatLng(latitude1,longitude1);
            m1.setPosition(latLng1);
            listForPreMarker.set(position,m1);

            int size=position-1;
            Marker m2=listForPreMarker.get(size);
            double latitude2=(marker2.getPosition().latitude+marker.getPosition().latitude)/2;
            double longitude2=(marker2.getPosition().longitude+marker.getPosition().longitude)/2;
            LatLng latLng2=new LatLng(latitude2,longitude2);
            m2.setPosition(latLng2);
            listForPreMarker.set(position-1,m2);
        }
    }
    //判断是否交叉，如果交叉，返回true，否则返回false
    public boolean isIntersect(List<Marker> list){
        boolean isIntersect=false;
        List<Boolean> listB=new ArrayList<>();
        List<LatLng> list1=new ArrayList<>();
        for(Marker marker:list){list1.add(marker.getPosition());}
        int size=list1.size()-1;
        for(int i=0;i<size;i++){
             if(i==size-1){
                listB.add(new Judging(list1.get(i),list1.get(i+1),list1.get(0),list1.get(1)).Segment_Intersect());
            }else if(i==size-2){
                listB.add(new Judging(list1.get(i),list1.get(i+1),list1.get(i+2),list1.get(0)).Segment_Intersect());
            }else{
                listB.add(new Judging(list1.get(i),list1.get(i+1),list1.get(i+2),list1.get(i+3)).Segment_Intersect());
            }
        }
        for(boolean b:listB){
            if(b){
                isIntersect=true;
            }
        }
        return isIntersect;
    }
    //拖拽准Marker的过程,返回一个Marker链表
    public List<Marker> dragPreMarkerProcess(Marker marker,List<Marker> listForMarker,
                              List<Marker> listForPreMarker,LatLng latLng){
        int index=listForPreMarker.indexOf(marker);
        marker.setPosition(latLng);
        List<Marker> list1=new ArrayList<>();
        for(Marker marker1:listForMarker){list1.add(marker1);}
        list1.add(index+1,marker);
        return list1;
    }
    public void dragPreMarkerResult(List<Marker> list,List<Marker> listForMarker, List<Marker> listForPreMarker,
                                    BaiduMap map,BitmapDescriptor bde,BitmapDescriptor bdf){
        //将拖拽准marker生成的list里面的经纬度信息进行保存，简化清空listForMarker的步骤
        List<LatLng> list1=new ArrayList<LatLng>();
        for(Marker marker1:list){
            list1.add(marker1.getPosition());
        }
        //清空mapOfMarker.get(s)/listForMarker
        for(Marker marker1:listForMarker){
            marker1.remove();
        }
        listForMarker.clear();
        //将list1复制给mapOfMarker.get(s)
        for(LatLng latLng:list1){
            OverlayOptions ooA = new MarkerOptions().position(latLng).icon(bde)
                    .zIndex(9).draggable(true);
            Marker mMarkerA = (Marker) (map.addOverlay(ooA));
            listForMarker.add(mMarkerA);
        }
        //清除备用Marker
        for(Marker marker1:listForPreMarker){
            marker1.remove();
        }
        listForPreMarker.clear();
        //重新生成准Marker，并保存
        for(Marker marker1:generatePreMarker(map, listForMarker, bdf)){
            listForPreMarker.add(marker1);
        }
    }
}
