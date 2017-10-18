/**
 * 项目APPlication
 *
 */
package com.youqd.kind.ckind.base;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.baidu.mapapi.SDKInitializer;
import com.liulishuo.filedownloader.FileDownloader;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.youqd.kind.ckind.UpdateConfig;
import com.youqd.kind.ckind.util.SharePreferenceUtil;

import cn.jpush.android.api.JPushInterface;

public class BaseApplication extends Application {
	private static BaseApplication app;

	/** 共享信息  **/
	private SharePreferenceUtil mSpUtil;

	private SharedPreferences mShared;

	private static UploadManager uploadManager;



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



	public String getToken(){
		String token = mShared.getString("token","");
		return token;
	}

	public void setToken(String token){
		mShared.edit().putString("token",token).apply();
	}
	@Override
	public void onCreate() {
		super.onCreate();
		app = this;
		mShared = getSharedPreferences("TOKEN_CIND",MODE_PRIVATE);

		FileDownloader.init(this);
		UpdateConfig.init(this);
		SDKInitializer.initialize(getApplicationContext());
		CrashReport.initCrashReport(getApplicationContext(), "7f771cfacc", false);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration
				.Builder(this)
				.memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
				.threadPoolSize(12)//线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				.discCacheSize(50 * 1024 * 1024)
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.diskCacheFileCount(100)
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.writeDebugLogs() // Remove for release app
				.build();//开始构建
		ImageLoader.getInstance().init(config);//全局初始化此配置

		Configuration Qconfig = new Configuration.Builder()
				.chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
				.putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
				.connectTimeout(10) // 链接超时。默认10秒
				.responseTimeout(60) // 服务器响应超时。默认60秒
				.build();
// 重用uploadManager。一般地，只需要创建一个uploadManager对象
		uploadManager = new UploadManager(Qconfig);
		JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
		JPushInterface.init(getApplicationContext());
	}
}
