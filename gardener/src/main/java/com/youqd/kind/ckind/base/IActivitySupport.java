/**
 * @Title：IActivitySupport.java
 * @Description：定义ActivitySupport的接口，汇集通用的功能
 * @author wangyn
 * @since 2014-2-24 下午2:14:42
 */
package com.youqd.kind.ckind.base;

import android.content.Context;
import android.content.SharedPreferences;

import com.youqd.kind.ckind.model.LoginConfig;

public interface IActivitySupport {
	// 获取应用管理对象
	public abstract BaseApplication getKoalApplication();

	// 校验网络-如果没有网络就弹出设置,并返回true
	public abstract boolean validateInternet();

	// 校验网络-如果没有网络就返回true.
	public abstract boolean hasInternetConnected();

	// 退出应用
	public abstract void isExit();

	// 判断GPS是否已经开启
	public abstract boolean hasLocationGPS();

	// 判断基站是否已经开启
	public abstract boolean hasLocationNetWork();

	// 检查内存卡
	public abstract void checkMemoryCard();

	// 显示toast
	public abstract void showToast(String text, int longint);

	// 短时间显示toast
	public abstract void showToast(String text);

	// 返回当前Activity上下文
	public abstract Context getContext();

	// 获取当前登录用户的SharedPreferences配置
	public SharedPreferences getLoginUserSharedPre();

	// 保存用户配置
	public void saveLoginConfig(LoginConfig loginConfig);

	// 获取用户配置
	public LoginConfig getLoginConfig();

}
