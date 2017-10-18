package com.youqd.kind.ckind.fragment;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aaronhan.rtspclient.RtspClient;
import com.cosfund.library.dialog.HintDialog;
import com.cosfund.library.util.LogUtils;
import com.cosfund.library.util.NetWorkUtils;
import com.cosfund.library.widget.HintToastUtils;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;
import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.activity.MainActivity;
import com.youqd.kind.ckind.activity.MaxVideoActivity;
import com.youqd.kind.ckind.util.UserManage;
import com.youqd.kind.ckind.video.MediaController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Chenxiu on 2016/7/29.
 */
public class SeeFragment extends KingFragment{

    public static final String EAXE_URL = "url";
    public static final String EAXE_TITLE = "title";
    public static final String EAXE_STA = "EAXE_STA";
    private MediaController mMediaController;

    private PLVideoView mSurfaceView;
    private String mVideoPath;

    public ImageView mMaxImage ;

    private TextView mErroTv;

    private ImageView mThImage;

    private ProgressBar mProgress;

    private ImageView mLodingImage;

    private boolean mIsPlay = true;

    public static SeeFragment newInstant(long time,String path){
        SeeFragment f = new SeeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EAXE_URL,path);
        bundle.putLong(EAXE_TITLE,time);
        f.setArguments(bundle);
        return f;
    }


    public static SeeFragment newInstant(long time,long statime,String path){
        SeeFragment f = new SeeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EAXE_URL,path);
        bundle.putLong(EAXE_TITLE,time);
        bundle.putLong(EAXE_STA,statime);
        f.setArguments(bundle);
        return f;
    }
    HintDialog dialog;


    private TextView mErroTv2;

    @Override
    public int initLayout() {
        return R.layout.activity_see;
    }

    @Override
    public void initData() {

        mSurfaceView = (PLVideoView) findViewById(R.id.video_v);
        mMaxImage = (ImageView) findViewById(R.id.max_btn);
        mThImage = (ImageView) findViewById(R.id.th_image);
        mVideoPath = getArguments().getString(EAXE_URL);
        mLodingImage = (ImageView) findViewById(R.id.loding_icon) ;
        mProgress = (ProgressBar) findViewById(R.id.play_ProgressBar);
        ImageLoader.getInstance().displayImage(mVideoPath.replace("hls","snapshot").replace("m3u8","jpg"),mThImage);
        long titme = getArguments().getLong(EAXE_TITLE);
        long staTitme = getArguments().getLong(EAXE_STA);
        mErroTv = (TextView) findViewById(R.id.video_erro_tv) ;
        mErroTv2 = (TextView) findViewById(R.id.video_erro_tv2) ;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat kaiFormat = new SimpleDateFormat("HH:mm");
        try {
            long endTime = format.parse(UserManage.getInstance().getUser().getVideoEndTime()).getTime();
            if (endTime < System.currentTimeMillis()) {
                findViewById(R.id.shikan_tv).setVisibility(View.VISIBLE);
            } else {

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(titme<System.currentTimeMillis() || System.currentTimeMillis()<staTitme){
            mIsPlay = false;
            mErroTv.setVisibility(View.VISIBLE);
            mErroTv2.setVisibility(View.VISIBLE);
            mErroTv2.setText("开放时间段：("+kaiFormat.format(new Date(staTitme))+" - "+kaiFormat.format(new Date(titme))+")");
            mErroTv.setText("本时段直播未开放");
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
                        dialog.setTitle("温馨提示");
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
                    if(MainActivity.mActivity!=null){
                        MainActivity.mActivity.onrelease();
                    }
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
                        mThImage.setVisibility(View.GONE);
                        if(MainActivity.mActivity!=null){
                            MainActivity.mActivity.acqueire();
                        }
                    } else {
                        findViewById(R.id.play_lin).setVisibility(View.VISIBLE);
                       // mThImage.setVisibility(View.VISIBLE);
                        mUIThread = new UIThread();
                        mProgress.setVisibility(View.GONE);
                        mUIThread.start();
                        if(MainActivity.mActivity!=null){
                            MainActivity.mActivity.onrelease();
                        }

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

        try {
            long endTime = format.parse(getUser().getVideoEndTime().replace(" ", " ")).getTime();
            if (endTime < System.currentTimeMillis()) {
                MainActivity.mActivity.mShare.edit().putString("1"+format.format(new Date()).split(" ")[0],"1").commit();
                isDay = true;
                new Thread(){
                    @Override
                    public void run() {
                        while (isDay){
                            try {
                                Thread.sleep(1000);
                                isDayInt ++;
                                if(isDayInt == 60){
                                    isDay = false;
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(getActivity()!=null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    MainActivity.mActivity.goPostion(0);
                                    MainActivity.mActivity.seeEnd();
                                }
                            });
                        }
                        super.run();
                    }
                }.start();
            } else {
                if(getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mMaxImage.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(TextUtils.isEmpty(mVideoPath)) {
            mVideoPath = "http://pili-live-hls.live.itchaxun.com/jybbtestlive/test001.m3u8";
        }
        findViewById(R.id.video_play).setVisibility(View.GONE);
        findViewById(R.id.shikan_tv).setVisibility(View.GONE);
        mThImage.setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.stop_btn)).setImageResource(R.drawable.icon_defult_stop_play);
        mMediaController = new MediaController(getActivity());
        mSurfaceView.setVideoPath(mVideoPath);
        mSurfaceView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_4_3);
        mSurfaceView.setAVOptions(getAVOptions());
        mSurfaceView.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
                LogUtils.e("erro = "+i);
                mSurfaceView.pause();

                if(i == -875574520 || i == -1){
                    mErroCount++;
                    if(mErroCount<=3){
                        Toast.makeText(getActivity(),"尝试重连中..",Toast.LENGTH_SHORT).show();
                        mSurfaceView.setVideoPath(mVideoPath);
                        mSurfaceView.start();
                    }else{
                        Toast.makeText(getActivity(),"直播暂未开放，请稍后再试！",Toast.LENGTH_SHORT).show();
                    }
                }
                if(i==-2002){
                    mSurfaceView.setVideoPath(mVideoPath);
                    mSurfaceView.start();
                }else {
                    findViewById(R.id.video_play).setVisibility(View.VISIBLE);
                    mThImage.setVisibility(View.VISIBLE);
                    mProgress.setVisibility(View.GONE);
                }
                return false;
            }
        });
        mProgress.setVisibility(View.VISIBLE);
        mSurfaceView.setOnInfoListener(new PLMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
                if(i == 701){
                    mProgress.setVisibility(View.VISIBLE);
                }else{
                    if(mSweetAlertDialog!=null){
                        if(mSweetAlertDialog.isShowing()){
                            mSweetAlertDialog.dismiss();
                            if(MainActivity.mActivity!=null){
                                MainActivity.mActivity.onrelease();
                            }
                        }
                    }
                    mProgress.setVisibility(View.GONE);
                    mThImage.setVisibility(View.GONE);
                    if(MainActivity.mActivity!=null){
                        MainActivity.mActivity.acqueire();
                    }
                }

                return false;
            }
        });
        mSurfaceView.requestFocus();
        mSurfaceView.start();
        if(MainActivity.mActivity!=null){
            MainActivity.mActivity.acqueire();
        }
        ((TextView)findViewById(R.id.video_stuas)).setText("直播中");
    }
    SweetAlertDialog mSweetAlertDialog;
    private int mErroCount = 0;

    @Override
    protected void onFragmentStopLazy() {
        super.onFragmentStopLazy();
        mSurfaceView.pause();
        findViewById(R.id.video_play).setVisibility(View.VISIBLE);
     //   mThImage.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.GONE);
    }

    public AVOptions getAVOptions(){
        AVOptions options = new AVOptions();
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
        options.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
        options.setInteger(AVOptions.KEY_CACHE_BUFFER_DURATION, 2000);
        options.setInteger(AVOptions.KEY_MAX_CACHE_BUFFER_DURATION, 4000);
        options.setInteger(AVOptions.KEY_START_ON_PREPARED, 1);
        return options;
    }

}
