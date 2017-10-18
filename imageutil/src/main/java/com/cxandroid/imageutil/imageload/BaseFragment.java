package com.cxandroid.imageutil.imageload;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;



public abstract class BaseFragment extends Fragment {

    public View mView;


    public boolean isVisible = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(initView(), container, false);
        } else {
            ViewGroup group = (ViewGroup) mView.getParent();
            if (group != null) {
                group.removeView(mView);
            }
        }
        return mView;
    }


    /**
     * 设置fragment布局
     *
     * @return
     */
    protected abstract int initView();

    /**
     * 初始化数据，所有初始化数据的方法写在这里面
     *
     * @return
     */
    protected abstract void initData();


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(getUserVisibleHint()) {
            isVisible = true;
        } else {
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        setUserVisibleHint(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();

        super.onViewCreated(view, savedInstanceState);
    }



    /**
     * 快速 Intent
     *
     * @param cla 目标class
     */
    public void goIntent(Class cla) {
        Intent intent = new Intent(getActivity(), cla);
        startActivity(intent);
    }

    /**
     * 快速 Intent
     *
     * @param cla    目标class
     * @param bundle 传递参数
     */
    public void goIntent(Class cla, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cla);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 快速 Intent
     *
     * @param cla 目标class
     */
    public void goIntent(Class cla, String title) {
        Intent intent = new Intent(getActivity(), cla);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    /**
     * 提示
     */
    protected void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        //	new HintViewUtils(getActivity(),msg,null,null);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
