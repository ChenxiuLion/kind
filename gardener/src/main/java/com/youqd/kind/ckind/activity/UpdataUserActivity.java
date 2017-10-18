package com.youqd.kind.ckind.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.PingResult;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

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
        mUserNameEt.setHint(mUser.getIntroduce());
        ((TextView)findViewById(R.id.top_title)).setText("修改信息");

        findViewById(R.id.rightId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String str = mUserNameEt.getText().toString().trim().replace("\n","");
                if(TextUtils.isEmpty(str)){
                    showShortToast("没有改动");
                    return;
                }else{
                    mUser.setIntroduce(str);
                    HttpTool.getInstance().upUser(mUser,
                            new KingCallback<PingResult>(PingResult.class) {
                                @Override
                                public void onSucceed(PingResult data) {

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
