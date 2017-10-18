package com.youqd.kind.skind;

import android.os.Bundle;
import android.view.View;

import com.lzy.okhttputils.OkHttpUtils;
import com.youqd.kind.skind.activity.ForgetActivity;
import com.youqd.kind.skind.activity.MainActivity;
import com.youqd.kind.skind.base.BaseActivity;

public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OkHttpUtils.init(getApplication());
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews() {

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login_submit){
            startActivity(MainActivity.class);
        }else{
            startActivity(ForgetActivity.class);
        }
    }
}
