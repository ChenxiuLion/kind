package com.youqd.kind.skind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.bean.SalaryBean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SalaryAdapter extends BaseAdapter {

    private List<SalaryBean> datas;
    private LayoutInflater li = null;

    public SalaryAdapter(Context context, List<SalaryBean> data) {
        this.datas = data;
        li = LayoutInflater.from(context);
    }

    public void setData(List<SalaryBean> datas) {
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
            convertView = li.inflate(R.layout.fragment_salary_item, null);
            viewHolder = new ViewHolder();
            viewHolder.salaryName = (TextView) convertView.findViewById(R.id.salary_name);
            viewHolder.salaryRemark = (TextView) convertView.findViewById(R.id.salary_remark);
            viewHolder.salaryImg = (ImageView) convertView.findViewById(R.id.salary_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.salaryName.setText(datas.get(position).getSalaryName());
        viewHolder.salaryRemark.setText(datas.get(position).getSalaryRemark());
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView salaryName;
        public TextView salaryRemark;
        public ImageView salaryImg;
    }
}
