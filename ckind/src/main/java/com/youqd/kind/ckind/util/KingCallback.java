package com.youqd.kind.ckind.util;

import android.os.Handler;
import android.os.Looper;

import com.cosfund.library.util.LogUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Chenxiu on 2016/7/16.
 */
public abstract class KingCallback<T> implements Callback {

    private T mData;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Class mClass;

    public KingCallback(Class clas){
        mClass = clas;
    }
    @Override
    public void onFailure(Request request, IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onErro();
            }
        });

    }

    @Override
    public void onResponse(Response response) throws IOException {
        String str = response.body().string();
        LogUtils.json(str);
        mData = (T) new Gson().fromJson(str,mClass);
        if(mData!=null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onSucceed(mData);
                }
            });
        }else{
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onErro();
                }
            });
        }

    }

    public abstract void onSucceed(T data);

    public abstract void onErro();
}
