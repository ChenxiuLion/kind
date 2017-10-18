/**
 * @Title：VerifyUtils
 * @Description：验证类
 * @author youqd
 * @since 2015年5月27日 上午10:36:11 
 */
package com.youqd.kind.ckind.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtil {

	/**
	 * 验证是否包含特殊字符，如果包含返回false,不包含返回true
	 *
	 * @return
	 */
	public static boolean specialChat(String msg) {
		String regex = "^[^`~!$^&*=|{}:\\[\\].<>/?~！@#￥……&——|【】‘：”“'。？]{0,50}$";
		Pattern pattern = Pattern.compile(regex);
		boolean result = pattern.matcher(msg).matches();
		return result;
	}

	/**
	 * 判断是否为浮点数或者整数
	 *
	 * @param str
	 * @return true Or false
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否为正确的邮件格式
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmail(String str) {
		if (isEmpty(str))
			return false;
		return str.matches("^([a-zA-Z0-9]*[-_]*[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]*[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
	}

	/**
	 * 判断字符串是否为合法手机号 11位 13 14 15 18开头
	 *
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(String str) {
		if (isEmpty(str))
			return false;
		return str.matches("^(13|14|15|18)\\d{9}$");
	}

	/**
	 * 判断是否为数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * 判断字符串是否为非空(包含null与"")
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str == null || "".equals(str))
			return false;
		return true;
	}

	/**
	 * 判断字符串是否为非空(包含null与"","    ")
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNotEmptyIgnoreBlank(String str) {
		if (str == null || "".equals(str) || "".equals(str.trim()))
			return false;
		return true;
	}

	/**
	 * 判断字符串是否为空(包含null与"")
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str))
			return true;
		return false;
	}

	/**
	 * 判断字符串是否为空(包含null与"","    ")
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmptyIgnoreBlank(String str) {
		if (str == null || "".equals(str) || "".equals(str.trim()))
			return true;
		return false;
	}
}
