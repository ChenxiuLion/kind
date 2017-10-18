package com.youqd.kind.ckind.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.Gson;
import com.youqd.kind.ckind.base.BaseApplication;
import com.youqd.kind.ckind.bean.AllBoby;
import com.youqd.kind.ckind.bean.GetUserName;
import com.youqd.kind.ckind.bean.KindBean;
import com.youqd.kind.ckind.bean.LoginBean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Chenxiu on 2016/8/10.
 */
public class UserManage {

    private SharedPreferences mShared;


    public static UserManage getInstance(){
        return new UserManage(BaseApplication.getInstance());
    }

    public UserManage(Context context){
        mShared = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
    }

    /**
     * 获取学校信息
     * @return
     */
    public KindBean getKind(){
        String json = mShared.getString("Kind","");
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json,KindBean.class);
    }

    /**
     * 保存学校信息
     * @param bean
     */
    public void setKind(KindBean bean){
        mShared.edit().putString("Kind",new Gson().toJson(bean)).apply();
    }


    /**
     * 获取用户信息
     * @return
     */
    public LoginBean getUser(){
        String json = mShared.getString("user","");
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json,LoginBean.class);
    }

    public void setClassName(String name){
        LoginBean s = getUser();
        s.setClassName(name);
        setUser(s);
    }


    public void clearUser(){
        mShared.edit().putString("user","").apply();
    }
    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
    /**
     * 增加一个家长
     * @param patriarchId
     * @param name
     */
    public void addPatriarch(String patriarchId,String name){
        mShared.edit().putString("patriarch_"+patriarchId,name).apply();
    }

    /**
     * 获取一个家长
     * @param patriarchId 家长id
     */
    public String getPatriarchName(String patriarchId){
        return mShared.getString("patriarch_"+patriarchId,"家长");
    }

    /**
     * 增加一个家长
     * @param patriarchId 家长id
     */
    public void getPatriarchName(final String patriarchId, final TextView tv){
        String str =  mShared.getString("patriarch_"+patriarchId,"");
        if(TextUtils.isEmpty(str)){
            HttpTool.getInstance().getUserName(patriarchId, new KingCallback<GetUserName>(GetUserName.class) {
                @Override
                public void onSucceed(GetUserName data) {
                    tv.setText(data.getResultData().getRelation());
                    addPatriarch(patriarchId,data.getResultData().getRelation());
                }

                @Override
                public void onErro() {

                }
            });
        }else{
            tv.setText(str);
        }
    }

    /**
     * 保存用户信息
     * @param bean
     */
    public void setUser(LoginBean bean){
        mShared.edit().putString("user",new Gson().toJson(bean)).apply();
    }

    /**
     * 保存用户信息
     * @param bean
     */
    public void setAllBoby(AllBoby bean){
        mShared.edit().putString("AllBoby",new Gson().toJson(bean)).apply();
    }

    /**
     * 获取用户信息
     * @return
     */
    public AllBoby getAllBoby(){
        String json = mShared.getString("AllBoby","");
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json,AllBoby.class);
    }
}
