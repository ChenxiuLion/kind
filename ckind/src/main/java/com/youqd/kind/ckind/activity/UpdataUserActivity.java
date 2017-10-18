package com.youqd.kind.ckind.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.PingResult;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UpdataTool;
import com.youqd.kind.ckind.util.Upload;
import com.youqd.kind.ckind.video.NewRecordVideoActivity;
import com.youqd.kind.ckind.video.PlayVideoActiviy;

import java.util.ArrayList;

/**
 * Created by Chenxiu on 2016/8/20.
 */
public class UpdataUserActivity extends KingActivity {



    private EditText mUserNameEt;

    private LoginBean mUser;

    @Override
    protected int initLayout() {
        return R.layout.activity_updata_user;
    }

    @Override
    protected void initViews() {
        mUser = getUser();
        mUserNameEt = (EditText) findViewById(R.id.updata_edit);
        mUserNameEt.setHint(mUser.getUserName());
        ((TextView)findViewById(R.id.top_title)).setText("修改信息");

        findViewById(R.id.rightId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String str = mUserNameEt.getText().toString().trim().replace("\n","");
                if(TextUtils.isEmpty(str)){
                    showShortToast("没有改动");
                    return;
                }else{
                    HttpTool.getInstance().upUser(mUser,
                            new KingCallback<PingResult>(PingResult.class) {
                                @Override
                                public void onSucceed(PingResult data) {
                                    mUser.setUserName(str);
                                    setUser(mUser);
                                    showShortToast("保存成功");
                                    setResult(169,getIntent());
                                    finish();
                                }

                                @Override
                                public void onErro() {
                                    showShortToast("保存失败");
                                }
                            });
                }
            }
        });

    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

}
