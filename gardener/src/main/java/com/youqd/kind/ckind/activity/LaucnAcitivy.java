package com.youqd.kind.ckind.activity;

import android.os.Handler;
import android.view.View;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.LoginActivity;
import com.youqd.kind.ckind.base.KingActivity;

/**
 * Created by chenxiu on 2017/8/13.
 */

public class LaucnAcitivy extends KingActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_launcn;
    }

    @Override
    protected void initViews() {
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
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
