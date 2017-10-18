/**
 * 项目APPlication
 *
 */
package com.youqd.kind.skind.base;

import android.app.Application;

import com.youqd.kind.skind.util.SharePreferenceUtil;

public class BaseApplication extends Application {
	private static BaseApplication app;

	/** 共享信息  **/
	private SharePreferenceUtil mSpUtil;

	/**
	 * 单例对象
	 * @return
	 */
	public synchronized static BaseApplication getInstance() {
		return app;
	}

	/**
	 * 得到配置文件工具类，做为调用配置文件的一个入口
	 * @return
	 */
	public synchronized SharePreferenceUtil getSpUtil() {
		if (mSpUtil == null)
			mSpUtil = new SharePreferenceUtil(this);
		return mSpUtil;
	}


}
