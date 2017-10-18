package com.aee.groundstation.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.aee.groundstation.GridViewAdapter;
import com.aee.groundstation.R;
import com.aee.groundstation.VideoInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuhz on 2016/11/22.
 */
public class Playback extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private RelativeLayout relativeLayout;
    private GridView gridView;
    private Button play,delete,back;
    private List<VideoInfo> allVideoList=new ArrayList<VideoInfo>();//存放所有的对象
    private Bitmap bitmap;
    private String playPath;//获取视频的播放路径
    private List<Integer> judge=new ArrayList<Integer>();//所有点击的item
    public static  List<String> list=new ArrayList<String>();//点击item的路径信息
    private List<VideoInfo> videoList=new ArrayList<VideoInfo>();//待处理的对象信息
    private GridViewAdapter simpleAdapter;
    private Cursor cursor;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.huifang_main);
        read();
        init();
        simpleAdapter=new GridViewAdapter
                (Playback.this,R.layout.suoluetu,allVideoList);
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
        play.setOnClickListener(this);
        delete.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    //初始化各个控件
    private void init(){
        gridView=(GridView)findViewById(R.id.gm);
        play=(Button)findViewById(R.id.play);
        delete=(Button)findViewById(R.id.delete);
        back=(Button)findViewById(R.id.back);
    }

    //根据视频的路径，获取其缩略图
    private Bitmap suoluetu(String path){
        Bitmap bitmap=null;
        File file=new File(path);
        if(file.exists()){
            bitmap= ThumbnailUtils.createVideoThumbnail(file.getAbsolutePath(), MediaStore.Images.Thumbnails.MICRO_KIND);
            bitmap=ThumbnailUtils.extractThumbnail(bitmap,200,200,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                list.clear();
                break;
            case R.id.play:
                Intent intent=new Intent(Playback.this,PlayActivity.class);
                intent.putExtra("path",playPath);
                startActivityForResult(intent,2);
                break;
            case R.id.delete:
                Intent intent2=new Intent(Playback.this,DeleteVideo.class);
                startActivityForResult(intent2,1);
                break;
        }
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VideoInfo vi=allVideoList.get(position);
        playPath=vi.getPath();
        relativeLayout=(RelativeLayout) view.findViewById(R.id.suoluetu);
        if(judge.contains(position)){
            videoList.remove(vi);
            for(int i=0;i<judge.size();i++){
                if(position==judge.get(i)){
                    judge.remove(i);
                }
            }
            list.remove(vi.getPath());
            relativeLayout.setBackgroundResource(R.drawable.save_set_white);
        }else{
            videoList.add(vi);
            judge.add(position);
            list.add(vi.getPath());
            relativeLayout.setBackgroundResource(R.drawable.save_set_choose);
        }
        if(0==list.size()){
            play.setEnabled(false);
            delete.setEnabled(false);
        }else if(1==list.size()){
            play.setEnabled(true);
            delete.setEnabled(true);
        }else{
            play.setEnabled(false);
            delete.setEnabled(true);
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if(RESULT_OK==resultCode){
                    //要想更新，只能是更新保存数据的list，要不然即使怎么操作，都不会更新
                    judge.clear();
                    list.clear();
                    allVideoList.removeAll(videoList);
                    videoList.clear();
                    simpleAdapter.notifyDataSetChanged();
                    play.setEnabled(false);
                    delete.setEnabled(false);
                }
                break;
            case 2:
                if(RESULT_OK==resultCode){
                    relativeLayout.setBackgroundResource(R.drawable.save_set_white);
                    judge.clear();
                    list.clear();
                    videoList.clear();
                    play.setEnabled(false);
                    delete.setEnabled(false);
                }
                break;
            default:
        }
    }
    //使用内容提供者获取视频信息
    private void read(){
        try{
            cursor=getApplicationContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null,
                    null,null,null);
            while(cursor.moveToNext()){
                //视频名称
                String title=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));
                //视频文件的路径
                String url=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                VideoInfo vi=new VideoInfo();
                vi.setName(title);
                vi.setPath(url);
                vi.setResource(suoluetu(url));
                allVideoList.add(vi);
            }
        }catch (Exception e){

        }finally {
            if(cursor!=null){cursor.close();}
        }
    }

}

