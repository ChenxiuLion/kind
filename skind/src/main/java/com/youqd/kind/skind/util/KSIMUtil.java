/**
 * @Title：KSIMUtil
 * @Description：KSIM项目用到的工具类(本项目用到，其它项目用不到的公用方法)
 * @author youqd
 * @since 2014年7月1日 下午5:42:09 
 */
package com.youqd.kind.skind.util;

import android.content.Context;
import android.os.StatFs;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.youqd.kind.skind.R;
import com.youqd.kind.skind.util.StringUtil;

import java.io.File;

/**
 * @author yqd
 *
 */
public class KSIMUtil {

	/**
	 * 检查证书,如果证书不存在，提示信息
	 ***/
	public static boolean chechCert(String certPath) {
		File file = new File(certPath);
		if (file.isFile()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 得到SD卡可用空间大小
	 *
	 * @return
	 */
	public static long getSDFreeSize(String sdPath) {
		StatFs statFs = new StatFs(sdPath);
		// sd卡分区数
		int blockCounts = statFs.getBlockCount();
		Log.e("ray", "blockCounts" + blockCounts);
		// sd卡可用分区数
		int avCounts = statFs.getAvailableBlocks();
		Log.e("ray", "avCounts" + avCounts);
		// 一个分区数的大小
		long blockSize = statFs.getBlockSize();
		Log.e("ray", "blockSize" + blockSize);
		// sd卡可用空间
		long spaceLeft = avCounts * blockSize;
		return spaceLeft;
	}

	/**
	 * 得到性别
	 *
	 * @param vcardURL
	 * @param context
	 * @return
	 */
	public static String getSex(String vcardURL, Context context) {
		String sex = StringUtil.doEmpty(vcardURL);
		if (sex.equals("0")) {
			sex = context.getResources().getString(R.string.sex_man);
		} else if (sex.equals("1")) {
			sex = context.getResources().getString(R.string.sex_woman);
		}
		return sex;
	}

	/**
	 * 得到性别
	 *
	 * @param context
	 * @return
	 */
	public static int getSexCode(String sex, Context context) {
		if (sex.equals(context.getResources().getString(R.string.sex_man))) {
			return 0;
		} else if (sex.equals(context.getResources().getString(R.string.sex_woman))) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 隐藏软键盘
	 *
	 * @param context
	 * @param editText
	 */
	public static void hideSoftKeyboard(Context context, EditText editText) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
	}



	/**
	 * 拼成SQL IN语句，多个参数以逗号隔开:如：aa,bb,cc 拼成SQL IN 为 (‘aa','bb','cc')
	 *
	 * @param msg
	 * @return
	 */
	public static String joinSqlIn(String msg) {
		String sqlIn = "('" + msg.replace(",", "','") + "')";
		return sqlIn;
	}

	/**
	 * 根据文件名称得到文件的图标
	 *
	 * @param fileName
	 * @return
	 */
	public static int setFileIcon(Context context, String fileName) {
		try {
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			suffix = suffix.toLowerCase();

			if (suffix.equals("xls")) {
				return R.drawable.fcg;
			} else if (suffix.equals("doc")) {
				return R.drawable.fdq;
			} else if (suffix.equals("pdf")) {
				return R.drawable.fda;
			} else if (suffix.equals("ppt")) {
				return R.drawable.fcz;
			} else if (suffix.equals("txt")) {
				return R.drawable.fcv;
			} else if (suffix.equals("mp3")) {
				return R.drawable.fdc;
			} else if (suffix.equals("zip")) {
				return R.drawable.fcf;
			} else if (suffix.equals("xml")) {
				return R.drawable.fdp;
			} else if (suffix.equals("png") || suffix.equals("jpg") || suffix.equals("gif") || suffix.equals("bmp")) {
				return R.drawable.fdd;
			} else {
				return R.drawable.fco;
			}
		} catch (Exception ex) {
			return R.drawable.fco;
		}
	}
}
