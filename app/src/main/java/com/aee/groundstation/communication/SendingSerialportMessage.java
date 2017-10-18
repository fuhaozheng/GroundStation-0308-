package com.aee.groundstation.communication;


import org.winplus.serial.utils.SerialPort;
import java.io.File;
import java.io.IOException;

import io.vov.vitamio.utils.Log;

/**
 * Created by fuhz on 2016/10/17.
 * 旧的File是/dev/ttyS0，新的File是/dev/ttyS1
 */
public class SendingSerialportMessage {
    private SerialPort serialPort;
    ComPortDataEntity cpd;
    private int baudrate=57600;
    private byte data;
    private int length;
    private byte[] dataContent;
    public SendingSerialportMessage(byte data,int length,byte[] dataContent){
        this.data=data;
        this.length=length;
        this.dataContent=dataContent;
        File dev = new File("/dev");
        try {
            serialPort = new SerialPort(new File("/dev/ttyS0"),baudrate,0);
            cpd = new ComPortDataEntity();
            cpd.setDataCmd(data);
            cpd.setDataLength((byte)length);
            cpd.setDataMain(dataContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void send(){
            serialPort.write(cpd);
    }
}
