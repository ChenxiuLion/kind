package com.youqd.kind.ckind.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cosfund.library.util.UIManagerUtils;
import com.example.bajian.sheetdialogue.SheetDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.R;
import com.youqd.kind.ckind.base.KingActivity;
import com.youqd.kind.ckind.util.DataCleanManager;
import com.youqd.kind.ckind.util.UserManage;

public class MyActivity extends KingActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_my;
    }

    @Override
    protected void initViews() {
        findViewById(R.id.rightId).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.top_title)).setText("设置");
        findViewById(R.id.clear_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManage.getInstance().clearUser();
                UIManagerUtils.getAppManager().AppExit(mContext);
            }
        });
        findViewById(R.id.pld_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(OldPasswordActivity.class);
            }
        });

        findViewById(R.id.feed_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FeedbackActivity.class);
            }
        });
        findViewById(R.id.abuot_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AbuotActivity.class);
            }
        });
        try {
            ((TextView)findViewById(R.id.hc_tv)).setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.clear_cache).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SheetDialog(mContext)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItems(new String[]{"清空缓存"}
                                , SheetDialog.SheetItemColor.Blue, new SheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        try {
                                            DataCleanManager.clearAllCache(mContext);
                                            ImageLoader.getInstance().clearDiskCache();
                                            ImageLoader.getInstance().clearMemoryCache();
                                            ((TextView)findViewById(R.id.hc_tv))
                                                    .setText(DataCleanManager.getTotalCacheSize(mContext));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).show();
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
