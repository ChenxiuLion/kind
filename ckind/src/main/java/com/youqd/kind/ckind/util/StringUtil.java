/**
 * @Title：StringUtil.java
 * @Description：TODO
 * @author wangyn
 * @since 2014-2-24 下午4:04:14
 */
package com.youqd.kind.ckind.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.List;

public class StringUtil {
	// 处理空字符串
	public static String doEmpty(String str) {
		return doEmpty(str, "");
	}

	// 处理空字符串
	public static String doEmpty(String str, String defaultValue) {
		if (str == null || str.equalsIgnoreCase("null") || str.trim().equals("")) {
			str = defaultValue;
		} else if (str.startsWith("null")) {
			str = str.substring(4, str.length());
		}
		return str.trim();
	}

	public static boolean notEmpty(Object o) {
		return o != null && !"".equals(o.toString().trim()) && !"null".equalsIgnoreCase(o.toString().trim()) && !"undefined".equalsIgnoreCase(o.toString().trim());
	}

	public static boolean empty(Object o) {
		return o == null || "".equals(o.toString().trim()) || "null".equalsIgnoreCase(o.toString().trim()) || "undefined".equalsIgnoreCase(o.toString().trim());
	}

	public static boolean num(Object o) {
		int n = 0;
		try {
			n = Integer.parseInt(o.toString().trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean decimal(Object o) {
		double n = 0;
		try {
			n = Double.parseDouble(o.toString().trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (n > 0.0) {
			return true;
		} else {
			return false;
		}
	}

	// 给JID返回用户名
	public static String getUserNameByJid(String Jid) {
		if (empty(Jid)) {
			return null;
		}
		if (!Jid.contains("@")) {
			return Jid;
		}
		return Jid.split("@")[0];
	}

	// 给用户名返回JID
	public static String getJidByName(String userName, String jidFor) {
		if (empty(jidFor) || empty(jidFor)) {
			return null;
		}
		return userName + "@" + jidFor;
	}

	// 给用户名返回JID,使用默认域名koal.zz
	public static String getJidByName(String userName) {
		String jidFor = Constants.DEFAULT_SERVER_NAME;
		return getJidByName(userName, jidFor);
	}

	// 根据给定的时间字符串，返回月 日 时 分 秒
	public static String getMonthTomTime(String allDate) {
		return allDate.substring(5, 19);
	}

	// 根据给定的时间字符串，返回月 日 时 分 月到分钟
	public static String getMonthTime(String allDate) {
		return allDate.substring(5, 16);
	}

	/**
	 * 返回一个Double,跟据传来的小数据位截取
	 * @param doubNum  传来的浮点数
	 * @param pointNum 传来的小数点位数
	 * @return
	 */
	public static Double doubFormat(Double doubNum , int pointNum){
		DecimalFormat dft = new DecimalFormat("##.##");
		try{
			String str = ".";
			for(int i = 0 ; i < pointNum ; i ++){
				str += "#";
			}
			dft = new DecimalFormat(str);
			return Double.valueOf(dft.format(doubNum));
		}catch(Exception e){
			e.printStackTrace();
			return 0.0;
		}
	}


	/**
	 * 返回一个Double,返回小数据位截取
	 * @param doubNum  传来的浮点数
	 * @return
	 */
	public static Double doubFormat(Double doubNum){
		return doubFormat(doubNum, 2);
	}

	/**
	 * 得到错误的详细信息
	 * @param e
	 * @return
	 */
	public static String errorInfo(Exception e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.reset();
		e.printStackTrace(new PrintStream(baos));
		return baos.toString();
	}

	/**
	 * 得到错误的详细信息
	 * @param e
	 * @return
	 */
	public static String errorInfo(Throwable e){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.reset();
		e.printStackTrace(new PrintStream(baos));
		return baos.toString();
	}

	/**
	 * LIST集合转化成Array组合
	 * @param msgList
	 * @return
	 */
	public static String[] list2Array(List<String> msgList){
		String[] msgArr = new String[msgList.size()];

		for(int i = 0 ; i < msgList.size() ; i++){
			msgArr[i] = msgList.get(i);
		}
		return msgArr;
	}
}
