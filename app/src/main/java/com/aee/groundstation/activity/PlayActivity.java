package com.aee.groundstation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


import com.aee.groundstation.R;

/**
 * Created by fuhz on 2016/11/22.
 */
public class PlayActivity  extends Activity {
    private VideoView mVideoView;
    private MediaController mMediaController;
    String path1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_play);
        Intent intent=getIntent();
        String path=intent.getStringExtra("path");
        path1=path;
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        //设置视频的地址
        mVideoView.setVideoPath(path1);
//        //可以设置本地地址，也可以设置网络地址
//        mVideoView.setVideoURI(Uri.parse(path1));
        mMediaController = new MediaController(this);
        mMediaController.show(5000);
        //设置媒体控制器
        mVideoView.setMediaController(mMediaController);
        //设置视频的播放参数，这里设置为拉伸
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH,0);
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);//高画质
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            public void onPrepared(MediaPlayer mediaPlayer){
                //播放速度为正常速度1
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        mVideoView.requestFocus();
        mMediaController.show(5000);
    }
    public void onBackPressed(){
        Intent intent=new Intent();
        intent.putExtra("back","value");
        setResult(RESULT_OK,intent);
        finish();
    }

}

