package com.aee.groundstation.communication;

/**
 * Created by dengheng on 2016/9/19.
 */
public class ComPortDataEntity extends Thread{
    private byte dataHeader1;
    private byte dataHeader2;
    private byte dataCmd;
    private byte dataLength;
    private byte[] dataMain;
    private byte datasum1;
    private byte datasum2;

    public byte getDataHeader1() {
        return dataHeader1;
    }

    public void setDataHeader1(byte dataHeader1) {
        this.dataHeader1 = dataHeader1;
    }

    public byte getDataCmd() {
        return dataCmd;
    }

    public void setDataCmd(byte dataCmd) {
        this.dataCmd = dataCmd;
    }

    public byte getDatasum2() {
        return datasum2;
    }

    public void setDatasum2(byte datasum2) {
        this.datasum2 = datasum2;
    }

    public byte getDatasum1() {
        return datasum1;
    }

    public void setDatasum1(byte datasum1) {
        this.datasum1 = datasum1;
    }

    public byte[] getDataMain() {
        return dataMain;
    }

    public void setDataMain(byte[] dataMain) {
        this.dataMain = dataMain;
    }

    public byte getDataLength() {
        return dataLength;
    }

    public void setDataLength(byte dataLength) {
        this.dataLength = dataLength;
    }

    public byte getDataHeader2() {
        return dataHeader2;
    }

    public void setDataHeader2(byte dataHeader2) {
        this.dataHeader2 = dataHeader2;
    }

    public ComPortDataEntity(){
        this.dataHeader1 = 0x55;
        this.dataHeader2 = (byte)0xaa;
    }

    public  byte[] toBytes(){
        byte[] dst = new byte[6+this.dataLength];
        int iDx = 0;
        dst[iDx++] = this.dataHeader1;
        dst[iDx++] = this.dataHeader2;
        dst[iDx++] = this.dataCmd;
        dst[iDx++] = this.dataLength;

        int i = 0;
        for (; iDx < this.dataLength + 4; )
        {
            dst[iDx++] = this.dataMain[i++];
        }

        dst[iDx] = 0;
        dst[iDx + 1] = 0;
        dst[iDx] += this.dataCmd;//dst[42]=cmd  195
        dst[iDx + 1] += this.dataCmd;//dst[43]=cmd  195
        dst[iDx] += this.dataLength;//dst[42]=cmd+size 233=195+38
        dst[iDx + 1] += dst[iDx];//dst[43]= 172=195+233

        for (i = 0; i < this.dataLength; i++)
        {
            dst[iDx] += this.dataMain[i];//dst[42]=216
            dst[iDx + 1] += dst[iDx];//dst[43]=226
        }
        return  dst;
    }
}
