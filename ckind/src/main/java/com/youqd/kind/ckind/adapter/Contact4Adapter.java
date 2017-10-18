package com.youqd.kind.ckind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.MessgerList;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.UserManage;

import java.util.List;

/**
 * Created by youqd on 2016/3/24.
 */
public class Contact4Adapter extends BaseAdapter {

    private List<MessgerList.Gardener> datas;
    private LayoutInflater li = null;

    public Contact4Adapter(Context context, List<MessgerList.Gardener> data) {
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

        MessgerList.Gardener bean = (MessgerList.Gardener) getItem(position);
        if(bean.getGardenerType()==1) {
            ViewHolder viewHolder = null;
            convertView = li.inflate(R.layout.messger_item_view, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.contactName);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.phone_item_image);
            viewHolder.contact = (TextView) convertView.findViewById(R.id.contactTel);
            viewHolder.tel = (TextView) convertView.findViewById(R.id.contactPost);
            convertView.setTag(viewHolder);


            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + datas.get(position).getGardenerPhoto(), viewHolder.image,
                    KingActivity.getOptions(R.drawable.icon_phone_image));
            viewHolder.name.setText(datas.get(position).getGardenerName());
            for (MessgerList.Gardener.Messger messger : datas.get(position).getData()) {
                viewHolder.contact.setText(messger.getContent());
                viewHolder.tel.setText(messger.getCreateTime().split(" ")[1].substring(0, 5));
                break;
            }
        }else{
            ViewHolder2 viewHolder = null;
            convertView = li.inflate(R.layout.fragment_record_item, null);
            viewHolder = new ViewHolder2();
            viewHolder.title = (TextView) convertView.findViewById(R.id.record_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.record_content);
            viewHolder.source = (TextView) convertView.findViewById(R.id.record_src);
            viewHolder.dataTime = (TextView) convertView.findViewById(R.id.record_time);
            ImageView newImage = (ImageView) convertView.findViewById(R.id.icon_record_new);
            viewHolder.title.setText(bean.getGardenerName());
            viewHolder.content.setText(bean.getGardenerAccount());
            viewHolder.source.setText("");
            viewHolder.dataTime.setText(bean.getGardenerPhoto().replace("T"," "));
            newImage.setVisibility(View.VISIBLE);
        }
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

    public static class ViewHolder2 {
        public TextView title;
        public TextView content;
        public TextView source;
        public TextView dataTime;
    }
}
