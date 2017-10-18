package com.aee.groundstation.communication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.aee.groundstation.R;

import org.winplus.serial.utils.SerialPort;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {
    private TextView tv ;
    private Button bt;
    private ListView lv;

    private int baudrate = 115200;
    private SerialPort serialPort;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    ComPortDataEntity cpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        tv = (TextView) this.findViewById(R.id.textView);
        lv = (ListView) this.findViewById(R.id.listView);
        bt = (Button) this.findViewById(R.id.button);
        tv.setText("Hello");
        bt.setText("Send");
        bt.setOnClickListener(this);


        File dev = new File("/dev");
        File[] files = dev.listFiles();

        try {
            serialPort = new SerialPort(new File("/dev/ttyS0"),baudrate,0);
            cpd = new ComPortDataEntity();
            cpd.setDataCmd((byte)12);
            cpd.setDataLength((byte)1);
            cpd.setDataMain(new byte[]{(byte)24});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                serialPort.write(cpd);
                break;
            default:
                break;
        }
    }
}