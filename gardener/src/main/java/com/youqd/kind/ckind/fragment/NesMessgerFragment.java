package com.youqd.kind.ckind.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cosfund.library.refresh.PullToRefreshBase;
import com.cosfund.library.refresh.PullToRefreshListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.activity.ChatActivity;
import com.youqd.kind.ckind.activity.MainActivity;
import com.youqd.kind.ckind.activity.NewDetailActivity;
import com.youqd.kind.ckind.adapter.Contact4Adapter;
import com.youqd.kind.ckind.bean.ContactBean;
import com.youqd.kind.ckind.bean.GetJobInfo;
import com.youqd.kind.ckind.bean.MessgerList;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenxiu on 2016/8/18.
 */
public class NesMessgerFragment extends KingFragment {

    private int mPager =0;

    private PullToRefreshListView mListView;
    private Contact4Adapter mAdapter;
    private List<MessgerList.Gardener> mData = new ArrayList<>();
    @Override
    public int initLayout() {
        return R.layout.fragment_mes;
    }

    @Override
    public void initData() {
        findViewById(R.id.base_back).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.top_title)).setText("未读消息");
        mListView = (PullToRefreshListView) findViewById(R.id.swipe_layout);
        mAdapter = new Contact4Adapter(getActivity(),mData);
        mListView.setAdapter(mAdapter);
        String json = mACache.getAsString("NesMessgerFragment1_"+ getUser().getUserAccount());
        if(!TextUtils.isEmpty(json)){
            List<MessgerList.Gardener> dataBeen= new Gson().fromJson(json,
                    new TypeToken<List<MessgerList.Gardener>>(){}.getType());
            mData.addAll(dataBeen);
            mAdapter.notifyDataSetChanged();
        }
        getData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessgerList.Gardener bean = mData.get(position-1);
                if(bean.getGardenerType()==1) {
                    ContactBean contactBean = new ContactBean();
                    contactBean.setImage(bean.getGardenerPhoto());
                    contactBean.setId(bean.getGardenerID());
                    contactBean.setName(bean.getGardenerName());
                    contactBean.setTel("12");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",contactBean);
                    startActivity(ChatActivity.class,bundle);
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("id",bean.getGardenerID()+"");
                    startActivity(NewDetailActivity.class,bundle);
                }

                HttpTool.getInstance().lookMessger(bean, new KingCallback<GetJobInfo>(GetJobInfo.class) {
                    @Override
                    public void onSucceed(GetJobInfo data) {
                        getData();
                        if(MainActivity.mActivity!=null){
                            MainActivity.mActivity.getMessgerData();
                        }
                    }

                    @Override
                    public void onErro() {

                    }
                });
            }
        });
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mPager = 0;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                mPager++;
                getData();
            }
        });


    }

    public void getData(){
        HttpTool.getInstance().getWDMessger(getUser().getKindergartenID()+"",getUser().getID()+"",mPager,
                new KingCallback<MessgerList>(MessgerList.class) {
                    @Override
                    public void onSucceed(MessgerList data) {
                        if(mPager == 0){
                            mData.clear();
                        }
                        mData.addAll(data.getMessgers());
                        Logger.json(new Gson().toJson(data.getMessgers()));
                        mACache.put("NesMessgerFragment1_"+ getUser().getUserAccount(),new Gson().toJson(mData));

                        mAdapter.notifyDataSetChanged();
                        mListView.onRefreshComplete();
                    }

                    @Override
                    public void onErro() {

                    }
                });
    }
}
