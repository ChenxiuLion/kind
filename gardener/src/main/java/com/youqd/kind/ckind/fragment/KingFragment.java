package com.youqd.kind.ckind.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.shizhefei.fragment.LazyFragment;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.util.ACache;
import com.youqd.kind.ckind.util.UserManage;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Chenxiu on 2016/7/31.
 */
public abstract class KingFragment extends LazyFragment {

    public ACache mACache;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(initLayout());
        mACache = ACache.get(getActivity());
        initData();
    }

    public abstract int initLayout();

    public abstract void initData();
    public KingActivity getMain(){
        return (KingActivity) getActivity();
    }

    /**
     * 获取用户信息
     * @return
     */
    public LoginBean getUser(){
      return UserManage.getInstance().getUser();
    }


    /**
     * 保存用户信息
     * @param bean
     */
    public void setUser(LoginBean bean){
        UserManage.getInstance().setUser(bean);
    }

    public  String StringData(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mWay = String.valueOf(c.get(Calendar.WEEK_OF_MONTH));
        if("0".equals(mWay)){
            mWay ="一";
        }else if("1".equals(mWay)){
            mWay ="二";
        }else if("2".equals(mWay)){
            mWay ="三";
        }else if("3".equals(mWay)){
            mWay ="四";
        }else if("4".equals(mWay)){
            mWay ="五";
        }

        return mYear + "年" + mMonth + "月  "+"第"+mWay+"周";
    }
    public  String StringData(Calendar c){
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mWay = String.valueOf(c.get(Calendar.WEEK_OF_MONTH));
        if("0".equals(mWay)){
            mWay ="一";
        }else if("1".equals(mWay)){
            mWay ="二";
        }else if("2".equals(mWay)){
            mWay ="三";
        }else if("3".equals(mWay)){
            mWay ="四";
        }else if("4".equals(mWay)){
            mWay ="五";
        }

        return mYear + "年" + mMonth + "月  "+"第"+mWay+"周";
    }



    public static DisplayImageOptions getOptions(){
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在内存中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型//
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();//构建完成
        return options;
    }
    public static DisplayImageOptions getOptions(int res){
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(res)
                .showImageOnFail(res)
                .showImageForEmptyUri(res)
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在内存中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型//
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();//构建完成
        return options;
    }
    /** 通过Class跳转界面 **/
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /** 含有Bundle通过Class跳转界面 **/
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


}
