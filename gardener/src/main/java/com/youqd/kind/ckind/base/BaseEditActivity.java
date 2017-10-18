/**
 * 基本的Activity类，记录一些常用设置
 *
 */
package com.youqd.kind.ckind.base;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.cosfund.library.util.UIManagerUtils;
import com.kind.chx.gardener.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.util.ACache;
import com.youqd.kind.ckind.util.UserManage;

import java.util.Calendar;
import java.util.TimeZone;

import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseEditActivity extends ActivitySupport implements View.OnClickListener{
	protected BaseApplication mApplication;
	/**
	 * 屏幕的宽度、高度、密度
	 */
	protected int mScreenWidth;
	protected int mScreenHeight;
	protected float mDensity;

	protected Context mContext;

	public SharedPreferences mShare ;

	public ACache mACache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mApplication = (BaseApplication) getApplication();
		DisplayMetrics metric = new DisplayMetrics();
		mShare =getSharedPreferences("KINGDS",MODE_PRIVATE);
		UIManagerUtils.getAppManager().addActivity(this);
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
		mDensity = metric.density;
		mACache = ACache.get(this);
		mContext =this;
		if(initLayout()!=0) {
			setContentView(initLayout());
		}
		initViews();
		initEvents();
	}
	float dp2Px(float dp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}

	public void showLoding(){
		showLoding("上传中",false);
	}
	public void showLoding(String title){
		showLoding(title,false);
	}

	private SweetAlertDialog pDialog;
	public void showLoding(String title,boolean flag){
		stopLoding();
		pDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
		pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
		pDialog.setTitleText(title);
		pDialog.setCancelable(flag);
		pDialog.show();
	}

	public void stopLoding(){
		if(pDialog!=null) {
			if (pDialog.isShowing()) {
				pDialog.dismiss();
				pDialog = null;
			}
		}
	}

	public void showSuccess(){
		stopLoding();
		new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
				.setTitleText("提示")
				.setContentText("成功!")
				.show();
	}
	public void showErro(){
		stopLoding();
		new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
				.setTitleText("提示")
				.setContentText("失败!")
				.show();
	}
	public SharedPreferences getSP(){
		return getSharedPreferences("King",MODE_PRIVATE);
	}
	protected abstract int initLayout();
	/** 初始化视图 **/
	protected abstract void initViews();

	/** 初始化事件 **/
	protected abstract void initEvents();

	/** 短暂显示Toast提示(来自res) **/
	protected void showShortToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
	}

	/** 短暂显示Toast提示(来自String) **/
	protected void showShortToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}


	/** 长时间显示Toast提示(来自String) **/
	protected void showLongToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}



	/** Debug输出Log日志 **/
	protected void showLogDebug(String tag, String msg) {
		Log.d(tag, msg);
	}

	/** Error输出Log日志 **/
	protected void showLogError(String tag, String msg) {
		Log.e(tag, msg);
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/** 含有标题和内容的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).show();
		return alertDialog;
	}

	/** 含有标题、内容、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
										  String positiveText,
										  DialogInterface.OnClickListener onPositiveClickListener,
										  String negativeText,
										  DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** 含有标题、内容、图标、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
										  int icon, String positiveText,
										  DialogInterface.OnClickListener onPositiveClickListener,
										  String negativeText,
										  DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).setIcon(icon)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** 默认退出 **/
	protected void defaultFinish() {
		super.finish();
	}

	/**
	 * 关闭软键盘
	 */
	public void closeSoftKeyboard(){
		View view = getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
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

	public void onBack(View v){
		onBackPressed();
	}


	public static DisplayImageOptions getOptions(){
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
				.cacheInMemory(true)//设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)
				.showImageOnLoading(R.drawable.icon_defutl_loding_icon)
				.showImageOnFail(R.drawable.icon_defutl_loding_icon)
				.showImageForEmptyUri(R.drawable.icon_defutl_loding_icon)
				.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型//
				.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
				.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
				.build();//构建完成
		return options;
	}

	public static DisplayImageOptions getOptions(int resid){
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(resid)
				.showImageOnFail(resid)
				.showImageForEmptyUri(resid)
				.cacheInMemory(true)//设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)
				.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型//
				.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
				.displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
				.build();//构建完成
		return options;
	}
}
