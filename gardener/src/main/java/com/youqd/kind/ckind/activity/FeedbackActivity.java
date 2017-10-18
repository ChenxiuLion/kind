package com.youqd.kind.ckind.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

/**
 * Created by chenxiu on 2017/8/6.
 */

public class FeedbackActivity extends KingActivity {

    private EditText mEditText;

    private Button mSmtBtn;
    @Override
    protected int initLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("意见反馈");

        mEditText = (EditText) findViewById(R.id.edit_feed);
        mSmtBtn = (Button) findViewById(R.id.login_submit);

        mSmtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEditText.getText().toString();

                if(!TextUtils.isEmpty(str)){
                    HttpTool.getInstance().doPostFeedback(str, new KingCallback<JobResult>(JobResult.class) {
                        @Override
                        public void onSucceed(JobResult data) {
                            if(data.getResultCode()!=1){
                                showShortToast("提交失败");
                                return;
                            }
                            showShortToast("提交成功");
                            finish();
                        }

                        @Override
                        public void onErro() {

                                showShortToast("提交失败");
                                return;

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
