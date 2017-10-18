package com.youqd.kind.ckind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.MessgerList;
import com.youqd.kind.ckind.constant.Constant;

import java.util.List;

/**
 * Created by youqd on 2016/3/24.
 */
public class Contact3Adapter extends BaseAdapter {

    private List<MessgerList.Gardener> datas  ;
    private LayoutInflater li = null;

    public Contact3Adapter(Context context, List<MessgerList.Gardener> data) {
        this.datas = data;
        li = LayoutInflater.from(context);
    }

    public void setData(List<MessgerList.Gardener> datas) {
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
            convertView = li.inflate(R.layout.messger_item_view, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.contactName);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.phone_item_image);
            viewHolder.contact = (TextView)  convertView.findViewById(R.id.contactTel);
            viewHolder.tel =  (TextView)  convertView.findViewById(R.id.contactPost);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+datas.get(position).getGardenerPhoto(),viewHolder.image,
                KingActivity.getOptions(R.drawable.icon_phone_image));
        viewHolder.name.setText(datas.get(position).getGardenerName());
        for(MessgerList.Gardener.Messger messger : datas.get(position).getData()){
            viewHolder.contact.setText(messger.getContent());
            viewHolder.tel.setText(messger.getCreateTime().split(" ")[1].substring(0,5));
        }
       // viewHolder.contact.setText();
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
        public ImageView image;
    }
}
