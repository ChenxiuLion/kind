package com.youqd.kind.ckind.activity;

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

import com.cosfund.library.refresh.PullToRefreshListView;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.adapter.Contact2Adapter;
import com.youqd.kind.ckind.adapter.ContactAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ContactBean;
import com.youqd.kind.ckind.bean.GardenerList;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenxiu on 2016/8/16.
 */

public class MessgerActivity extends KingActivity implements AdapterView.OnItemClickListener{

    private PullToRefreshListView listView = null;
    private Contact2Adapter adapter = null;
    private List<ContactBean> beanList  = new ArrayList<ContactBean>();

    private EditText mEditTv;


    @Override
    protected int initLayout() {
        return R.layout.activity_contact;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("选择老师");
        findViewById(R.id.contact_top).setVisibility(View.GONE);
        listView = (PullToRefreshListView)findViewById(R.id.recodeList);
        mEditTv = (EditText) findViewById(R.id.et_search_keyword);

        mEditTv.setVisibility(View.GONE);
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
                                bean.setPosition("老师");
                                bean.setImage(temp.getPhoto());
                                beanList.add(bean);
                            }
                            adapter = new Contact2Adapter(mContext,beanList);
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
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",beanList.get(position-1));
        startActivity(ChatActivity.class,bundle);
     //   Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + beanList.get(position).getTel()));
      //  startActivity(intent);
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
