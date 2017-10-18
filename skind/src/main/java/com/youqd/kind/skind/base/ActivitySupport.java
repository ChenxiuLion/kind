/**
 * @Title：ActivitySupport.java
 * @Description：为项目Activity类提供通用支撑
 * @author wangyn
 * @since 2014-2-24 下午2:17:41
 */
package com.youqd.kind.skind.base;

import android.content.Context;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.youqd.kind.skind.model.LoginConfig;
import com.youqd.kind.skind.util.Constants;
import com.youqd.kind.skind.util.SharePreferenceUtil;

import java.util.ArrayList;

public class ActivitySupport extends FragmentActivity implements IActivitySupport {

	public static ArrayList<BackPressHandler> mListeners = new ArrayList<BackPressHandler>();
	private static final String TAG = "ActivitySupport";
	protected Context context = null;
	protected SharedPreferences preferences;
	protected BaseApplication app;
	/** 用于和Service通信  **/
	private ServiceConnection mServiceConnection = null;
	protected SharePreferenceUtil spu = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		preferences = getSharedPreferences(Constants.SHARED_PREFERENCES_ID, Context.MODE_PRIVATE);
		app = (BaseApplication) this.getApplication();
		spu = new SharePreferenceUtil(context);
	}

	/**
	 * 解除服务
	 */
	public void unbindXMPPService() {
		try {
			unbindService(mServiceConnection);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	/**
	 * 调用activityOnResume
	 */
	protected void activityOnResume() {
		for (BackPressHandler handler : mListeners) {
			handler.activityOnResume();
		}
	}

	/**
	 * 调用activityOnPause
	 */
	protected void activityOnPause() {
		if (mListeners.size() > 0){
			for (BackPressHandler handler : mListeners) {
				handler.activityOnPause();
			}
		}
	}


	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public BaseApplication getKoalApplication() {
		// TODO Auto-generated method stub
		return app;
	}


	@Override
	public boolean hasInternetConnected() {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager != null) {
			NetworkInfo network = manager.getActiveNetworkInfo();
			if (network != null && network.isConnectedOrConnecting()) {
				return true;
			}
		}
		return false;
	}

	public void showToast(int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}

	public void showLongToast(int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
	}


	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public SharedPreferences getLoginUserSharedPre() {
		return preferences;
	}

	@Override
	public void saveLoginConfig(LoginConfig loginConfig) {
		spu.saveLoginConfig(loginConfig);
	}

	@Override
	public LoginConfig getLoginConfig() {
		return spu.getLoginConfig();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.koal.sim.base.IActivitySupport#validateInternet()
	 */
	@Override
	public boolean validateInternet() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.koal.sim.base.IActivitySupport#isExit()
	 */
	@Override
	public void isExit() {
		// TODO Auto-generated method stub

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.koal.sim.base.IActivitySupport#hasLocationGPS()
	 */
	@Override
	public boolean hasLocationGPS() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.koal.sim.base.IActivitySupport#hasLocationNetWork()
	 */
	@Override
	public boolean hasLocationNetWork() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.koal.sim.base.IActivitySupport#checkMemoryCard()
	 */
	@Override
	public void checkMemoryCard() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.koal.sim.base.IActivitySupport#showToast(java.lang.String, int)
	 */
	@Override
	public void showToast(String text, int longint) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.koal.sim.base.IActivitySupport#showToast(java.lang.String)
	 */
	@Override
	public void showToast(String text) {
		// TODO Auto-generated method stub

	}

	/**
	 * Activity调用IMService的接口，主要用于监控服务是否运行
	 * @author yqd
	 *
	 */
	public static abstract interface BackPressHandler {
		public abstract void activityOnResume();
		public abstract void activityOnPause();
	}
}
