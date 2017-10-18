package com.youqd.kind.skind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.bean.RecordBean;

import java.util.List;

/**
 * Created by youqd on 2016/3/24.
 */
public class RecordAdapter extends BaseAdapter {

    private List<RecordBean> datas;
    private LayoutInflater li = null;

    public RecordAdapter(Context context, List<RecordBean> data) {
        this.datas = data;
        li = LayoutInflater.from(context);
    }

    public void setData(List<RecordBean> datas) {
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
            convertView = li.inflate(R.layout.fragment_record_item, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.record_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.record_content);
            viewHolder.source = (TextView) convertView.findViewById(R.id.record_src);
            viewHolder.dataTime = (TextView) convertView.findViewById(R.id.record_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(datas.get(position).getTitle());
        viewHolder.content.setText(datas.get(position).getContent());
        viewHolder.source.setText(datas.get(position).getSource());
        viewHolder.dataTime.setText(datas.get(position).getDataTime());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView title;
        public TextView content;
        public TextView source;
        public TextView dataTime;
    }
}