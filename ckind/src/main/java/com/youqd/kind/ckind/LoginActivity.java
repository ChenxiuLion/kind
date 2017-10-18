package com.youqd.kind.ckind;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cosfund.library.util.CipherUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.activity.ForgetActivity;
import com.youqd.kind.ckind.activity.MainActivity;
import com.youqd.kind.ckind.base.BaseApplication;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;

import cn.jpush.android.api.JPushInterface;

public class LoginActivity extends KingActivity {

    private EditText mUserName;

    private EditText mUserPassWord;

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews() {
        mUserName = (EditText) findViewById(R.id.user_name_edit);
        mUserPassWord = (EditText) findViewById(R.id.user_password_edit);
        if(getUser()!=null){
            startActivity(MainActivity.class);
            finish();
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.forget_btn){
            startActivity(ForgetActivity.class);
            return;
        }

        if(TextUtils.isEmpty(mUserName.getText().toString())){
            showShortToast("账号未输入");
            return ;
        }
        if(TextUtils.isEmpty(mUserPassWord.getText().toString())){
            showShortToast("密码未输入");
            return ;
        }

        if(UserManage.getInstance().getKind()!=null){
            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+UserManage.getInstance().getKind().getResultData().getLogo(),
                    (ImageView) findViewById(R.id.icon_boy_iv),getOptions(R.drawable.icon_defult_logo));
        }

        HttpTool.getInstance().doLogin(mUserName.getText().toString(), mUserPassWord.getText().toString(),
                new KingCallback<LoginBean>(LoginBean.class) {
                    @Override
                    public void onSucceed(LoginBean data) {
                        if(data.isIsSuccess()){
                            setUser(data);
                            BaseApplication.getInstance().setToken(data.getTicket());
                            startActivity(MainActivity.class);
                            finish();
                        }else{
                            showShortToast("账号密码错误");
                        }
                    }

                    @Override
                    public void onErro() {
                        showShortToast("网络不给力");
                    }
                });
    }
}
