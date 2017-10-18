package com.youqd.kind.ckind.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.adapter.BhPagerAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.SeeResult;
import com.youqd.kind.ckind.fragment.SeeFragment;
import com.youqd.kind.ckind.util.HttpTool;
import com.youqd.kind.ckind.util.KingCallback;

import java.util.ArrayList;
import java.util.List;

public class SeeActivity extends KingActivity {


    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private ViewPager mViewpage;

    private LinearLayout mPageLayout;

    private List<View> mPageViews = new ArrayList<>();

    @Override
    protected int initLayout() {
        return R.layout.activity_see_page;
    }


    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("看宝宝");
        mViewpage = (ViewPager) findViewById(R.id.see_view_pager);
        mPageLayout  = (LinearLayout) findViewById(R.id.page_lin);

        HttpTool.getInstance().doSeeList(getBaby().getClassID()+"",getBaby().getKindergartenID()+"",
                new KingCallback<SeeResult>(SeeResult.class) {
                    @Override
                    public void onSucceed(SeeResult data) {
                        if(data.getResultData().size()==0){
                            showToast("幼儿园暂未开通看宝宝远程直播系统");
                        }
                        for(SeeResult.ResultDataBean bean : data.getResultData()) {
                            addPage("我们的"+bean.getLocation(), bean.getVideoLink());
                        }

                        mViewpage.setOffscreenPageLimit(3);
                        mViewpage.setAdapter(new BhPagerAdapter(getSupportFragmentManager(),mFragments));
                        mViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                initPageViews(position);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                        initPageViews(0);
                    }

                    @Override
                    public void onErro() {

                    }
                });

    }

    public void initPageViews(int position){
        for(View view : mPageViews){
            view.setBackgroundResource(R.drawable.shape_yuan);
        }
        mPageViews.get(position).setBackgroundResource(R.drawable.shape_yuan_lv);
    }

    public void addPage(String title,String url){
        //mFragments.add(SeeFragment.newInstant(title,url));
        TextView view = new TextView(mContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20,20);
        layoutParams.setMargins(0,0,10,0);
        view.setLayoutParams(layoutParams);
        mPageLayout.addView(view);
        mPageViews.add(view);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {
    }
}
