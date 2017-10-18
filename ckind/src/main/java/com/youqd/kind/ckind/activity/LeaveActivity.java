package com.youqd.kind.ckind.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.LeaveBean;
import com.youqd.kind.ckind.model.LeaveResult;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chenxiu on 2016/8/24.
 */
public class LeaveActivity extends KingActivity {

    private Button mButton;

    private EditText mEdit;
    @Override
    protected int initLayout() {
        return R.layout.activity_leave;
    }

    @Override
    protected void initViews() {
        mButton = (Button) findViewById(R.id.smt_btn);
        mEdit = (EditText) findViewById(R.id.leave_edit);
        findViewById(R.id.rootview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mButton.setText("为"+getBaby().getUserName()+"请假");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mEdit.getText().toString().trim();
                if(TextUtils.isEmpty(str)){
                    showShortToast("请假理由未填写");
                    return;
                }
                LeaveBean bean = new LeaveBean();
                bean.setCardNO(getBaby().getCardNO());
                bean.setBabyID(getBaby().getID());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                bean.setCheckTime(simpleDateFormat.format(new Date()));
                bean.setCheckType(4);
                bean.setClassID(getBaby().getClassID());
                bean.setReason(str);
                bean.setUserName(getUser().getUserName());
                HttpTool.getInstance().doLeave(bean,
                        new KingCallback<LeaveResult>(LeaveResult.class) {
                            @Override
                            public void onSucceed(LeaveResult data) {
                                if(data.getResultCode()==1){
                                    showShortToast("请假成功");
                                    finish();
                                }else{
                                    showShortToast("请假失败");
                                }
                            }

                            @Override
                            public void onErro() {
                                showShortToast("请假失败");
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
