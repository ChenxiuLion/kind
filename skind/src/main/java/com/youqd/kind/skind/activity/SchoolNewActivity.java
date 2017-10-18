package com.youqd.kind.skind.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.adapter.RecordAdapter;
import com.youqd.kind.skind.base.BaseActivity;
import com.youqd.kind.skind.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

public class SchoolNewActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private ListView newListView = null;
    private RecordAdapter newAdapter = null;
    private List<RecordBean> beanList = null;
    private ImageView rightImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_new);
        ((TextView)findViewById(R.id.top_title)).setText("校园新闻");
        newListView = (ListView)findViewById(R.id.newList);
        newAdapter = new RecordAdapter(this,getData());
        newListView.setAdapter(newAdapter);
        newListView.setOnItemClickListener(this);
        rightImg = (ImageView)findViewById(R.id.rightId);
        rightImg.setImageResource(R.drawable.add_gray_selector);
        rightImg.setOnClickListener(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rightId:
                startActivity(NewAddActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString(NewDetailActivity.TITLE,beanList.get(position).getTitle());
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

}
