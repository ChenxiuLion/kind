package com.youqd.kind.ckind.activity;

import android.view.View;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoView;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;

/**
 * Created by Chenxiu on 2016/9/4.
 */
public class MaxVideoActivity extends KingActivity {

    public static final String EAXE_URL = "url";
    public static final String EAXE_TITLE = "title";


    private PLVideoView mSurfaceView;
    private String mVideoPath;
    @Override
    protected int initLayout() {
        return R.layout.activity_see_max;
    }

    @Override
    protected void initViews() {
        mSurfaceView = (PLVideoView) findViewById(R.id.video_v);
        mVideoPath = getIntent().getStringExtra("path");
        mSurfaceView.setVideoPath(mVideoPath);
        mSurfaceView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_4_3);
        mSurfaceView.setAVOptions(getAVOptions());
        mSurfaceView.requestFocus();
        mSurfaceView.start();

        findViewById(R.id.back_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.stop_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSurfaceView.pause();
                finish();
            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onDestroy() {
        mSurfaceView.stopPlayback();
        mSurfaceView = null;
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }

    public AVOptions getAVOptions(){
        AVOptions options = new AVOptions();

// 准备超时时间，包括创建资源、建立连接、请求码流等，单位是 ms
// 默认值是：无
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);

// 读取视频流超时时间，单位是 ms
// 默认值是：10 * 1000
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);

// 当前播放的是否为在线直播，如果是，则底层会有一些播放优化
// 默认值是：0
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);

// 是否开启"延时优化"，只在在线直播流中有效
// 默认值是：0
        options.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);

// 默认的缓存大小，单位是 ms
// 默认值是：2000
        options.setInteger(AVOptions.KEY_CACHE_BUFFER_DURATION, 2000);

// 最大的缓存大小，单位是 ms
// 默认值是：4000
        options.setInteger(AVOptions.KEY_MAX_CACHE_BUFFER_DURATION, 4000);

// 是否自动启动播放，如果设置为 1，则在调用 `prepareAsync` 或者 `setVideoPath` 之后自动启动播放，无需调用 `start()`
// 默认值是：1
        options.setInteger(AVOptions.KEY_START_ON_PREPARED, 1);

        return options;
    }

}
