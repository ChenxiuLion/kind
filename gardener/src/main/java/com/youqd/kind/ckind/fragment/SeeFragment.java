package com.youqd.kind.ckind.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosfund.library.dialog.HintDialog;
import com.cosfund.library.util.LogUtils;
import com.cosfund.library.util.NetWorkUtils;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoView;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.activity.MainActivity;
import com.youqd.kind.ckind.activity.MaxVideoActivity;
import com.youqd.kind.ckind.video.MediaController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/7/29.
 */
public class SeeFragment extends KingFragment{

    public static final String EAXE_URL = "url";
    public static final String EAXE_TITLE = "title";

    private MediaController mMediaController;

    private PLVideoView mSurfaceView;
    private String mVideoPath;

    public ImageView mMaxImage ;

    private TextView mErroTv;

    private boolean mIsPlay = true;

    public static SeeFragment newInstant(long time,String path){
        SeeFragment f = new SeeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EAXE_URL,path);
        bundle.putLong(EAXE_TITLE,time);
        f.setArguments(bundle);
        return f;
    }
    HintDialog dialog;

    @Override
    public int initLayout() {
        return R.layout.activity_see;
    }

    @Override
    public void initData() {

        mSurfaceView = (PLVideoView) findViewById(R.id.video_v);
        mMaxImage = (ImageView) findViewById(R.id.max_btn);

        mVideoPath = getArguments().getString(EAXE_URL);
        long titme = getArguments().getLong(EAXE_TITLE);
        mErroTv = (TextView) findViewById(R.id.video_erro_tv) ;

        if(titme<System.currentTimeMillis()){
            mIsPlay = false;
            mErroTv.setVisibility(View.VISIBLE);
            findViewById(R.id.play_btn_view).setVisibility(View.GONE);
        }else{
          //
        }

        findViewById(R.id.video_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NetWorkUtils.isConnected(getActivity())) {
                    if (NetWorkUtils.isWifi(getActivity())) {
                        play();
                    } else {

                        dialog = new HintDialog(getActivity());
                        dialog.setMessage("家园宝宝提醒您：您正在使用非WiFi网络，播放将产生流量费用，请您确定是否继续播放");
                        dialog.setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                play();
                                dialog.dismiss();
                            }
                        });
                        dialog.setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.setTitle("");
                        dialog.show();
                    }
                }else{
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("无网络")
                            .setContentText("请检查网络!")
                            .show();
                }

            }
        });



        findViewById(R.id.stop_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSurfaceView.isPlaying()) {
                    mSurfaceView.pause();
                    ((TextView)findViewById(R.id.video_stuas)).setText("播放");
                    findViewById(R.id.video_play).setVisibility(View.VISIBLE);
                    ((ImageView) findViewById(R.id.stop_btn)).setImageResource(R.drawable.icon_defult_sm_play);
                }else{
                    play();

                }
            }
        });
        mMaxImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSurfaceView.pause();
                findViewById(R.id.video_play).setVisibility(View.VISIBLE);
                Bundle bundle = new Bundle();
                bundle.putString("path",mVideoPath);
                startActivity(MaxVideoActivity.class,bundle);
            }
        });

        findViewById(R.id.zzplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsPlay) {
                    if(System.currentTimeMillis() - clikTime <1000){
                        if(mSurfaceView.isPlaying()) {
                            mSurfaceView.pause();
                            ((TextView) findViewById(R.id.video_stuas)).setText("播放");
                            findViewById(R.id.video_play).setVisibility(View.VISIBLE);
                            ((ImageView) findViewById(R.id.stop_btn)).setImageResource(R.drawable.icon_defult_sm_play);
                        }else{
                            play();
                        }
                    }
                    clikTime = System.currentTimeMillis();
                    if (mPlayLinTime > 0) {
                        mPlayLinTime = -1;
                        mUIThread = null;
                        findViewById(R.id.play_lin).setVisibility(View.GONE);
                    } else {
                        findViewById(R.id.play_lin).setVisibility(View.VISIBLE);
                        mUIThread = new UIThread();
                        mUIThread.start();
                    }
                }
            }
        });
    }

    private long clikTime ;
    public UIThread mUIThread ;

    public class UIThread extends Thread{
        @Override
        public void run() {
            while (mPlayLinTime<5){
                if(mPlayLinTime==-1) {
                    mPlayLinTime = 0;
                    return;
                }
                mPlayLinTime++;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(getActivity()!=null){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPlayLinTime = 0;
                        findViewById(R.id.play_lin).setVisibility(View.GONE);
                    }
                });
            }
        }
    }

    private int mPlayLinTime = 0;
    public boolean isDay  = false;

    public int isDayInt = 0;

    public void play(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(TextUtils.isEmpty(mVideoPath)) {
            mVideoPath = "http://pili-live-hls.live.itchaxun.com/jybbtestlive/test001.m3u8";
        }
        findViewById(R.id.video_play).setVisibility(View.GONE);
        findViewById(R.id.shikan_tv).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.stop_btn)).setImageResource(R.drawable.icon_defult_stop_play);
        mMediaController = new MediaController(getActivity());
        mSurfaceView.setVideoPath(mVideoPath);
        mSurfaceView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_4_3);
        mSurfaceView.setAVOptions(getAVOptions());
        mSurfaceView.requestFocus();
        mSurfaceView.start();
        ((TextView)findViewById(R.id.video_stuas)).setText("直播中");
    }

    @Override
    protected void onFragmentStopLazy() {
        super.onFragmentStopLazy();
        LogUtils.e("stop");
        mSurfaceView.pause();
        findViewById(R.id.video_play).setVisibility(View.VISIBLE);
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
