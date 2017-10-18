package com.aee.groundstation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aee.groundstation.R;

import java.util.List;

/**
 * Created by fuhz on 2017/2/17.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> implements View.OnClickListener{
    private List<String> datas;
    public RecycleAdapter(List<String> datas) {
        this.datas = datas;
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int position);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_click, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder,  int position) {
        viewHolder.mTextView.setText(datas.get(position));
        //将数据保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(datas.get(position));
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,datas.indexOf((String)v.getTag()));
        }
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.t2);
        }
    }
}

