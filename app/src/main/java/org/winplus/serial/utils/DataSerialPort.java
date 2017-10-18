package org.winplus.serial.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import com.aee.groundstation.communication.*;
/**
 * Created by dengheng on 2016/9/19.
 */
public class DataSerialPort implements  SerialPortEventListener{
    private CommPortIdentifier commPort;
    private SerialPort serialPort;
    private InputStream inputStream;
    private OutputStream outputStream;
    private int timeout = 2000;//open 端口时的等待时间

    public DataSerialPort (String portName){

        CommPortIdentifier cpid;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();

        while(en.hasMoreElements()){
            cpid = (CommPortIdentifier)en.nextElement();
            if(cpid.getPortType() == CommPortIdentifier.PORT_SERIAL && cpid.getName().equals(portName)){
                this.commPort = cpid;
                break;
            }
        }
        if(commPort == null){
            return;
        }
        else{
            try{
                serialPort = (SerialPort)commPort.open(portName, timeout);
            }catch(PortInUseException e){
                return;
            }
        }

        try{
            inputStream = new BufferedInputStream(serialPort.getInputStream());
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }catch(Exception e){

        }
    }
    /**
     * @方法名称 :write
     * @功能描述 :向端口发送数据，请在调用此方法前 先选择端口，并确定SerialPort正常打开！
     * @返回值类型 :void
     *  @param message
     */
    public void write(ComPortDataEntity message) {
        if(serialPort == null){
            return;
        }
        try{
            outputStream = new BufferedOutputStream(serialPort.getOutputStream());
            outputStream.write(message.toBytes());
        }catch(IOException e){
            throw new RuntimeException("获取端口的OutputStream出错："+e.getMessage());
        }finally{
            try{
                outputStream.close();
            }catch(Exception e){
            }
        }
    }

    /**
     * @方法名称 :close
     * @功能描述 :关闭 SerialPort
     * @返回值类型 :void
     */
    public void close(){
        serialPort.close();
        serialPort = null;
        commPort = null;
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        switch(spe.getEventType()){
            case SerialPortEvent.BI:/*Break interrupt,通讯中断*/
            case SerialPortEvent.OE:/*Overrun error，溢位错误*/
            case SerialPortEvent.FE:/*Framing error，传帧错误*/
            case SerialPortEvent.PE:/*Parity error，校验错误*/
            case SerialPortEvent.CD:/*Carrier detect，载波检测*/
            case SerialPortEvent.CTS:/*Clear to send，清除发送*/
            case SerialPortEvent.DSR:/*Data set ready，数据设备就绪*/
            case SerialPortEvent.RI:/*Ring indicator，响铃指示*/
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:/*Output buffer is empty，输出缓冲区清空*/
                break;
            case SerialPortEvent.DATA_AVAILABLE:/*Data available at the serial port，端口有可用数据。读到缓冲数组，输出到终端*/
                byte[] readBuffer ;
                try {
                    int sum = inputStream.available();
                    while (sum > 0) {
                        readBuffer = new byte[sum];
                        inputStream.read(readBuffer);
                    }
                } catch (IOException e) {
                    return;
                }
        }
    }
}
