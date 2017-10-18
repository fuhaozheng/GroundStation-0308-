package com.aee.groundstation;

/**
 * Created by fuhz on 2016/11/18.
 */
public class IntToByte {
    public static byte[] getByte(int i){
        byte[] b=new byte[3];
        b[0]=(byte)(i>>16);
        b[1]=(byte)(i>>8);
        b[2]=(byte)(i);
        return b;
    }
}
