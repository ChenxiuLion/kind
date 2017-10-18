package com.youqd.kind.ckind.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.FragmentListPageAdapter;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.fragment.TabFragment;
import com.youqd.kind.ckind.fragment.WorkFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WorkActivity extends KingActivity {

    public static final String YYYYMMDD = "yyyy-MM-dd";

    public static final String YYYYMMDD_ZH = "yyyy年MM月dd日";

    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; // 中国周一是一周的第一天
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String[] names = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    private ScrollIndicatorView indicator;

    private List<Fragment> mFragments = new ArrayList<>();

    private static Calendar monday = Calendar.getInstance();
    @Override
    protected int initLayout() {
        return R.layout.activity_work;
    }

    @Override
    protected void initViews() {

        final SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD);
        ((TextView)findViewById(R.id.top_title)).setText("宝宝考勤");

        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        indicator = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.rgb(0, 165, 124), 0, ScrollBar.Gravity.CENTENT_BACKGROUND));

        // 设置滚动监听
        int selectColorId = R.color.white;
        int unSelectColorId = R.color.tab_top_text_1;
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColorId(this, selectColorId, unSelectColorId));
        int week = monday.get(Calendar.DAY_OF_WEEK);
        if(week==1){
            monday.add(Calendar.DAY_OF_WEEK,-1);
        }
        ((TextView)findViewById(R.id.week_tv)).setText(StringData(monday));
        getMondayOfWeek();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int temp = 0;
        for(int p = 0 ; p<names.length;p++){
            monday.add(Calendar.DAY_OF_WEEK,p);
            if(monday.get(Calendar.DAY_OF_MONTH)==Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
                 temp = p;
            }
            WorkFragment fragment = new WorkFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.INTENT_INT_INDEX, format1.format(monday.getTime()));
            fragment.setArguments(bundle);
            mFragments.add(fragment);


            getMondayOfWeek();
        }


        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        inflate = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        indicatorViewPager.setOnIndicatorPageChangeListener(new IndicatorViewPager.OnIndicatorPageChangeListener() {
            @Override
            public void onIndicatorPageChange(int preItem, int currentItem) {
                monday.add(Calendar.DAY_OF_WEEK,currentItem);
                getMondayOfWeek();
            }
        });
        indicator.setSplitAuto(true);
        viewPager.setCurrentItem(temp);
    }

    @Override
    protected void initEvents() {

    }



    @Override
    public void onClick(View v) {

    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position % names.length]);
            textView.setPadding(5, 0, 5, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {

            return mFragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return FragmentListPageAdapter.POSITION_NONE;
        }

    };

    /**
     * 根据日期取得对应周周一日期
     *
     * @return
     */
    public Date getMondayOfWeek() {
        monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return monday.getTime();
    }

    /**
     * 根据日期取得对应周周日日期
     *
     * @return
     */
    public  Date getSundayOfWeek() {
        monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
        monday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return monday.getTime();
    }
}
