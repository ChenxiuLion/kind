package com.youqd.kind.skind.activity;

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
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.youqd.kind.skind.R;
import com.youqd.kind.skind.evaluation.utils.GlobalWeekDialog;
import com.youqd.kind.skind.fragment.FoodFragment;
import com.youqd.kind.skind.fragment.TabFragment;

public class FoodActivity extends FragmentActivity  implements View.OnClickListener{

    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private String[] names = { "星期一", "星期二", "星期三", "星期四", "星期五"};
    private ScrollIndicatorView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("宝宝饮食");
        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        indicator = (ScrollIndicatorView) findViewById(R.id.moretab_indicator);
        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.rgb(0, 165, 124), 0, ScrollBar.Gravity.CENTENT_BACKGROUND));

        // 设置滚动监听
        int selectColorId = R.color.white;
        int unSelectColorId = R.color.tab_top_text_1;
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColorId(this, selectColorId, unSelectColorId));

        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        inflate = LayoutInflater.from(getApplicationContext());
        indicatorViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        indicator.setSplitAuto(true);
    }

    @Override
    public void onClick(View v) {
        final GlobalWeekDialog delDialog = new GlobalWeekDialog(this);
        delDialog.setCanceledOnTouchOutside(true);
        delDialog.getTitle().setText("选择日期");
        delDialog.setLeftBtnText("取消");
        delDialog.setRightBtnText("确定");
        delDialog.setContent();
        delDialog.setLeftOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delDialog.dismiss();
            }
        });
        delDialog.setRightOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delDialog.dismiss();
            }
        });
        delDialog.show();
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
            FoodFragment fragment = new FoodFragment();
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