package com.youqd.kind.ckind.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cosfund.library.refresh.PullToRefreshBase;
import com.cosfund.library.refresh.PullToRefreshScrollView;
import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.adapter.RecordAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.bean.NewBean;
import com.youqd.kind.ckind.bean.NewsModel;
import com.youqd.kind.ckind.bean.PingResult;
import com.youqd.kind.ckind.bean.PostNew;
import com.youqd.kind.ckind.bean.RecordBean;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

import java.util.ArrayList;
import java.util.List;

import chenxiu.zz.com.camer.CameraAty;

public class SchoolNewActivity extends KingActivity implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener{

    private ListView newListView = null;
    private RecordAdapter newAdapter = null;
    private List<RecordBean> beanList = null;
    private int mPage = 0;
    private  NewBean mBean;
    private PostNew mPostNew;
    private List<NewsModel.ResultDataBean> mData = new ArrayList<>();
    private PullToRefreshScrollView mSwiper;
    @Override
    protected int initLayout() {
        return R.layout.activity_school_new;
    }

    private boolean isScroll = true;
    @Override
    protected void initViews() {
        String title = getIntent().getStringExtra("title");
        ((TextView)findViewById(R.id.top_title)).setText(title);
        int type = getIntent().getIntExtra("type",1);
        newListView = (ListView)findViewById(R.id.newList);
        mSwiper = (PullToRefreshScrollView) findViewById(R.id.swipe_layout);
        ((ImageView)findViewById(R.id.rightId)).setImageResource(R.drawable.icon_daynmic_add);
        mSwiper.setMode(PullToRefreshBase.Mode.BOTH);
        findViewById(R.id.rightId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddNotifiActivity.class);
            }
        });
        mSwiper.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                mPage =0 ;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                isScroll = false;
                mPage++;
                initData();
            }
        });

        mPostNew = new PostNew();
        mPostNew.setAgentID(UserManage.getInstance().getKind().getResultData().getAgentID());
        mPostNew.setAscending(false);
        mPostNew.setKindergartenID(getUser().getKindergartenID());
        mPostNew.setNoticeType(type);
        mPostNew.setOrderBy("id");
        mPostNew.setPageSize(5);

        //mPostNew.setReadGradeID(getUser().getGradeID());
        //mPostNew.setReadClassID(getUser().getClassID());
        mPostNew.setPageIndex(mPage);
        if(type ==6){
            mPostNew.setSubNoticeType(2);
        }else if(type == 5){
            mPostNew.setSubNoticeType(3);
        }else{
            mPostNew.setSubNoticeType(1);
        }
        mBean = new NewBean();
        mBean.setKindergartenID(getUser().getKindergartenID());
        mBean.setPageIndex(mPage);
        mBean.setPageSize(5);
        mBean.setNoticeType(type);
        mBean.setReadClassID(getUser().getClassID());
        if(type ==6){
            mBean.setSubNoticeType(2);
            newListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                    if(mData.get(position).getCreateUserID() == getUser().getID()){
                        new SheetDialog(mContext)
                                .setCanceledOnTouchOutside(false)
                                .addSheetItems(new String[]{"删除"}
                                        , SheetDialog.SheetItemColor.Blue, new SheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                HttpTool.getInstance().deleteNotice(mData.get(position).getID(),
                                                        new KingCallback<PingResult>(PingResult.class) {
                                                            @Override
                                                            public void onSucceed(PingResult data) {
                                                                mPage =0 ;
                                                                initData();
                                                            }

                                                            @Override
                                                            public void onErro() {

                                                            }
                                                        });
                                            }
                                        }).show();
                    }
                    return true;
                }
            });
        }else{
            ((ImageView)findViewById(R.id.rightId)).setVisibility(View.GONE);
        }
        String json = mACache.getAsString("SchoolNewActivity_"+getUser().getUserAccount()+type);
        if(TextUtils.isEmpty(json)) {
            initData();
        }else{
            List<NewsModel.ResultDataBean> data = new Gson().fromJson(json,
                    new TypeToken<List<NewsModel.ResultDataBean>>(){}.getType());
            isScroll = true;
            mData.addAll(data);
        }
        newAdapter = new RecordAdapter(SchoolNewActivity.this,mData);
        newListView.setAdapter(newAdapter);
        newListView.setOnItemClickListener(SchoolNewActivity.this);
    }


    public void initData(){
        mPostNew.setPageIndex(mPage);
        HttpTool.getInstance().doNews(mPostNew, new KingCallback<NewsModel>(NewsModel.class) {
            @Override
            public void onSucceed(NewsModel data) {
                if(mPage == 0 ){
                    mData.clear();

                }
                isScroll = true;
                mData.addAll(data.getResultData());
                mACache.put("SchoolNewActivity_"+getUser().getUserAccount()+getIntent().getIntExtra("type",5),
                        new Gson().toJson(mData));
                newAdapter.notifyDataSetChanged();
                mSwiper.onRefreshComplete();
            }

            @Override
            public void onErro() {
                isScroll = true;
                mSwiper.onRefreshComplete();
            }
        });
    }
    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onResume() {
        initData();
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString(NewDetailActivity.TITLE,newAdapter.getData().get(position).getTitle());
        bundle.putString(NewDetailActivity.CONTENT,newAdapter.getData().get(position).getContent());
        bundle.putSerializable("bean",newAdapter.getData().get(position));
        startActivity(NewDetailActivity.class,bundle);
    }

    public List<RecordBean> getData(){

        beanList = new ArrayList<RecordBean>();
        String[] titles = {"关注幼儿 呵护健康","实验幼儿园健康体检活动报道","教师示范课展示及研讨活动"};
        String[] contents = {"根据幼儿园卫生保健工作要求，实验幼儿园联合县妇幼保健院于5月15日、16日为全园幼儿进行了春季体检...",
                "通过一年一度的身体检查，不仅可以及时了解幼儿的生长发育及身体状况...",
                "为了全面提高该园教师的教育教学能力和业务素质，充分发挥骨干教师..."};
        RecordBean recordBean = null;

        for(int i = 0 ; i < 3; i++){
            recordBean = new RecordBean();
            recordBean.setId(i);
            recordBean.setTitle(titles[i]);
            recordBean.setContent(contents[i]);
            recordBean.setSource("来自三二班");
            recordBean.setDataTime("2012-11-06 01:02");
            beanList.add(recordBean);
        }

        return beanList;
    }

    @Override
    public void onRefresh() {
        mPage = 0;
        mPostNew.setKindergartenID(getUser().getKindergartenID());
        mPostNew.setPageIndex(mPage);
        mPostNew.setPageSize(5);
        initData();
    }
}
