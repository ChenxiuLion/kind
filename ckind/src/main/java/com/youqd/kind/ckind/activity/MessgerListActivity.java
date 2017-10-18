package com.youqd.kind.ckind.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cosfund.library.refresh.PullToRefreshBase;
import com.cosfund.library.refresh.PullToRefreshListView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.adapter.Contact2Adapter;
import com.youqd.kind.ckind.adapter.Contact3Adapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ContactBean;
import com.youqd.kind.ckind.bean.GardenerList;
import com.youqd.kind.ckind.bean.MessgerList;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenxiu on 2016/8/16.
 */

public class MessgerListActivity extends KingActivity implements AdapterView.OnItemClickListener{

    private PullToRefreshListView listView = null;
    private Contact3Adapter adapter = null;
    private List<MessgerList.Gardener> beanList  = new ArrayList<>();

    private EditText mEditTv;


    @Override
    protected int initLayout() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MessgerActivity.class);
            }
        });
        ((ImageView)findViewById(R.id.rightId)).setImageResource(R.drawable.icon_laoshis);
        ((TextView)findViewById(R.id.top_title)).setText("全部留言");
        listView = (PullToRefreshListView)findViewById(R.id.recodeList);
        mEditTv = (EditText) findViewById(R.id.et_search_keyword);
        findViewById(R.id.contact_top).setVisibility(View.GONE);
        mEditTv.setVisibility(View.GONE);
        listView.setOnItemClickListener(this);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();
            }
        });
        initData();
    }


    public void initData(){
        HttpTool.getInstance().doMessgerListg(getUser().getID()+"",new KingCallback<MessgerList>(MessgerList.class) {
                    @Override
                    public void onSucceed(MessgerList data) {

                        if(data!=null) {
                            beanList.clear();
                            beanList.addAll(data.getMessgers());
                        }
                        adapter = new Contact3Adapter(mContext,beanList);
                        listView.setAdapter(adapter);
                        listView.onRefreshComplete();
                    }

                    @Override
                    public void onErro() {
                        listView.onRefreshComplete();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Logger.e(beanList.get(position).getTel());
        ContactBean contactBean = new ContactBean();
        contactBean.setImage(beanList.get(position-1).getGardenerPhoto());
        contactBean.setId(beanList.get(position-1).getGardenerID());
        contactBean.setName(beanList.get(position-1).getGardenerName());
        contactBean.setTel("12");
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",contactBean);
        startActivity(ChatActivity.class,bundle);
     //   Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + beanList.get(position).getTel()));
      //  startActivity(intent);
    }

}
