package com.youqd.kind.skind.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.base.BaseActivity;

public class SeeActivity extends BaseActivity {

    private int currId = 0;
    private ImageView topPic;
    private ImageView btmPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("看宝宝");

        topPic = (ImageView)findViewById(R.id.seeTopPic);
        btmPic = (ImageView)findViewById(R.id.seeBtmPic);

    }


    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {
        if(currId == 0){
            currId = 1;
        }else{
            currId = 0;
        }

        if(currId == 1){
            topPic.setImageResource(R.drawable.icon_see_school);
            btmPic.setImageResource(R.drawable.icon_see_btm_school);
        }else{
            topPic.setImageResource(R.drawable.icon_see_class);
            btmPic.setImageResource(R.drawable.icon_see_btm_class);
        }
    }
}
