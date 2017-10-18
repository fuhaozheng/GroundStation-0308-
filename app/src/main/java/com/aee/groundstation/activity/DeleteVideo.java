package com.aee.groundstation.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import com.aee.groundstation.R;
import java.io.File;

/**
 * Created by fuhz on 2016/11/22.
 */
public class DeleteVideo extends Activity implements View.OnClickListener{
    private Button cancel,delete;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        cancel=(Button) findViewById(R.id.cancel);
        delete=(Button) findViewById(R.id.delete);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.delete:
                for(String path:Playback.list){
//                    File file=new File(path);
//                    file.delete();
                    getContentResolver().delete(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            MediaStore.Video.Media.DATA+"=?",new String[]{path});
                }
                Intent intent =new Intent();
                intent.putExtra("data_return","delete the selected files");
                setResult(RESULT_OK,intent);
                finish();
                break;

        }
    }
}

