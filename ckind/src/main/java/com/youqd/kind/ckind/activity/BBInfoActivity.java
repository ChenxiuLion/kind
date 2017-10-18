package com.youqd.kind.ckind.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.cxandroid.imageutil.imageload.SelectImageActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.ImageBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.UpdataBoby;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UpdataTool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BBInfoActivity extends KingActivity {
    private ArrayList<ImageBean> mPaths = new ArrayList<>();

    private ArrayList<String> mImages = new ArrayList<>();

    private String mUserImageStr;

    private ImageView mUserIv;

    private LoginBean.AllBaByBean mBean;

    private TextView mDataTv;

    @Override
    protected int initLayout() {
        return R.layout.activity_bbinfo;
    }

    @Override
    public void onBack(View v) {

        super.onBack(v);
    }

    @Override
    public void onBackPressed() {
        HttpTool.getInstance().upDataBoby(mBean, new KingCallback<UpdataBoby>(UpdataBoby.class) {
            @Override
            public void onSucceed(UpdataBoby data) {

            }

            @Override
            public void onErro() {
            }
        });
        super.onBackPressed();
    }

    @Override
    protected void initViews() {
        mBean = getBaby();
        if(mBean==null){
            showShortToast("您还没有宝宝");
            finish();
        }
        mDataTv = (TextView) findViewById(R.id.date_info);
        mDataTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  showData();
            }
        });
        ((TextView)findViewById(R.id.top_title)).setText("宝宝信息");
        ((TextView)findViewById(R.id.boby_info_name)).setText(mBean.getUserName());
        ((TextView)findViewById(R.id.school_name)).setText(mBean.getSchoolName());
        ((TextView)findViewById(R.id.class_name)).setText(mBean.getClassName());

        HttpTool.getInstance().getGradeName(mBean.getGradeID()+"",
                new KingCallback<Grade>(Grade.class) {
                    @Override
                    public void onSucceed(Grade data) {
                        ((TextView)findViewById(R.id.nj_name)).setText(data.getResultData().getName());
                    }

                    @Override
                    public void onErro() {

                    }
                });

        mUserIv = (ImageView) findViewById(R.id.baby_info_image_view);
        ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+mBean.getPhoto(),mUserIv,getOptions(R.drawable.icon_phone_image));

        findViewById(R.id.rightId).setVisibility(View.GONE);

        findViewById(R.id.baby_info_image_lin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPhoto();
            }
        });
        findViewById(R.id.boby_name_lin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(mContext, EditActivity.class);
               // startActivityForResult(intent, 185);
            }
        });
        findViewById(R.id.upda_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simple4 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simple3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat simple2 = new SimpleDateFormat("M个月d天");
        String date = mBean.getBornDate().split(" ")[0];
        String newData = getCurrentDate();
        try {
            long cTiem = simple.parse(newData).getTime() - simple.parse(date).getTime();
            String vTime = simple4.format(simple.parse(date));
            String str = (compareDate(date,null,2)>0?compareDate(date,null,2)+"岁":"")+simple2.format(cTiem);
            ((TextView)findViewById(R.id.date_tv)).setText(str);
            mDataTv.setText(vTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void initEvents() {

    }

    public class Grade{

        /**
         * ID : 1
         * Name : sample string 2
         * CreateTime : 2016-08-21T01:10:49.9365234+08:00
         * Status : true
         * KindergartenID : 5
         */

        private ResultDataBean ResultData;
        /**
         * ResultData : {"ID":1,"Name":"sample string 2","CreateTime":"2016-08-21T01:10:49.9365234+08:00","Status":true,"KindergartenID":5}
         * ResultCode : 0
         * ResultMessage : sample string 1
         * ReusltDataTotal : 3
         */

        private int ResultCode;
        private String ResultMessage;
        private int ReusltDataTotal;

        public ResultDataBean getResultData() {
            return ResultData;
        }

        public void setResultData(ResultDataBean ResultData) {
            this.ResultData = ResultData;
        }

        public int getResultCode() {
            return ResultCode;
        }

        public void setResultCode(int ResultCode) {
            this.ResultCode = ResultCode;
        }

        public String getResultMessage() {
            return ResultMessage;
        }

        public void setResultMessage(String ResultMessage) {
            this.ResultMessage = ResultMessage;
        }

        public int getReusltDataTotal() {
            return ReusltDataTotal;
        }

        public void setReusltDataTotal(int ReusltDataTotal) {
            this.ReusltDataTotal = ReusltDataTotal;
        }

        public class ResultDataBean {
            private int ID;
            private String Name;
            private String CreateTime;
            private boolean Status;
            private int KindergartenID;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public boolean isStatus() {
                return Status;
            }

            public void setStatus(boolean Status) {
                this.Status = Status;
            }

            public int getKindergartenID() {
                return KindergartenID;
            }

            public void setKindergartenID(int KindergartenID) {
                this.KindergartenID = KindergartenID;
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
    private TimePickerView pvTime;

    public void showData(){
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //设置标题
        pvTime.setTitle("选择日期");
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 30, calendar.get(Calendar.YEAR));
        pvTime.setTime(new Date());
        //设置是否循环
        pvTime.setCyclic(true);
        //设置是否可以关闭
        pvTime.setCancelable(true);
        //设置选择监听
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                if(date.getTime()<System.currentTimeMillis()) {
                    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simple3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    SimpleDateFormat simple2 = new SimpleDateFormat("M个月d天");
                    String newData = getCurrentDate();
                    try {
                        long cTiem = simple.parse(newData).getTime() - date.getTime();
                        String str = (compareDate(simple.format(date), null, 2) > 0 ? compareDate(simple.format(date), null, 2) + "岁" : "") + getDay(cTiem);
                        ((TextView) findViewById(R.id.date_tv)).setText(str);
                        mBean.setBornDate(simple3.format(date));
                        setBaby(mBean);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    mDataTv.setText(getTime(date));
                }
            }
        });
        //显示
        pvTime.show();
    }

    public String getDay(long time){
        String day= "";

        SimpleDateFormat simple2 = new SimpleDateFormat("M个月d天");
        day = simple2.format(time);

        String str = day.split("个")[0];
        int i = Integer.parseInt(str);
        i--;
        if(i == 0){
            return day.split("个")[1].split("月")[1];
        }else{
            return i+"个"+day.split("个")[1];
        }
    }

    public void onPhoto(){
        Intent intent = new Intent(mContext, SelectImageActivity.class);
        intent.putExtra("images", "[]");
        intent.putExtra("maxCount", 1);
        startActivityForResult(intent, 189);
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            mPaths.clear();
            if(requestCode == 189) {
                ArrayList<String> images = data.getStringArrayListExtra("images");
                mImages.clear();
                mImages.addAll(images);
                for(String path : mImages){
                    ImageBean image = new ImageBean();
                    image.setPath(path);
                    image.setType(0);
                    mPaths.add(image);
                }
                showLoding("上传中",true);
                UpdataTool.getTool(mPaths).statrUpdata(new UpdataTool.OnUpdata() {
                    @Override
                    public void onIndex(int index) {

                    }

                    @Override
                    public void onSucceed(ArrayList<String> paths) {
                        for(String str : paths){
                            mBean.setPhoto(str);
                            setBaby(mBean);
                            mUserImageStr = str;
                            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL+str,mUserIv,getOptions());
                        }
                        stopLoding();
                    }

                    @Override
                    public void onErro() {
                        stopLoding();
                    }
                });
            }else if(requestCode == 185){
                mBean = getBaby();
                ((TextView)findViewById(R.id.boby_name_tv)).setText(mBean.getUserName());
            }

        }
    }



    /**
     * @param date1 需要比较的时间 不能为空(null),需要正确的日期格式
     * @param date2 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
     * @return
     */
    public static int compareDate(String date1,String date2,int stype){
        int n = 0;

        String[] u = {"天","月","年"};
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";

        date2 = date2==null?getCurrentDate():date2;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e3) {
            System.out.println("wrong occured");
        }
        //List list = new ArrayList();
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果
            //list.add(df.format(c1.getTime()));    // 这里可以把间隔的日期存到数组中 打印出来
            n++;
            if(stype==1){
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1
            }
            else{
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1
            }
        }

        n = n-1;

        if(stype==2){
            n = (int)n/365;
        }

        System.out.println(date1+" -- "+date2+" 相差多少"+u[stype]+":"+n);
        return n;
    }

    /**
     * 得到当前日期
     * @return
     */
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date);
    }
}
