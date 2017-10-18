package com.youqd.kind.ckind.activity;

import android.view.View;

import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.fragment.DynamicFragment;

/**
 * Created by Chenxiu on 2016/8/20.
 */
public class DynamicActivity extends KingActivity {


    DynamicFragment mFragment;

    @Override
    protected int initLayout() {
        return R.layout.activity_dynamic;
    }

    @Override
    protected void initViews() {
        mFragment = new DynamicFragment();
        mFragment.setType(2);

        getSupportFragmentManager().beginTransaction().replace(R.id.dynamic_content,mFragment).commit();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
