package com.cxandroid.imageutil.imageloader.util;

import java.io.File;

import android.os.Environment;

/**
 * 文件工具类
 * 
 * @author QiWanLBB
 * 
 * @陈修 2015-8-28
 */
public class FileUtil {

	/**
	 * 检查上传文件的大小
	 */
	public static boolean isUpFile(File file)
	{
		long length = 1024*1024*3;
		if(file.length()<length)
		{
			return true;
		}
		
		return false;
		
	}
	 public static boolean hasSdcard() {

		      String status = Environment.getExternalStorageState();
		 
		      if (status.equals(Environment.MEDIA_MOUNTED)) {

		          return true;
		
		      } else {
		
		          return false;
		
		      }
	
		  }

}
