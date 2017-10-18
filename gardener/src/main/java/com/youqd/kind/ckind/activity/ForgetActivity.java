package com.youqd.kind.ckind.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosfund.library.util.GeneralUtils;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.LoginActivity;

import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.widget.CodeBtn;

public class ForgetActivity extends KingActivity {


    private EditText mEditPhone;

    private EditText mEditCode;

    private EditText mEditPasswrod;


    private CodeBtn mBtnGetCode;

    private Button mBtnNext;

    @Override
    protected int initLayout() {
        return R.layout.activity_forget;
    }

    @Override
    protected void initViews() {
        ((ImageView)findViewById(R.id.rightId)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(LoginActivity.class);
                    }
                }
        );

        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("忘记密码");

        mEditPhone = (EditText) findViewById(R.id.forget_phone_edit);
        mEditCode = (EditText) findViewById(R.id.forget_code_edit);
        mEditPasswrod = (EditText) findViewById(R.id.password);
        mBtnGetCode = (CodeBtn) findViewById(R.id.get_code_btn);

        mBtnNext = (Button) findViewById(R.id.forget_submit);

        mBtnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str  = mEditPhone.getText().toString();

                if(TextUtils.isEmpty(str)){
                    showShortToast("手机号为空");
                    return;
                }

                if(!GeneralUtils.isMobileNO(str)){
                    showShortToast("手机号码格式不正确！");
                    return;
                }
                HttpTool.getInstance().getCode(str, new KingCallback<JobResult>(JobResult.class) {
                    @Override
                    public void onSucceed(JobResult data) {
                        if(data.getResultCode() == 1){
                            mBtnGetCode.start(ForgetActivity.this);

                        }else{
                            showShortToast("用户不存在！");
                        }

                    }

                    @Override
                    public void onErro() {

                    }
                });
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = mEditCode.getText().toString();
                String phone = mEditPhone.getText().toString();
                String password = mEditPasswrod.getText().toString();

                HttpTool.getInstance().onForgetAccount(phone, password, code, new KingCallback<JobResult>(JobResult.class) {
                    @Override
                    public void onSucceed(JobResult data) {
                        if(data.getResultCode() == 1){
                            showShortToast("密码修改成功");
                            finish();
                        }
                    }

                    @Override
                    public void onErro() {

                    }
                });
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
