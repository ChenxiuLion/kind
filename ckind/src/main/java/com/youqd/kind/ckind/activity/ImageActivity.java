package com.youqd.kind.ckind.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.constant.Constant;

import tm.model.ImageInfo;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Chenxiu on 2016/7/25.
 */
public class ImageActivity extends KingActivity {

    private ImageView mImage;

    @Override
    protected int initLayout() {
        return R.layout.activity_image;
    }

    @Override
    protected void initViews() {
        mImage = (ImageView) findViewById(R.id.image);
        String path = getIntent().getStringExtra("image");
        ImageLoader.getInstance().displayImage(path, mImage, getOptions());
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
