package com.youqd.kind.ckind.activity;

import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.base.KingActivity;

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
