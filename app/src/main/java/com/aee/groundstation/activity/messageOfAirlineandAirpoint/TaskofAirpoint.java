package com.aee.groundstation.activity.messageOfAirlineandAirpoint;

/**
 * Created by fuhz on 2017/2/27.
 */
public class TaskofAirpoint {
    public int ID;//任务的ID
    public int Sequence;//任务固定的顺序
    public int Parameter;//附带的参数
    public String message="";//显示的信息
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getParameter() {
        return Parameter;
    }

    public void setParameter(int parameter) {
        Parameter = parameter;
    }

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int sequence) {
        Sequence = sequence;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
