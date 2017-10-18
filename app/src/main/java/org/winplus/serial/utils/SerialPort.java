package org.winplus.serial.utils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.util.Log;

import com.aee.groundstation.CommonInfo;
import com.aee.groundstation.communication.*;

public class SerialPort {
	private static final String TAG = "SerialPort";

	/*
	 * Do not remove or rename the field mFd: it is used by native method
	 * close();
	 */
	private FileDescriptor mFd;
	private FileInputStream mFileInputStream;
	private FileOutputStream mFileOutputStream;
	private boolean isOK = true;

	public SerialPort(File device, int baudrate, int flags)
			throws SecurityException, IOException {

		/* Check access permission */
		if (!device.canRead() || !device.canWrite()) {
			try {
				/* Missing read/write permission, trying to chmod the file */
				Process su;
				su = Runtime.getRuntime().exec("/system/bin/su");
				//su = Runtime.getRuntime().exec("su");
				String cmd = "chmod 777 " + device.getAbsolutePath() + "\n"
						+ "exit\n";
				su.getOutputStream().write(cmd.getBytes());
				if ((su.waitFor() != 0) || !device.canRead()
						|| !device.canWrite()) {
					throw new SecurityException();
				}
			} catch (Exception e) {
				e.printStackTrace();
				//throw new SecurityException();
			}
		}

		mFd = open(device.getAbsolutePath(), baudrate, flags);
		if (mFd == null) {
			Log.e(TAG, "native open returns null");
			throw new IOException();
		}
		mFileInputStream = new FileInputStream(mFd);
		mFileOutputStream = new FileOutputStream(mFd);
		Thread th = new ReadThread();
		th.start();
		isOK = true;
	}

	/**
	 * 读串口线程
	 */
	private class ReadThread extends Thread {
		@Override
		public void run() {
			super.run();
			while (!isInterrupted()) {
				if(isOK){
					if (mFileInputStream != null) {
						byte[] buffer = new byte[512];
						int size = 0;
						try {
							size = mFileInputStream.read(buffer);
						} catch (IOException e) {
							e.printStackTrace();
						}
						//对反馈回来的命令进行处理
						synchronized (SerialPort.class){
							if (size > 0) {
								CommonInfo.s="";
								CommonInfo.receiveByte=new byte[size];
								for(int i=0;i<size;i++){
									CommonInfo.s+=buffer[i]+" ";
									CommonInfo.receiveByte[i]=buffer[i];
								}
							}
						}
//						if (size > 0) {
//							CommonInfo.receiveByte=new byte[size];
//							CommonInfo.s="";
//							for(int i=0;i<size;i++){
//								CommonInfo.s+=buffer[i]+" ";
//								CommonInfo.receiveByte[i]=buffer[i];
//							}
//						}
						try {
							//延时20ms
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			return;
		}
	}

	private void startReceviedData(){
		isOK = true;
	}

	private void pauseReceviedData(){
		isOK = false;
	}

	/**
	 * @方法名称 :write
	 * @功能描述 :向端口发送数据，请在调用此方法前 先选择端口，并确定SerialPort正常打开！
	 * @返回值类型 :void
	 *  @param message
	 */
	public void write(ComPortDataEntity message) {

		try{
			mFileOutputStream.write(message.toBytes());
			mFileOutputStream.flush();
		}catch(IOException e){
			throw new RuntimeException("获取端口的OutputStream出错："+e.getMessage());
		}finally{
			try{
				//mFileOutputStream.close();
			}catch(Exception e){
			}
		}
	}
	// Getters and setters
	public InputStream getInputStream() {
		return mFileInputStream;
	}

	public OutputStream getOutputStream() {
		return mFileOutputStream;
	}

	// JNI
	private native static FileDescriptor open(String path, int baudrate, int flags);

	public native void close();

	static {
		System.loadLibrary("serial_port");
	}
}
