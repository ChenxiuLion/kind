package com.youqd.kind.skind.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.adapter.ContactAdapter;
import com.youqd.kind.skind.base.BaseActivity;
import com.youqd.kind.skind.bean.ContactBean;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView listView = null;
    private ContactAdapter adapter = null;
    private List<ContactBean> beanList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("家长联系录");
        listView = (ListView)findViewById(R.id.recodeList);
        adapter = new ContactAdapter(this,getData());
        listView.setAdapter(adapter);
        listView.setDivider(new ColorDrawable(Color.rgb(242, 242, 242)));
        listView.setDividerHeight(1);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + beanList.get(position).getTel()));
        startActivity(intent);
    }

    public List<ContactBean> getData(){

        beanList = new ArrayList<ContactBean>();
        String[] names = {"小明妈妈","小红妈妈","小高妈妈"};
        String[] tels = {"10086","10010","10010"};
        String[] positions = {"","",""};
        ContactBean bean = null;

        for(int i = 0 ; i < 3; i++){
            bean = new ContactBean();
            bean.setId(i);
            bean.setName(names[i]);
            bean.setTel(tels[i]);
            bean.setPosition(positions[i]);
            beanList.add(bean);
        }

        return beanList;
    }

}
