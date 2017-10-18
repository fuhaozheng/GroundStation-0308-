package com.aee.groundstation.tool;

/**
 * Created by fuhz on 2017/3/4.
 */
public class Segments_Intersect {
    private P p1,p2,p3,p4;
    public Segments_Intersect(P p1,P p2,P p3,P p4){
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.p4=p4;
    }
    public double det(P pi,P pj){
        return pi.getX()*pj.getY()-pj.getX()*pi.getY();
    }
    public P pipj(P pi,P pj){
        P p=new P();
        p.setX(pj.getX()-pi.getX());
        p.setY(pj.getY()-pi.getY());
        return p;
    }
    public double Direction(P pi,P pj,P pk){
        return det(pipj(pk,pi),pipj(pj,pi));
    }
    public boolean On_Segment(P pi,P pj,P pk){
        double max_x=(pi.getX()-pj.getX())>0?pi.getX():pj.getX();
        double min_x=(pi.getX()-pj.getX())<0?pi.getX():pj.getX();
        double max_y=(pi.getY()-pj.getY())>0?pi.getY():pj.getY();
        double min_y=(pi.getY()-pj.getY())<0?pi.getY():pj.getY();
        if((min_x<pk.getX())&&(pk.getX()<=max_x)&&(min_y<=pk.getY())&&(pk.getY()<=max_y)){
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
