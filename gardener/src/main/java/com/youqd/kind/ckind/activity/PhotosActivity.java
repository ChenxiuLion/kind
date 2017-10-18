package com.youqd.kind.ckind.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.adapter.BhPagerAdapter;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.fragment.ImageFragment;
import com.youqd.kind.ckind.model.KingVideos;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Chenxiu on 2016/8/3.
 */
public class PhotosActivity extends KingActivity {

    private ViewPager mViewPager;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private ArrayList<KingVideos> mImagePaths ;
    @Override
    protected int initLayout() {
        return R.layout.activity_photos;
    }

    private int index;

    @Override
    protected void onNewIntent(Intent intent) {
        initViews();
        super.onNewIntent(intent);
    }

    @Override
    protected void initViews() {
        mFragments.clear();
        mViewPager = (ViewPager) findViewById(R.id.photos_viewpager);
        mImagePaths = getIntent().getParcelableArrayListExtra("images");

        if(mImagePaths == null){
            mImagePaths = new ArrayList<>();
            String[] da = getIntent().getStringArrayExtra("images");
            for(String i : da){
                mImagePaths.add(new KingVideos("",i));
            }
        }
        index = getIntent().getIntExtra("postion",0);

        ((TextView)findViewById(R.id.top_title)).setText((index+1)+"/"+mImagePaths.size());
        findViewById(R.id.rightId).setVisibility(View.GONE);
        for(KingVideos path : mImagePaths){
            if(path.isVideo()) {
                mFragments.add(ImageFragment.getThis(path.getVideoPath()));
            }else{
                mFragments.add(ImageFragment.getThis(path.getThumbnail()));
            }
        }
        BhPagerAdapter mAdapter = new BhPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(index);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((TextView)findViewById(R.id.top_title)).setText((position+1)+"/"+mImagePaths.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onPause() {
        JCVideoPlayer.releaseAllVideos();
        super.onPause();
    }
}
