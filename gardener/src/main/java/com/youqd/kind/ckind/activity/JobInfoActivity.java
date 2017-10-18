package com.youqd.kind.ckind.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.adapter.JobAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.JobBean;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.bean.PostJob;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.model.KingVideos;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.MyGridView;
import com.youqd.kind.ckind.util.UpdataNewTool;
import com.youqd.kind.ckind.video.PlayVideoActiviy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import chenxiu.zz.com.camer.CameraAty;
import cn.pedant.SweetAlert.SweetAlertDialog;
import krelve.view.Kanner;

/**
 * Created by Chenxiu on 2016/7/26.
 */
public class JobInfoActivity extends KingActivity {

    private JobBean mJobBean;

    private ListView mListView;

    private List<JobBean> mDatas = new ArrayList<>();



    @Override
    protected int initLayout() {
        return R.layout.activity_job_info;
    }

    @Override
    protected void initViews() {
        mJobBean = (JobBean) getIntent().getSerializableExtra("data");
        findViewById(R.id.rightId).setVisibility(View.GONE);


        ((TextView) findViewById(R.id.top_title)).setText("作业详情");

        mListView = (ListView) findViewById(R.id.recodeList);
        mDatas.add(mJobBean);

        JobAdapter mAdapter = new JobAdapter(mContext,mDatas,true);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void finish() {

        super.finish();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
