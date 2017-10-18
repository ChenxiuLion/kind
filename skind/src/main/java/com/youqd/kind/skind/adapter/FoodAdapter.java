package com.youqd.kind.skind.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.bean.FoodBean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class FoodAdapter extends BaseAdapter {

    private List<FoodBean> datas;
    private LayoutInflater li = null;

    public FoodAdapter(Context context, List<FoodBean> data) {
        this.datas = data;
        li = LayoutInflater.from(context);
    }

    public void setData(List<FoodBean> datas) {
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
            convertView = li.inflate(R.layout.fragment_food_item, null);
            viewHolder = new ViewHolder();
            viewHolder.foodName = (TextView) convertView.findViewById(R.id.food_name);
            viewHolder.foodContent = (TextView) convertView.findViewById(R.id.food_content);
            viewHolder.foodImg = (ImageView) convertView.findViewById(R.id.food_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.foodName.setText(datas.get(position).getFoodIndexName());
        viewHolder.foodContent.setText(datas.get(position).getFoodContent());

        switch (datas.get(position).getIndexCode()) {
            case 0:
                viewHolder.foodImg.setImageResource(R.drawable.food1);
                break;
            case 1:
                viewHolder.foodImg.setImageResource(R.drawable.food2);
                break;
            case 2:
                viewHolder.foodImg.setImageResource(R.drawable.food3);
                break;
            case 3:
                viewHolder.foodImg.setImageResource(R.drawable.food4);
                break;
            case 4:
                viewHolder.foodImg.setImageResource(R.drawable.food5);
                break;
            default:
                viewHolder.foodImg.setImageResource(R.drawable.food1);
                break;
        }

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public TextView foodName;
        public TextView foodContent;
        public ImageView foodImg;
    }
}
