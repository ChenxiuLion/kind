package com.youqd.kind.ckind.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.GetUserName;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AccountActivity extends KingActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_account;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("看宝宝账户");

        HttpTool.getInstance().getUserName(getUser().getID()+"", new KingCallback<GetUserName>(GetUserName.class) {
            @Override
            public void onSucceed(GetUserName data) {
            }

            @Override
            public void onErro() {

            }
        });
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ((TextView) findViewById(R.id.baby_left_name)).setText("手机号（账号）："+getUser().getMobile());


    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
