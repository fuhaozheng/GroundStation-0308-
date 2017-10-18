package com.aee.groundstation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by fuhz on 2016/11/8.
 */
public class GridViewAdapter extends ArrayAdapter<VideoInfo> {
    private int resourceId;
    public GridViewAdapter(Context context, int resourceId, List<VideoInfo> objects){
        super(context,resourceId,objects);
        this.resourceId=resourceId;

    }
    public View getView(int position,View convertView,ViewGroup parent){
        VideoInfo vi= getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView=(ImageView)view.findViewById(R.id.image);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageBitmap(vi.getResource());
        return view;
    }
    class ViewHolder{
        ImageView imageView;
    }

}

