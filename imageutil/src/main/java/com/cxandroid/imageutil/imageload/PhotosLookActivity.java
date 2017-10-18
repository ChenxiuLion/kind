package com.cxandroid.imageutil.imageload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cxandroid.imageutil.R;
import com.cxandroid.imageutil.imageloader.util.ImageDown;
import com.cxandroid.imageutil.title.KFTitleView;
import com.cxandroid.imageutil.title.OnTitleOncilkEvent;

import java.util.ArrayList;

/**
 * Created by Chenxiu on 2016/8/30.
 */
public class PhotosLookActivity extends FragmentActivity {

    private ViewPager mViewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private BhPagerAdapter mAdaptger;

    private KFTitleView mTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_activity);
        mViewPager = (ViewPager) findViewById(R.id.photo_viewpager);
        mTitle = (KFTitleView) findViewById(R.id.title);
        for(String path : ImageAdapter.mImages){
            mFragments.add(ImageFragment.newFragment(path));
        }
        mTitle.setTitlte("1/"+ImageAdapter.mImages.size());
        mTitle.setTitleOncilk(new OnTitleOncilkEvent() {
            @Override
            public void onLeft() {
                onBackPressed();
            }

            @Override
            public void onRight() {

            }
        });
        mAdaptger = new BhPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdaptger);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTitle.setTitlte((position+1)+"/"+ImageAdapter.mImages.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



}
