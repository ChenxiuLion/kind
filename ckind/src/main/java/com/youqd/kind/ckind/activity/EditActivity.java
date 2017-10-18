package com.youqd.kind.ckind.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.LoginBean;

/**
 * Created by Chenxiu on 2016/7/25.
 */
public class EditActivity extends KingActivity {

    private ImageView mImage;

    private EditText mBobyNameEv;

    @Override
    protected int initLayout() {
        return R.layout.activity_edit;
    }

    @Override
    protected void initViews() {
        mBobyNameEv = (EditText) findViewById(R.id.edit_boby_name);
        mBobyNameEv.setHint(getBaby().getUserName());

        findViewById(R.id.login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mBobyNameEv.getText().toString().trim();
                if(TextUtils.isEmpty(str)){
                    showShortToast("未做修改");
                    return;
                }else{
                    LoginBean.AllBaByBean baByBean = getBaby();
                    baByBean.setUserName(str);
                    setBaby(baByBean);
                    setResult(99,getIntent());
                    finish();
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
