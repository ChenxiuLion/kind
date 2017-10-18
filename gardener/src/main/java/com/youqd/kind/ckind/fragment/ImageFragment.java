package com.youqd.kind.ckind.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.util.HttpTool;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by Chenxiu on 2016/8/3.
 */
public class ImageFragment extends KingFragment {

    private ImageView mImageView;

    private String mPath;

    public static ImageFragment getThis(String path){

        ImageFragment fragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("path",path);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_image;
    }

    @Override
    public void initData() {
        mImageView = (ImageView) findViewById(R.id.photos_image);
        mPath = getArguments().getString("path");
        if(!mPath.contains("mp4")) {
            mImageView.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(Constant.IMAGE_URL + mPath, mImageView, getOptions());
        }else{
            final JCVideoPlayer videoController = (JCVideoPlayer) findViewById(R.id.videocontroller1);
            videoController.setVisibility(View.VISIBLE);
            videoController.isDown();
            HttpTool.getInstance().getFile(Constant.IMAGE_URL + mPath, new FileDownloadListener() {
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
                            "",
                            "");
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
    }
}
