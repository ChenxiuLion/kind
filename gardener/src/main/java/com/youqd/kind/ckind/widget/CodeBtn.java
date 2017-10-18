package com.youqd.kind.ckind.widget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by chenxiu on 2017/8/13.
 */

public class CodeBtn extends android.support.v7.widget.AppCompatButton {

    private int time =0;

    private Activity mActivity;


    public CodeBtn(Context context) {
        super(context);
    }

    public CodeBtn(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CodeBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




    public void start(Activity activity){
        mActivity =activity;
        time = 60;
        mNameStr = getText().toString();
        new TimeThread().start();
        setEnabled(false);
    }

    public void stop(){
        setEnabled(true);
        setText(mNameStr);
        mNameStr = null;
        time = 0;
    }

    private String mNameStr;

    public class TimeThread extends Thread{
        @Override
        public void run() {
            while (time > 0){
                try {
                    sleep(1000);
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setText(time+"");
                        }
                    });
                    time --;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!TextUtils.isEmpty(mNameStr)){
                        time = 0;
                        setEnabled(true);
                        setText(mNameStr);
                    }
                }
            });

        }
    }


}
