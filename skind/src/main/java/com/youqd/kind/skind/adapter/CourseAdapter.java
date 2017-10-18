package com.youqd.kind.skind.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.bean.CourseBean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class CourseAdapter extends BaseAdapter {

    private List<CourseBean> datas;
    private LayoutInflater li = null;
    private Context context;

    public CourseAdapter(Context context, List<CourseBean> data) {
        this.context = context;
        this.datas = data;
        li = LayoutInflater.from(context);
    }

    public void setData(List<CourseBean> datas) {
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
            convertView = li.inflate(R.layout.fragment_course_item, null);
            viewHolder = new ViewHolder();
            viewHolder.courseIndex = (TextView) convertView.findViewById(R.id.courseIndex);
            viewHolder.courseName = (TextView) convertView.findViewById(R.id.courseName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.courseIndex.setText(datas.get(position).getCourseIndexName());
        if(datas.get(position).getCourseName().equals("无")){
            viewHolder.courseName.setTextColor(Color.rgb(212,212,212));
        }else {
            viewHolder.courseName.setTextColor(Color.rgb(252, 204, 155));
        }
        viewHolder.courseName.setText(datas.get(position).getCourseName());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView courseIndex;
        public TextView courseName;
    }
}
