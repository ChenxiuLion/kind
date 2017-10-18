package com.youqd.kind.ckind.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.PwdResult;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/8/21.
 */
public class OldPasswordActivity extends KingActivity {

    private EditText mOldPwdEdit;

    private EditText mNewPwdEdit;

    private EditText mNew2PwdEdit;

    @Override
    protected int initLayout() {
        return R.layout.activity_old_password;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("重置密码");
        mOldPwdEdit = (EditText) findViewById(R.id.updata_old_passwrod);
        mNewPwdEdit = (EditText) findViewById(R.id.updata_new_passwrod);
        mNew2PwdEdit = (EditText) findViewById(R.id.updata_new_passwrod2);

        findViewById(R.id.login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mOldPwdEdit.getText().toString().trim())){
                    showShortToast("旧密码未输入");
                    return;
                }
                if(TextUtils.isEmpty(mNewPwdEdit.getText().toString().trim())){
                    showShortToast("新密码未输入");
                    return;
                }
                if(!mNew2PwdEdit.getText().toString().trim().equals(mNewPwdEdit.getText().toString().trim())){
                    showShortToast("两次密码输入不一致");
                    return;
                }
                HttpTool.getInstance().updataPwd(
                        getUser().getUserAccount(),
                        mOldPwdEdit.getText().toString().trim(),
                        mNewPwdEdit.getText().toString().trim(),
                        false,
                        new KingCallback<PwdResult>(PwdResult.class) {
                            @Override
                            public void onSucceed(PwdResult data) {
                                if(data!=null){
                                    if(data.getResultCode()==1){
                                        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("修改成功")
                                                .setContentText("密码修改成功，新密码为：" + mNewPwdEdit.getText().toString().trim())
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        sweetAlertDialog.dismiss();
                                                        finish();
                                                    }
                                                })
                                                .show();
                                    }else{
                                        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("修改失败")
                                                .setContentText("密码修改失败，可能账号密码不匹配")
                                                .show();
                                    }
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
