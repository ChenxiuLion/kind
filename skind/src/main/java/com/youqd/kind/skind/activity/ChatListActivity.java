package com.youqd.kind.skind.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.adapter.ChatListAdapter;
import com.youqd.kind.skind.adapter.RecordAdapter;
import com.youqd.kind.skind.base.BaseActivity;
import com.youqd.kind.skind.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView newListView = null;
    private ChatListAdapter newAdapter = null;
    private List<RecordBean> beanList = null;
    private ImageView rightImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_new);
        ((TextView)findViewById(R.id.top_title)).setText("家长留言");
        newListView = (ListView)findViewById(R.id.newList);
        newAdapter = new ChatListAdapter(this,getData());
        newListView.setAdapter(newAdapter);
        newListView.setOnItemClickListener(this);
        rightImg = (ImageView)findViewById(R.id.rightId);
        rightImg.setVisibility(View.GONE);
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
        Bundle bundle = new Bundle();
        bundle.putString(ChatActivity.TITLE,beanList.get(position).getTitle());
        startActivity(ChatActivity.class,bundle);
    }

    public List<RecordBean> getData(){

        beanList = new ArrayList<RecordBean>();
        String[] titles = {"张小红妈妈","李小明的妈妈","赵小四的爸爸"};
        String[] contents = {"老师在吗？我们家小红今天表现怎么样？",
                "小明今天生病了，不能去上学了！",
                "在吗？"};
        RecordBean recordBean = null;

        for(int i = 0 ; i < 3; i++){
            recordBean = new RecordBean();
            recordBean.setId(i);
            recordBean.setTitle(titles[i]);
            recordBean.setContent(contents[i]);
            recordBean.setDataTime("01:02");
            beanList.add(recordBean);
        }

        return beanList;
    }

}
