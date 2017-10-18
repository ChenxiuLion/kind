package com.youqd.kind.ckind.activity;

import android.util.Log;
import android.view.View;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Chenxiu on 2016/8/21.
 */
public class ViewPlayActivity extends KingActivity {
    @Override
    protected int initLayout() {
        return R.layout.activity_playvideo;
    }
    private String path;
    private String image;
    @Override
    protected void initViews() {

        path = getIntent().getStringExtra("path");
        image = getIntent().getStringExtra("image");
        final JCVideoPlayer videoController = (JCVideoPlayer) findViewById(R.id.videocontroller1);
        videoController.isDown();
        videoController.setSThumb(image);
        videoController.setmOnVideoLiser(new JCVideoPlayer.OnVideoLiser() {
            @Override
            public void onStart() {

            }

            @Override
            public void onEnd() {
                finish();
            }
        });


        findViewById(R.id.root_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        HttpTool.getInstance().getFile(Constant.IMAGE_URL + path, new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                int pro = (int)((float)soFarBytes/totalBytes *100);
                Log.e("pre","百分比 = "+pro);
                videoController.setProgressTv(pro);
            }

            @Override
            protected void blockComplete(BaseDownloadTask task) {

            }

            @Override
            protected void completed(BaseDownloadTask task) {
                videoController.isDownEnd();
                videoController.setUp("file://"+task.getPath(),
                        image,
                        "");
                videoController.play();
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {

            }

            @Override
            protected void warn(BaseDownloadTask task) {

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
