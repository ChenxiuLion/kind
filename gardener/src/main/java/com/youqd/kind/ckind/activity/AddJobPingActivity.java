package com.youqd.kind.ckind.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.BaseEditActivity;
import com.youqd.kind.ckind.bean.JobList;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.bean.PingResult;
import com.youqd.kind.ckind.model.Ping;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/8/14.
 */
public class AddJobPingActivity extends BaseEditActivity {

    private EditText mEditText;

    private JobList.ResultDataBean mUserBean;

    @Override
    protected int initLayout() {
        return R.layout.add_ping_view;
    }

    @Override
    protected void initViews() {

        mEditText = (EditText) findViewById(R.id.addping_edit);
        mUserBean = (JobList.ResultDataBean) getIntent().getSerializableExtra("data");
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
                mUserBean.setEvaluation(mEditText.getText().toString().trim());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                mUserBean.setEvaluationByCreateTime(dateFormat.format(new Date()));
                mUserBean.setEvaluationByID(getUser().getID());
                mUserBean.setEvaluationByName(getUser().getUserName());
                HttpTool.getInstance().addJobPing(mUserBean, new KingCallback<PingResult>(PingResult.class) {
                    @Override
                    public void onSucceed(PingResult data) {
                        getIntent().putExtra("DATA",mUserBean);
                        setResult(100, getIntent());
                        finish();

                    }

                    @Override
                    public void onErro() {
                        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("评论失败")
                                .setContentText("请再次评论!")
                                .show();
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
