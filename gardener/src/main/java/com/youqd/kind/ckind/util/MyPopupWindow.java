package com.youqd.kind.ckind.util;

import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by Chenxiu on 2016/7/24.
 */
public class MyPopupWindow extends PopupWindow {


    private OnDismiss mOnDismiss;

    public MyPopupWindow(View contentView, int matchParent, int wrapContent, boolean b) {
        super(contentView,matchParent,wrapContent,b);
    }

    public void setmOnDismiss(OnDismiss mOnDismiss) {
        this.mOnDismiss = mOnDismiss;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(mOnDismiss!=null){
            mOnDismiss.onDissmiss();
        }
    }

    public interface OnDismiss{
        void onDissmiss();
    }
}
