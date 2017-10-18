package com.cxandroid.imageutil.imageload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cxandroid.imageutil.R;
import com.cxandroid.imageutil.imageloader.util.ImageDown;

/**
 * Created by Chenxiu on 2016/8/30.
 */
public class ImageFragment extends BaseFragment {

    private ImageView mImageView;

    public static ImageFragment newFragment(String path){
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("path",path);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }


    @Override
    protected int initView() {
        return R.layout.fragment_imagess;
    }

    @Override
    protected void initData() {
        mImageView = (ImageView) mView.findViewById(R.id.fragment_imsage);
        String path = getArguments().getString("path");

        ImageDown.getInstance(3, ImageDown.Type.LIFO).
                loadImage(path,mImageView);
    }

}
