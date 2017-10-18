package com.youqd.kind.ckind.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.fragment.BabyFragment;
import com.youqd.kind.ckind.fragment.TabFragment;

public class BabyActivity extends KingActivity {

    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String[] names = { "辣妈课堂", "育儿知识"};
    private ScrollIndicatorView indicator;


    @Override
    protected int initLayout() {
        return R.layout.activity_food;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("育儿知识");
        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        indicator = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
//        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.rgb(0, 165, 124), 0, ScrollBar.Gravity.CENTENT_BACKGROUND));
        indicator.setScrollBar(new ColorBar(this, Color.rgb(0, 165, 124), 5));
        // 设置滚动监听
        int selectColorId = R.color.page_title;
        int unSelectColorId = R.color.tab_top_text_1;
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColorId(this, selectColorId, unSelectColorId));

        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        inflate = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        indicator.setSplitAuto(true);
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
            return names.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position % names.length]);
            textView.setPadding(20, 0, 20, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            BabyFragment fragment = new BabyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(TabFragment.INTENT_INT_INDEX, position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            return FragmentListPageAdapter.POSITION_NONE;
        }

    };
}