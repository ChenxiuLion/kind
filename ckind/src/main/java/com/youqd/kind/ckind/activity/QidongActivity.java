package com.youqd.kind.ckind.activity;

import android.os.Handler;
import android.view.View;

import com.youqd.kind.ckind.LoginActivity;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;

/**
 * Created by Chenxiu on 2016/12/4.
 */

public class QidongActivity extends KingActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_qidong;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(LoginActivity.class);
                        finish();
                    }
                });
            }
        },3000);
    }

    @Override
    public void onClick(View v) {

    }
}
