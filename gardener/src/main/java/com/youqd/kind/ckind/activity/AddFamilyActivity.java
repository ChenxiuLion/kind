package com.youqd.kind.ckind.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.beiing.leafchart.LeafLineChart;
import com.beiing.leafchart.bean.Axis;
import com.beiing.leafchart.bean.AxisValue;
import com.beiing.leafchart.bean.Line;
import com.beiing.leafchart.bean.PointValue;
import com.kind.chx.gardener.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.AllBoby;
import com.youqd.kind.ckind.bean.CheckList;
import com.youqd.kind.ckind.bean.JobResult;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;
import com.youqd.kind.ckind.util.UserManage;
import com.youqd.kind.ckind.widget.MyListView;
import com.youqd.kind.ckind.widget.RateTextCircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/8/21.
 */
public class AddFamilyActivity extends KingActivity {

    private RateTextCircularProgressBar mProgress;

    private MyListView mListView;

    private String tabIndex;

    private AllBoby mAllboby;

    LeafLineChart fuckLineChart;

    private int max , qiandao;

    @Override
    protected int initLayout() {
        return R.layout.activity_family;
    }

    @Override
    protected void initViews() {

        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        mAllboby = UserManage.getInstance().getAllBoby();
        max = mAllboby.getResultData().size();
        tabIndex = format.format(new Date());
        findViewById(R.id.rightId).setVisibility(View.GONE);
        fuckLineChart = (LeafLineChart) findViewById(R.id.leaf_chart);
        ((TextView)findViewById(R.id.top_title)).setText("班级考勤率");
        ((TextView)findViewById(R.id.date_top)).setText(tabIndex);
        ((TextView)findViewById(R.id.date_top2)).setText(tabIndex+"未到校名单");
        mProgress = (RateTextCircularProgressBar)findViewById(R.id.kql_jindu);
        mListView = (MyListView)findViewById(R.id.xuesheng_list);
        mProgress.setPrimaryColor(getResources().getColor(R.color.orange));
        mProgress.setBackgroundColor(Color.WHITE);

        initLineChart();
        HttpTool.getInstance().getBabyQiandao(format2.format(new Date()), UserManage.getInstance().getUser().getClassID()+"",
                new KingCallback<CheckList>(CheckList.class) {
                    @Override
                    public void onSucceed(CheckList data) {
                        for(CheckList.ResultDataBean bean : data.getResultData()){
                            updata(bean);
                        }
                        mProgress.setMax(100);
                        mProgress.setProgress((int) (((float)qiandao / max) * 100));
                        mListView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onErro() {

                    }
                });
    }
    private void initLineChart() {
        Axis axisX = new Axis(getAxisValuesX());
        axisX.setTextSize(10).setAxisColor(Color.parseColor("#00A57C")).setTextColor(Color.parseColor("#00A57C")).setHasLines(false);
        Axis axisY = new Axis(getAxisValuesY());
        axisY.setTextSize(8).setAxisColor(Color.parseColor("#00A57C")).setTextColor(Color.parseColor("#00A57C")).setHasLines(false).setShowText(true);
        fuckLineChart.setAxisX(axisX);
        fuckLineChart.setAxisY(axisY);
        fuckLineChart.setChartData(getFoldLine());
        fuckLineChart.showWithAnimation(3000);

//        fuckLineChart.show();
    }


    private List<AxisValue> getAxisValuesX(){
        List<AxisValue> axisValues = new ArrayList<>();
            AxisValue value = new AxisValue();
            value.setLabel("周一");
            axisValues.add(value);

        AxisValue value2 = new AxisValue();
        value2.setLabel("周二");
        axisValues.add(value2);

        AxisValue value22 = new AxisValue();
        value22.setLabel("周三");
        axisValues.add(value22);

        AxisValue value222 = new AxisValue();
        value222.setLabel("周四");
        axisValues.add(value222);

        AxisValue value2222 = new AxisValue();
        value2222.setLabel("周五");
        axisValues.add(value2222);

        AxisValue value22222 = new AxisValue();
        value22222.setLabel("周六");
        axisValues.add(value22222);

        AxisValue value222222 = new AxisValue();
        value222222.setLabel("周日");
        axisValues.add(value222222);
        return axisValues;
    }

    private List<AxisValue> getAxisValuesY(){
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            AxisValue value = new AxisValue();
            value.setLabel(String.valueOf(i * 20)+"%");
            axisValues.add(value);
        }
        return axisValues;
    }

    private Line getFoldLine(){
        List<PointValue> pointValues = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            PointValue pointValue = new PointValue();
            pointValue.setX( (i - 1) / 11f);
            int var = (int) (Math.random() * 100);
            pointValue.setLabel(String.valueOf(var));
            pointValue.setY(var / 100f);
            pointValues.add(pointValue);
        }

        Line line = new Line(pointValues);
        line.setLineColor(Color.RED)
                .setLineWidth(1)
                .setPointColor(Color.RED)
                .setCubic(false)
                .setPointRadius(3)
                .setFill(false)
                .setHasLabels(false)
                .setLabelColor(Color.parseColor("#00A57C"));
        return line;
    }
    public void updata(CheckList.ResultDataBean bean){
        for(AllBoby.ResultDataBean boby : mAllboby.getResultData()){

            if(bean.getCheckType()==4){
                if (boby.getID() == bean.getBabyID()) {
                    boby.setDesc(bean.getReason());
                    return;
                }
            }else if(bean.getCheckType()==1){
                if (boby.getID() == bean.getBabyID()) {
                    qiandao++;
                    mAllboby.getResultData().remove(boby);
                    return;
                }
            }
        }
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mAllboby.getResultData().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.kql_item,null);
            TextView classname = (TextView) convertView.findViewById(R.id.class_name);
            TextView name = (TextView) convertView.findViewById(R.id.user_name);
            TextView desc = (TextView)convertView.findViewById(R.id.user_desc);
            name.setText(mAllboby.getResultData().get(position).getUserName());
            if(!TextUtils.isEmpty(mAllboby.getResultData().get(position).getDesc())){
                desc.setText(mAllboby.getResultData().get(position).getDesc());
            }
            desc.setTextColor(Color.parseColor("#00A57C"));
            classname.setText(mAllboby.getResultData().get(position).getClassName());
            name.setTextColor(Color.parseColor("#00A57C"));
            return convertView;
        }
    };
    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

}
