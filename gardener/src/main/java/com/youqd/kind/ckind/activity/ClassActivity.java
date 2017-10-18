package com.youqd.kind.ckind.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.adapter.RecordAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

public class ClassActivity extends KingActivity implements AdapterView.OnItemClickListener{

    private ListView newListView = null;
    private RecordAdapter newAdapter = null;
    private List<RecordBean> beanList = null;


    @Override
    protected int initLayout() {
        return R.layout.activity_class;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("通知活动");
        newListView = (ListView)findViewById(R.id.newList);
        /*newAdapter = new RecordAdapter(this,getData());
        newListView.setAdapter(newAdapter);
        newListView.setOnItemClickListener(this);*/
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString(NewDetailActivity.TITLE,beanList.get(position).getTitle());
        startActivity(NewDetailActivity.class,bundle);
    }

    public List<RecordBean> getData(){

        beanList = new ArrayList<RecordBean>();
        String[] titles = {"春游活动","组织学习英语","植树活动"};
        String[] contents = {"古往今来，春游冠得许多雅称",
                "所有外教全部来自欧美，持有全球认证教师资格证书",
                "为了全面提高该园教师的教育教学能力和业务素质"};
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

}
