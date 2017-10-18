package com.youqd.kind.skind.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.youqd.kind.skind.LoginActivity;
import com.youqd.kind.skind.R;
import com.youqd.kind.skind.base.BaseActivity;

public class ForgetActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.rightId)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(LoginActivity.class);
                    }
                }
        );
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("找回密码");
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
