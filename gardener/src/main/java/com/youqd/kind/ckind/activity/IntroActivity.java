package com.youqd.kind.ckind.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cosfund.library.refresh.PullToRefreshBase;
import com.cosfund.library.refresh.PullToRefreshScrollView;
import com.google.gson.Gson;
import com.kind.chx.gardener.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.adapter.GalleryAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.KindBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

public class IntroActivity extends KingActivity {

    private RecyclerView mRecyclerView;

    private GalleryAdapter mAdapter;

    private PullToRefreshScrollView mScrollView;

    @Override
    protected int initLayout() {
        return R.layout.activity_intro;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("幼儿园简介");
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview_horizontal);
        mScrollView = (PullToRefreshScrollView) findViewById(R.id.scrollview);
        findViewById(R.id.kind_info_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView) findViewById(R.id.kind_info)).setMaxLines(100);
                v.setVisibility(View.GONE);
            }
        });
        mScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                initdata();
            }
        });
        ((TextView) findViewById(R.id.kind_phone)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = UserManage.getInstance().getKind().getResultData().getTelephone();
                if (TextUtils.isEmpty(phone)) {

                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        String json = mACache.getAsString("IntroActivity_"+getUser().getKindergartenID());
        if(TextUtils.isEmpty(json)) {
            initdata();
        }else{
            KindBean data = new Gson().fromJson(json,KindBean.class);
            mScrollView.onRefreshComplete();
            ((TextView) findViewById(R.id.kind_info)).setText(data.getResultData().getIntroduction());
            ((TextView) findViewById(R.id.intro_images_tv)).setText(
                    "校园实景（"+data.getResultData().getImageURL().split(";").length+"）");
            ((TextView) findViewById(R.id.kind_phone)).setText("电话："+data.getResultData().getTelephone());
            ((TextView) findViewById(R.id.kind_add)).setText("地址："+data.getResultData().getLocation());
            //设置布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            //设置适配器
            mAdapter = new GalleryAdapter(mContext, data.getResultData().getImageURL().split(";"));
            mRecyclerView.setAdapter(mAdapter);
        }

        ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+UserManage.getInstance().getKind().getResultData().getLogo(), (ImageView) findViewById(R.id.top_icon),getOptions());

    }

    public void initdata(){
        HttpTool.getInstance().getKindInfo(getUser().getKindergartenID() + "",
                new KingCallback<KindBean>(KindBean.class) {
                    @Override
                    public void onSucceed(KindBean data) {
                        mACache.put("IntroActivity_"+getUser().getKindergartenID(),new Gson().toJson(data));
                        mScrollView.onRefreshComplete();
                        ((TextView) findViewById(R.id.kind_info)).setText(data.getResultData().getIntroduction());
                        ((TextView) findViewById(R.id.kind_phone)).setText("电话："+data.getResultData().getTelephone());
                        ((TextView) findViewById(R.id.kind_add)).setText("地址："+data.getResultData().getLocation());
                        //设置布局管理器
                        ((TextView) findViewById(R.id.intro_images_tv)).setText(
                                "校园实景（"+data.getResultData().getImageURL().split(";").length+"）");
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                        //设置适配器
                        mAdapter = new GalleryAdapter(mContext, data.getResultData().getImageURL().split(";"));
                        mRecyclerView.setAdapter(mAdapter);

                    }

                    @Override
                    public void onErro() {

                    }
                });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
