package com.youqd.kind.ckind.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.bean.CourseBean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class CourseAdapter extends BaseAdapter {

    String[] colors = {
            "#FFB90F",
            "#CDAF95",
            "#B0E0E6",
            "#AB82FF",
            "#6959CD",
            "#666666"
    };

    private List<CourseBean> datas;
    private LayoutInflater li = null;

    public CourseAdapter(Context context, List<CourseBean> data) {
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
         int random=(int)(Math.random()*6);
            convertView = li.inflate(R.layout.fragment_course_item, null);
            viewHolder = new ViewHolder();
            viewHolder.courseIndex = (TextView) convertView.findViewById(R.id.courseIndex);
            viewHolder.courseName = (TextView) convertView.findViewById(R.id.courseName);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.userName);
        viewHolder.courseIndex.setText(datas.get(position).getCourseIndexName());
        viewHolder.courseName.setText(datas.get(position).getCourseName());
        viewHolder.courseName.setTextColor(Color.parseColor(colors[random]));
        viewHolder.userName.setText("授课老师："+datas.get(position).getUserName());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView courseIndex;
        public TextView courseName;

        public TextView userName;
    }
}
