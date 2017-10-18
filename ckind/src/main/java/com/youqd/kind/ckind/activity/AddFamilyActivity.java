package com.youqd.kind.ckind.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.bean.ParentRelation;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/8/21.
 */
public class AddFamilyActivity extends KingActivity {

    private EditText mUserName,mUserNick,mUserPhone;
    @Override
    protected int initLayout() {
        return R.layout.activity_family;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("添加家人");
        mUserName = (EditText) findViewById(R.id.user_name_edit);
        mUserNick = (EditText) findViewById(R.id.user_nick_edit);
        mUserPhone = (EditText) findViewById(R.id.user_phone_edit);
        findViewById(R.id.login_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUserName.getText().toString().trim().replace("\n","");
                final String nick = mUserNick.getText().toString().trim().replace("\n","");
                final String phone = mUserPhone.getText().toString().trim().replace("\n","");

                if(TextUtils.isEmpty(nick)){
                    showShortToast("关系未填写");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    showShortToast("手机号未填写");
                    return;
                }
                if(!isMobileNO(phone)){
                    showShortToast("手机号不正确");
                    return;
                }
                final AddFamily family = new AddFamily();
                family.setUserName(getBaby().getUserName()+nick);
                family.setMasterParent(getUser().getID()+"");
                family.setUserAccount(phone);
                family.setUserPassword(phone.substring(7,phone.length()));
                family.setMobile(phone);
                family.setKindergartenID(getBaby().getKindergartenID());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                family.setVideoStartTime(format.format(new Date()));
                family.setVideoEndTime(format.format(new Date(System.currentTimeMillis()+10)));
                family.setLastLoginTime(format.format(new Date()));
                HttpTool.getInstance().AddFamily(family,
                        new KingCallback<JobResult>(JobResult.class) {
                            @Override
                            public void onSucceed(JobResult data) {
                                if(data!=null) {
                                    if (data.getResultCode() == 1) {

                                        ParentRelation da = new ParentRelation();
                                        da.setBabyID(getBaby().getID());
                                        da.setParentID(data.getResultData());
                                        da.setParentName(family.getUserName());
                                        da.setRelation(nick);

                                        HttpTool.getInstance().addFamily2(da, new KingCallback<JobResult>(JobResult.class) {
                                            @Override
                                            public void onSucceed(JobResult data) {
                                                if(data.getResultCode() ==  1) {
                                                    new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                                                            .setTitleText("添加成功")
                                                            .setContentText("家人添加成功，初始密码为：" + phone.substring(7, phone.length()))
                                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                @Override
                                                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                    sweetAlertDialog.dismiss();
                                                                    finish();
                                                                }
                                                            })
                                                            .show();
                                                }
                                            }

                                            @Override
                                            public void onErro() {

                                            }
                                        });

                                    } else {
                                        showShortToast("添加失败");
                                    }
                                }
                            }

                            @Override
                            public void onErro() {
                                showShortToast("添加失败");
                            }
                        });
            }
        });
    }
    /**
     * 判断手机号是否正确
     * @param mobiles
     * @return
     */
    public boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((1[3,5,6,8][0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

    public class AddFamily{


        /**
         * UserName : 宝宝姓名+称呼
         * UserAccount : 手机号
         * UserPassword : 后四位
         * Mobile : 手机号
         * Status : true
         * VideoStartTime : 当前时间
         * VideoEndTime : 当前时间+1
         * LastLoginTime : 当前时间
         * KindergartenID : 1011
         * MasterParent : 家长ID
         * IsSuccess : true
         */

        private String UserName;
        private String UserAccount;
        private String UserPassword;
        private String Mobile;
        private boolean Status;
        private String VideoStartTime;
        private String VideoEndTime;
        private String LastLoginTime;
        private int KindergartenID;
        private String MasterParent;
        private boolean IsSuccess;

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getUserAccount() {
            return UserAccount;
        }

        public void setUserAccount(String UserAccount) {
            this.UserAccount = UserAccount;
        }

        public String getUserPassword() {
            return UserPassword;
        }

        public void setUserPassword(String UserPassword) {
            this.UserPassword = UserPassword;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public String getVideoStartTime() {
            return VideoStartTime;
        }

        public void setVideoStartTime(String VideoStartTime) {
            this.VideoStartTime = VideoStartTime;
        }

        public String getVideoEndTime() {
            return VideoEndTime;
        }

        public void setVideoEndTime(String VideoEndTime) {
            this.VideoEndTime = VideoEndTime;
        }

        public String getLastLoginTime() {
            return LastLoginTime;
        }

        public void setLastLoginTime(String LastLoginTime) {
            this.LastLoginTime = LastLoginTime;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public String getMasterParent() {
            return MasterParent;
        }

        public void setMasterParent(String MasterParent) {
            this.MasterParent = MasterParent;
        }

        public boolean isIsSuccess() {
            return IsSuccess;
        }

        public void setIsSuccess(boolean IsSuccess) {
            this.IsSuccess = IsSuccess;
        }
    }
}
