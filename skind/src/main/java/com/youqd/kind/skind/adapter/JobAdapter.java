package com.youqd.kind.skind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.bean.JobBean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class JobAdapter extends BaseAdapter {

    private List<JobBean> datas;
    private LayoutInflater li = null;
    private Context context;

    public JobAdapter(Context context, List<JobBean> data) {
        this.context = context;
        this.datas = data;
        li = LayoutInflater.from(context);
    }

    public void setData(List<JobBean> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = li.inflate(R.layout.fragment_job_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.job_name);
            viewHolder.content = (TextView) convertView.findViewById(R.id.job_content);
            viewHolder.jobTime = (TextView) convertView.findViewById(R.id.job_time);
            viewHolder.finish = (ImageView) convertView.findViewById(R.id.job_finish);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(datas.get(position).getName());
        viewHolder.content.setText(datas.get(position).getContent());

        //if(datas.get(position).isFinish()){
            viewHolder.finish.setImageResource(R.drawable.icon_suc_finish);
            viewHolder.jobTime.setTextColor(context.getResources().getColor(R.color.main_green_bg));
        //}else{
        //    viewHolder.finish.setImageResource(R.drawable.icon_no_finish);
        ///    viewHolder.jobTime.setTextColor(context.getResources().getColor(R.color.main_h_bg));
        //}

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView content;
        public ImageView finish;
        public TextView jobTime;
    }
}
