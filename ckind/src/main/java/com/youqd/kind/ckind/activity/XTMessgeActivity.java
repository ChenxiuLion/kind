package com.youqd.kind.ckind.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cosfund.library.refresh.PullToRefreshBase;
import com.cosfund.library.refresh.PullToRefreshScrollView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.adapter.RecordAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.NewBean;
import com.youqd.kind.ckind.bean.NewsModel;
import com.youqd.kind.ckind.bean.RecordBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenxiu on 2016/12/4.
 */

public class XTMessgeActivity extends KingActivity {

    private ListView newListView = null;
    private List<RecordBean> beanList = null;
    private int mPage = 0;
    private NewBean mBean;
    private List<NewsModel.ResultDataBean> mData = new ArrayList<>();
    private PullToRefreshScrollView mSwiper;
    private XTAdapter newAdapter;
    @Override
    protected int initLayout() {
        return R.layout.activity_xtm;
    }

    public void initData(){
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("家园宝宝");
        mBean.setPageIndex(mPage);
        HttpTool.getInstance().getXT(mBean, new KingCallback<NewsModel>(NewsModel.class) {
            @Override
            public void onSucceed(NewsModel data) {
                if(mPage == 0 ){
                    mData.clear();

                }
                mData.addAll(data.getResultData());
                newAdapter.notifyDataSetChanged();
                mSwiper.onRefreshComplete();
            }

            @Override
            public void onErro() {
                mSwiper.onRefreshComplete();
            }
        });
    }
    @Override
    protected void initViews() {
        newListView = (ListView)findViewById(R.id.newList);
        mSwiper = (PullToRefreshScrollView) findViewById(R.id.swipe_layout);

        mSwiper.setMode(PullToRefreshBase.Mode.BOTH);
        mSwiper.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                mPage =0 ;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                mPage++;
                initData();
            }
        });
        newListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString(NewDetailActivity.TITLE,mData.get(position).getTitle());
                bundle.putString(NewDetailActivity.CONTENT,mData.get(position).getContent());
                bundle.putSerializable("bean",mData.get(position));
                startActivity(NewDetailActivity.class,bundle);
            }
        });
        mBean = new NewBean();
        mBean.setPageIndex(mPage);
        mBean.setPageSize(5);
        mBean.setNoticeType(4);
        newAdapter = new XTAdapter();
        initData();
        newListView.setAdapter(newAdapter);
    }

    private class XTAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xt_item,null);
            TextView title = (TextView) convertView.findViewById(R.id.xt_item_name);
            TextView date = (TextView) convertView.findViewById(R.id.xt_item_date);
            TextView contetn = (TextView) convertView.findViewById(R.id.xt_item_content);
            ImageView image = (ImageView) convertView.findViewById(R.id.xt_item_image);
            title.setText(mData.get(position).getTitle());
            date.setText(mData.get(position).getCreateTime());
            contetn.setText(mData.get(position).getContent());
            String[] images = mData.get(position).getImageURL().split(";");
            if(images!=null&&images.length>0){
                ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+images[0],image,KingActivity.getOptions());
            }else{
                image.setVisibility(View.GONE);
            }

            return convertView;
        }
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
