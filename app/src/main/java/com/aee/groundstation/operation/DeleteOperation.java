package com.aee.groundstation.operation;

import android.support.v7.widget.RecyclerView;
import com.aee.groundstation.activity.messageOfAirlineandAirpoint.Airpoint;
import com.aee.groundstation.activity.messageOfAirlineandAirpoint.TaskofAirpoint;

import java.util.List;

/**
 * Created by fuhz on 2017/3/2.
 */
public class DeleteOperation {
    //删除航点拥有的任务
    //position表示所选的item的位置，list表示任务列表，adapter表示适配器，airpoint表示拥有该列表的航点
    public void deleteTaskOfAirpoint(int position, List<TaskofAirpoint> list,
                                     RecyclerView.Adapter adapter,Airpoint airpoint){
        if(position!=-1){
            //获取该position对应的任务的ID
            int id=list.get(position).getID();
            //删除列表里item的顺序
            list.remove(position);
            adapter.notifyDataSetChanged();
            //删除与之对应的航点的任务列表item的顺序
            airpoint.getListOfairpointAction1().remove(position);
            //更改任务的ID，当ID大于删除的任务的ID时，自动减1
            for(TaskofAirpoint taskofAirpoint:airpoint.getListOfairpointAction1()){
                int ID=taskofAirpoint.getID();
                if(ID>id){
                    taskofAirpoint.setID(ID-1);
                }
            }
            //删除后默认不选则任何一个item
            position=-1;
        }
    }
}
