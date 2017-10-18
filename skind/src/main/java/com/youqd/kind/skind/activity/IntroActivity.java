package com.youqd.kind.skind.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.base.BaseActivity;

public class IntroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("幼儿园简介");
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
}
