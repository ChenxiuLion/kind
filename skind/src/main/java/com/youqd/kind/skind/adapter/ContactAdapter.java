package com.youqd.kind.skind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.bean.ContactBean;

import java.util.List;

/**
 * Created by youqd on 2016/3/24.
 */
public class ContactAdapter extends BaseAdapter {

    private List<ContactBean> datas;
    private LayoutInflater li = null;

    public ContactAdapter(Context context, List<ContactBean> data) {
        this.datas = data;
        li = LayoutInflater.from(context);
    }

    public void setData(List<ContactBean> datas) {
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
            convertView = li.inflate(R.layout.fragment_contact_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.contactName);
            viewHolder.tel = (TextView) convertView.findViewById(R.id.contactTel);
            viewHolder.contact = (TextView) convertView.findViewById(R.id.contactPost);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(datas.get(position).getName());
        viewHolder.tel.setText(datas.get(position).getTel());
        viewHolder.contact.setText(datas.get(position).getPosition());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView tel;
        public TextView contact;
    }
}
