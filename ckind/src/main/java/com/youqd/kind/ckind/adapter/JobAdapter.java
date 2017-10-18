package com.youqd.kind.ckind.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.JobBean;
import com.youqd.kind.ckind.constant.Constant;

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
            viewHolder.jobImage = (ImageView) convertView.findViewById(R.id.icon_class_job);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(datas.get(position).getName());
        viewHolder.content.setText(datas.get(position).getContent());
        if(!TextUtils.isEmpty(datas.get(position).getImage())){
            viewHolder.jobImage.setVisibility(View.VISIBLE);
        }
        ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+datas.get(position).getImage(),
                viewHolder.jobImage, KingActivity.getOptions()
        );
        if(datas.get(position).isFinish()){
            viewHolder.finish.setImageResource(R.drawable.icon_suc_finish);
            viewHolder.jobTime.setTextColor(context.getResources().getColor(R.color.main_green_bg));
            viewHolder.jobTime.setText("完成时间："+datas.get(position).getDate());
        }else{
            viewHolder.finish.setImageResource(R.drawable.icon_no_finish);
            viewHolder.jobTime.setTextColor(context.getResources().getColor(R.color.main_h_bg));
            viewHolder.jobTime.setText("发布时间："+datas.get(position).getDate());
        }

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

        public ImageView jobImage;
    }
}
