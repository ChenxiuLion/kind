package com.youqd.kind.ckind.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.GetUserName;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

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

        HttpTool.getInstance().getUserName(getUser().getID()+"",getBaby().getID()+"", new KingCallback<GetUserName>(GetUserName.class) {
            @Override
            public void onSucceed(GetUserName data) {
                ((TextView) findViewById(R.id.baby_left_name)).setText(getBaby().getUserName()+"的"+data.getResultData().getRelation());
            }

            @Override
            public void onErro() {

            }
        });
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        findViewById(R.id.login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = UserManage.getInstance().getKind().getResultData().getTelephone();
                if (TextUtils.isEmpty(phone)) {

                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
        ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+getUser().getPhoto(), (ImageView) findViewById(R.id.user_image),getOptions());
        try {
            long endTime = format.parse(getUser().getVideoEndTime().replace("T"," ")).getTime();
            if(endTime<System.currentTimeMillis()){
                ((TextView) findViewById(R.id.look_date)).setText("已到期");
                findViewById(R.id.login_submit).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.look_date)).setTextColor(Color.RED);
            }else{
                ((TextView) findViewById(R.id.look_date)).setText(getUser().getVideoEndTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((TextView) findViewById(R.id.user_phone)).setText("手机号（账号）："+getUser().getUserAccount());


    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
