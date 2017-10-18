package com.youqd.kind.ckind.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.TextView;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;

/**
 * Created by Chenxiu on 2016/12/16.
 */

public class AbuotActivity extends KingActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_abuot;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("关于我们");
        ((TextView)findViewById(R.id.code_tv)).setText("版本号："+getVersionName(this));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

}
