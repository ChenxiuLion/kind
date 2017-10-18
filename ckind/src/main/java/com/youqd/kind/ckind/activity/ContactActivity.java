package com.youqd.kind.ckind.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.cosfund.library.refresh.PullToRefreshBase;
import com.cosfund.library.refresh.PullToRefreshListView;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.adapter.ContactAdapter;
import com.youqd.kind.ckind.adapter.RecordAdapter;
import com.youqd.kind.ckind.base.BaseActivity;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ContactBean;
import com.youqd.kind.ckind.bean.GardenerList;
import com.youqd.kind.ckind.bean.RecordBean;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends KingActivity implements AdapterView.OnItemClickListener{

    private PullToRefreshListView listView = null;
    private ContactAdapter adapter = null;
    private List<ContactBean> beanList  = new ArrayList<ContactBean>();

    private EditText mEditTv;


    @Override
    protected int initLayout() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("园丁通讯录");
        listView = (PullToRefreshListView)findViewById(R.id.recodeList);
        mEditTv = (EditText) findViewById(R.id.et_search_keyword);

        mEditTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                HttpTool.getInstance().doGardener(s.toString(),getBaby().getKindergartenID()+"",getBaby().getClassID()+"",
                        new KingCallback<GardenerList>(GardenerList.class) {
                            @Override
                            public void onSucceed(GardenerList data) {

                                if(data!=null) {
                                    beanList.clear();
                                    for (GardenerList.ResultDataBean temp : data.getResultData()) {
                                        ContactBean bean = new ContactBean();
                                        bean.setId(temp.getID());
                                        bean.setName(temp.getUserName());
                                        bean.setTel(temp.getMobile());
                                        bean.setPosition(temp.getPostTitle());
                                        bean.setImage(temp.getPhoto());
                                        beanList.add(bean);
                                    }
                                    adapter = new ContactAdapter(mContext,beanList);
                                    listView.setAdapter(adapter);
                                }
                            }

                            @Override
                            public void onErro() {

                            }
                        });
            }
        });
        listView.setMode(PullToRefreshBase.Mode.DISABLED);
        listView.setOnItemClickListener(this);
        HttpTool.getInstance().doGardener("",getBaby().getKindergartenID()+"",getBaby().getClassID()+"",
                new KingCallback<GardenerList>(GardenerList.class) {
                    @Override
                    public void onSucceed(GardenerList data) {

                        if(data!=null) {
                            beanList.clear();
                            for (GardenerList.ResultDataBean temp : data.getResultData()) {
                                ContactBean bean = new ContactBean();
                                bean.setId(temp.getID());
                                bean.setName(temp.getUserName());
                                bean.setTel(temp.getMobile());
                                bean.setPosition(temp.getPostTitle());

                                bean.setImage(temp.getPhoto());
                                beanList.add(bean);
                            }
                            adapter = new ContactAdapter(mContext,beanList);
                            listView.setAdapter(adapter);
                        }
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Logger.e(beanList.get(position).getTel());
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + beanList.get(position).getTel()));
        startActivity(intent);
    }

    public List<ContactBean> getData(){

        beanList = new ArrayList<ContactBean>();
        String[] names = {"张老师","李老师","赵老师"};
        String[] tels = {"10086","10010","10010"};
        String[] positions = {"园长","老师","老师"};
        ContactBean bean = null;


        return beanList;
    }

}
