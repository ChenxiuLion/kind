package com.youqd.kind.ckind;

import android.content.Context;

import com.cosfund.library.util.LogUtils;
import com.dou361.update.ParseData;
import com.dou361.update.UpdateHelper;
import com.dou361.update.bean.Update;
import com.dou361.update.type.RequestType;
import com.google.gson.Gson;
import com.youqd.kind.ckind.bean.UpdateBean;
import com.youqd.kind.ckind.constant.Constant;

import java.util.TreeMap;

/**
 * Created by Chenxiu on 2016/12/16.
 */

public class UpdateConfig {
    public static void init(Context context) {
        UpdateHelper.init(context);
        TreeMap<String,Object> pasm = new TreeMap<>();
        pasm.put("AppName","jiazhang");
        UpdateHelper.getInstance()
                .setCheckJsonParser(new ParseData() {
                    @Override
                    public Update parse(String httpResponse) {
                        LogUtils.json(httpResponse);
                        UpdateBean bean = new Gson().fromJson(httpResponse,UpdateBean.class);

                        Update update = new Update();

                        /**必填：此apk包的下载地址*/
                        update.setUpdateUrl(bean.getResultData().get(0).getDownLoadUrl());
                        /**必填：此apk包的版本号*/
                        update.setVersionCode(bean.getResultData().get(0).getVersionNum());
                        /**必填：此apk包的版本名称*/
                        update.setVersionName(bean.getResultData().get(0).getVersion());
                        /**可填：此apk包的更新内容*/
                        update.setUpdateContent(bean.getResultData().get(0).getVersionDescription());
                        /**可填：此apk包是否为强制更新*/
                        update.setForce(false);
                        return update;
                    }
                });
    }
}
