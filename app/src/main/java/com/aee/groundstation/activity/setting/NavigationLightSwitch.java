package com.aee.groundstation.activity.setting;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aee.groundstation.IntToByte;
import com.aee.groundstation.R;
import com.aee.groundstation.communication.CommandEnum;
import com.aee.groundstation.communication.SendingSerialportMessage;

/**
 * Created by fuhz on 2016/9/21.
 */
public class NavigationLightSwitch extends Activity  implements View.OnClickListener {
    private Button btn_ok, back;
    private ImageView open_light, close_light;
    private boolean isClick = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_light_control_switch);
        btn_ok = (Button) findViewById(R.id.ensure);
        btn_ok.setOnClickListener(this);
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ensure:
                new SendingSerialportMessage(CommandEnum.CMD_NavigateLight,0, IntToByte.getByte(0)).send();
                break;

        }
    }
}