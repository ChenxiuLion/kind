package com.youqd.kind.ckind.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.adapter.BhPagerAdapter;
import com.youqd.kind.ckind.bean.SeeResult;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenxiu on 2016/7/31.
 */
public class SeeMainFragment extends KingFragment {


    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private ViewPager mViewpage;

    private LinearLayout mPageLayout;

    private List<View> mPageViews = new ArrayList<>();

    private ArrayList<String> mTitleName = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_see_page;
    }

    @Override
    public void initData() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        findViewById(R.id.base_back).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("看宝宝");
        mViewpage = (ViewPager) findViewById(R.id.see_view_pager);
        mPageLayout  = (LinearLayout) findViewById(R.id.page_lin);
/*
        HttpTool.getInstance().doSeeList(getBaby().getClassID()+"",getBaby().getKindergartenID()+"",
                new KingCallback<SeeResult>(SeeResult.class) {
                    @Override
                    public void onSucceed(SeeResult data) {
                        if(data!=null) {
                            for (SeeResult.ResultDataBean bean : data.getResultData()) {
                                mTitleName.add("我们的" + bean.getLocation());
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                                try {
                                    addPage(format.parse(bean.getVideoViewEndTime()).getTime(), bean.getVideoLink());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(mFragments.size()==0){

                            }

                            mViewpage.setOffscreenPageLimit(3);
                            mViewpage.setAdapter(new BhPagerAdapter(getChildFragmentManager(), mFragments));
                            mViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {
                                    initPageViews(position);
                                    ((TextView) findViewById(R.id.see_tv)).setText(mTitleName.get(position));
                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });
                            if(data.getResultData().size()>0) {
                                initPageViews(0);
                            }
                        }
                    }

                    @Override
                    public void onErro() {

                    }
                });*/

    }

    public void initPageViews(int position){
        for(View view : mPageViews){
            view.setBackgroundResource(R.drawable.shape_yuan);
        }
        mPageViews.get(position).setBackgroundResource(R.drawable.shape_yuan_lv);
    }

    public void addPage(long time,String url){
        if(getActivity()!=null) {
/*            Calendar calendar = Calendar.getInstance();
            int hout = calendar.get(Calendar.HOUR_OF_DAY);
            if(hout<7 || hout >18){
                time = System.currentTimeMillis()-10000;
            }*/
            mFragments.add(SeeFragment.newInstant(time, url));
            TextView view = new TextView(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            layoutParams.setMargins(0, 0, 10, 0);
            view.setLayoutParams(layoutParams);
            mPageLayout.addView(view);
            mPageViews.add(view);
        }
    }

}
