package com.youqd.kind.ckind.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.BaseEditActivity;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.model.Ping;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/8/14.
 */
public class AddPingActivity extends BaseEditActivity {

    private EditText mEditText;

    private Ping mPing;

    @Override
    protected int initLayout() {
        return R.layout.add_ping_view;
    }

    @Override
    protected void initViews() {

        mEditText = (EditText) findViewById(R.id.addping_edit);
        mPing = (Ping) getIntent().getSerializableExtra("Data");
        findViewById(R.id.root_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.tijiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditText.getText().toString())) {
                    showShortToast("评论为空");
                    return;
                }
                mPing.setCommentContent(mEditText.getText().toString().trim());
                mPing.setCommentUserAccount(getUser().getUserAccount());
                mPing.setCommentUserName(getUser().getUserName());
                mPing.setTitle("0");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mPing.setCreateTime(dateFormat.format(new Date()));
                HttpTool.getInstance().AddPing(mPing, new KingCallback<JobResult>(JobResult.class) {
                    @Override
                    public void onSucceed(JobResult data) {
                        mPing.setID(data.getResultData());
                        getIntent().putExtra("Data",mPing);
                        setResult(100, getIntent());
                        finish();

                    }

                    @Override
                    public void onErro() {
                        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("发表失败")
                                .setContentText("请再次重新发表!")
                                .show();
                    }
                });

            }
        });
/*        mEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Logger.e(actionId+"");
                if(actionId == 0){
                    setResult(100,getIntent());
                    finish();
                    return true;
                }
                return false;
            }
        });*/
    }


    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
