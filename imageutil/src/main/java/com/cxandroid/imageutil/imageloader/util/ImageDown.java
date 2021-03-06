package com.cxandroid.imageutil.imageloader.util;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.text.Html.ImageGetter;
import android.text.style.MaskFilterSpan;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

/**
 * 图片加载
 * @author frankfeng
 *
 */
public class ImageDown 
{
	private static ImageDown mInstance;
	/**
	 * 图片缓存的核心对象
	 */
	private LruCache<String, Bitmap> mLruCache;
	/**
	 * 线程池
	 */
	private ExecutorService mThreadPool;
	private static final int DEAFULT_THREAD_COUNT=1;
	/**
	 * 队列的调度方式
	 */
	private Type mType=Type.LIFO;
	/**
	 * 任务队列
	 */
	private LinkedList<Runnable> mTaskQueue;
	/**
	 * 后台轮询线程
	 */
	private Thread mPoolThread;
	private Handler mPoolThreadHandler;
	/**
	 * UI线程中的Handler
	 * @author frankfeng
	 *
	 */
	private Handler mUIHandler;
	
	private Semaphore mSemaphorePoolThreadHandler=new Semaphore(0);
	
	private Semaphore mSemaphoreThreadPool;
	public enum Type{
		FIFO,LIFO;
	}
	private ImageDown(int threadCount,Type type){
		init(threadCount,type);
		}
	
	public static ImageDown getInstance(int threadCount, Type type){
		
		if(mInstance==null)
		{
			synchronized (ImageDown.class) 
			{	
			if(mInstance==null){
				mInstance=new ImageDown(DEAFULT_THREAD_COUNT,Type.LIFO);
			}
			}
		}
		return mInstance;
	}	
/**
 * 根据path为imageview 设置图片
 * @param path
 * @param imageView
 */
public void loadImage(final String path,final ImageView imageView)
{
	imageView.setTag(path);
	if(mUIHandler==null){
		mUIHandler=new Handler()
		{
		 public void handleMessage(Message msg) {
			//h获取得到图片， 为imageview回调设置图片
			 ImageBeanHolder holder=(ImageBeanHolder) msg.obj;
			 Bitmap bm=holder.bitmap;
			 ImageView imageview=holder.iamgeView;
			 String path=holder.path;
			 
			 //将path与getTag存储路径进行比较
			 if(imageview.getTag().toString().equals(path))
			 {
				imageview.setImageBitmap(bm); 
			 }
		 };	
			
		};
	}
	Bitmap bm=getBitmapFromLruCache(path);
	if(bm!=null){
		
		refreashBitmap(path, imageView, bm);
		
	}else{
		addTask(new Runnable() {
			
			@Override
			public void run() {
			
			//加载图片
			//图片的压缩
			//1.获得图片需要显示的大小
		ImageSize imagesize= getImagetViewSize(imageView);
		//2.压缩图片
		Bitmap bm=decodeSampledBitmapFormPath(path,imagesize.width,imagesize.heigh);
		//3.把图片加入到缓存
		addBitmapToLruCache(path,bm);
		
		refreashBitmap(path, imageView, bm);
		
		mSemaphoreThreadPool.release();
			}
		});
	}
	}	

	private void refreashBitmap(final String path,final ImageView imageView,Bitmap bm)
	{
		Message message=Message.obtain();
		ImageBeanHolder holder=new ImageBeanHolder();
		holder.bitmap=bm;
		holder.path=path;
		holder.iamgeView=imageView;
		message.obj=holder;
		mUIHandler.sendMessage(message);
	}
/**
 * 将图片加入到LruCaChe
 * @param path
 * @param bm
 */
	protected void addBitmapToLruCache(String path, Bitmap bm) 
	{
	
	if(getBitmapFromLruCache(path)==null)
	{
		if(bm!=null){
			mLruCache.put(path, bm);
		}
	}
	}

	/**
	 * 根据图片需要展示的宽和高对图片进行压缩
	 * @param path
	 * @param width
	 * @param heigh
	 * @return
	 */
	protected Bitmap decodeSampledBitmapFormPath(String path, int width, int heigh) 
	{
		Options options=new Options();
		options.inJustDecodeBounds=true;
		BitmapFactory.decodeFile(path,options);
		
		options.inSampleSize=caculateInSampleSize(options,width,heigh);
		//使用获取到的InsampleSize再次解析图片
		options.inJustDecodeBounds=false;
		Bitmap bitmap=BitmapFactory.decodeFile(path,options);
		
		return bitmap;
	}
	/**
	 * 
	 * 根据需求的宽和高以及图片实际的宽和高计算SampleSize
	 * @param options
	 * @param width
	 * @param heigh
	 * @return
	 */
	private int caculateInSampleSize(Options options, int reqWidth, int reqHeight)
	{
		int width=options.outWidth;
		int height=options.outHeight;
		int inSampleSize=1;
		if(width>reqWidth||height>reqHeight)
		{
			int widthRadio=Math.round(width*1.0f/reqWidth);
			int heightRadio=Math.round(height*1.0f/reqHeight);
			
			inSampleSize=Math.max(widthRadio,heightRadio);
		}
		
		return inSampleSize;
	}

	/**
	 * 获得图片的大小 并进行压缩
	 * @param imageView
	 */
	protected ImageSize getImagetViewSize(ImageView imageView)
	{
		ImageSize imagesize=new ImageSize();
		
	DisplayMetrics metrics=	imageView.getContext().getResources().getDisplayMetrics();
		LayoutParams lp=imageView.getLayoutParams();
		int width=imageView.
				getWidth();//获取imageview的实际宽度
		if(width<=0){
			width=lp.width;//获取imageview在layout中声明的宽度
			
		}
		if(width<=0){
			width=imageView.getMaxWidth();//检查最大值
		}
		if(width<=0){
			width=metrics.widthPixels;
		}
		
		int height=imageView.
				getHeight();//获取imageview的实际宽度
		if(height<=0){
			height=lp.height;//获取imageview在layout中声明的宽度
			
		}
		if(height<=0){
			height=imageView.getMaxHeight();//检查最大值
		}
		if(height<=0){
			height=metrics.heightPixels;
		}
		imagesize.heigh=height;
		imagesize.width=width;
		
		return imagesize;
	}
	private class ImageSize
	{
		int width;
		int heigh;
	}

	private synchronized void addTask(Runnable runnable) {
	// TODO Auto-generated method stub
	mTaskQueue.add(runnable);
	
	//if(mPoolThreadHandler==null) wait();
 	try {
 		if(mPoolThreadHandler==null)
		mSemaphorePoolThreadHandler.acquire();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	mPoolThreadHandler.sendEmptyMessage(0x110);
	}

	/**
	 * 根据path在缓存中获取bitmap
	 * @param key
	 * @return
	 */
	private Bitmap getBitmapFromLruCache(String key) {
	// TODO Auto-generated method stub
	return mLruCache.get(key);
	}

	

	private class ImageBeanHolder
	{
		Bitmap bitmap;
		ImageView iamgeView;
		String path;	
	}
	private void init(int threadCount, Type type) {
	//后台轮询线程
	mPoolThread=new Thread(){
		@Override
		public void run() 
		{
		Looper.prepare();
		
		mPoolThreadHandler=new Handler()
		{
			@Override
			public void handleMessage(Message msg) 
			{
				//线程池去除一个任务进行执行
				mThreadPool.execute(getTask());
				
				try {
					mSemaphoreThreadPool.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		//释放一个信号量
		mSemaphorePoolThreadHandler.release();
		Looper.loop();
		};
	};
	mPoolThread.start();
	//获取我们应用的最大可用内存
	int maxMemory=(int) Runtime.getRuntime().maxMemory();
	int cacheMemory=maxMemory/8;
	mLruCache=new LruCache<String,Bitmap>(cacheMemory){
		@Override
		protected int sizeOf(String key, Bitmap value) {
			// TODO Auto-generated method stub
			return value.getRowBytes()*value.getHeight();
		}
	};
	//创建线程池
	mThreadPool=Executors.newFixedThreadPool(threadCount);
	mTaskQueue=new LinkedList<Runnable>();
	mType=type;
	
	
	mSemaphoreThreadPool=new Semaphore(threadCount);
}

	private Runnable getTask() {
		// TODO Auto-generated method stub
		if(mType==Type.FIFO){
			return mTaskQueue.removeFirst();
		}else if(mType==Type.LIFO){
			return mTaskQueue.removeLast();
		}
		return null;
	}
	
}
