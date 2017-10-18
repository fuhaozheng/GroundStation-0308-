package com.aee.groundstation.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/10/18.
 */
public class CustomViewGroup extends RelativeLayout {

    private Context mContext;
    private View rootView;
    private TextView t5;
    private TextView t6;
    private RelativeLayout rl;
    public CustomViewGroup(Context context) {
        this(context, null);
    }
    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }
    private void initView() {
        rootView = View.inflate(mContext, R.layout.customviewgroup, this);
        t5 = (TextView) rootView.findViewById(R.id.t1);
        t6 = (TextView) rootView.findViewById(R.id.t2);
        rl = (RelativeLayout) rootView.findViewById(R.id.nstc_r1);
    }
    public void setT6(String s){t6.setText(s);}
    public void setT5(int color5){t5.setTextColor(color5);}
    public void setT6(int color6){t6.setTextColor(color6);}
    public void setEnable(boolean b){
        rl.setEnabled(b);
    }
    public void setBackground(int background){
        rl.setBackgroundResource(background);
    }
    public void set(String s,int color5,int color6,int background,boolean b){
        setT6(s);
        setT5(color5);
        setT6(color6);
        setBackground(background);
        setEnable(b);
    }
}
