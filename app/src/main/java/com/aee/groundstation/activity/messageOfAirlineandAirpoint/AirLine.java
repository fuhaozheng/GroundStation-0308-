package com.aee.groundstation.activity.messageOfAirlineandAirpoint;

/**
 * Created by fuhz on 2017/2/10.
 */
public class AirLine {
    public int id;//航线的ID
    public int timeOfFlying;//飞行时间
    public int countOfAirpoint;//航点总数
    public int lengthOfAirLine;//航线总长

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getTimeOfFlying() {
        return timeOfFlying;
    }

    public void setTimeOfFlying(int timeOfFlying) {
        this.timeOfFlying = timeOfFlying;
    }

    public int getCountOfAirpoint() {
        return countOfAirpoint;
    }

    public void setCountOfAirpoint(int countOfAirpoint) {
        this.countOfAirpoint = countOfAirpoint;
    }

    public int getLengthOfAirLine() {
        return lengthOfAirLine;
    }

    public void setLengthOfAirLine(int lengthOfAirLine) {
        this.lengthOfAirLine = lengthOfAirLine;
    }
}
