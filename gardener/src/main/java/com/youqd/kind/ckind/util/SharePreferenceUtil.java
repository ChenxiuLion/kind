/**
 * @Title：SharePreferenceUtil.java
 * @Description：TODO SharePreference对象管理类
 * @author wangyn
 * @since 2014-2-27 上午10:11:43
 */
package com.youqd.kind.ckind.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.kind.chx.gardener.R;
import com.youqd.kind.ckind.model.LoginConfig;


@SuppressLint("CommitPrefEdits")
public class SharePreferenceUtil {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;
	private Context context;

	public SharePreferenceUtil(Context context) {
		sp = context.getSharedPreferences(Constants.SHARED_PREFERENCES_ID, Context.MODE_PRIVATE);
		editor = sp.edit();
		this.context = context;
	}

	public void saveLoginConfig(LoginConfig loginConfig) {

		editor.putString(Constants.XMPP_HOST, loginConfig.getXmppHost()).commit();
		editor.putInt(Constants.XMPP_PORT, loginConfig.getXmppPort()).commit();
		editor.putString(Constants.XMPP_UPDATE_HOST, loginConfig.getUpdateHost()).commit();
		editor.putInt(Constants.XMPP_UPDATE_PORT, loginConfig.getUpdatePort()).commit();
		editor.putString(Constants.XMPP_SEIVICE_NAME, loginConfig.getXmppServiceName()).commit();
		editor.putString(Constants.USERNAME, loginConfig.getUsername()).commit();
		editor.putString(Constants.PASSWORD, loginConfig.getPassword()).commit();
		editor.putString(Constants.ENCRYPT_CERT_KEY, loginConfig.getEncryptCertKey()).commit();
		editor.putString(Constants.SIGN_CERT_KEY, loginConfig.getSignCertKey()).commit();
		editor.putBoolean(Constants.IS_AUTOLOGIN, loginConfig.isAutoLogin()).commit();
		editor.putBoolean(Constants.IS_NOVISIBLE, loginConfig.isNovisible()).commit();
		editor.putBoolean(Constants.IS_REMEMBER, loginConfig.isRemember()).commit();
		editor.putBoolean(Constants.IS_ONLINE, loginConfig.isOnline()).commit();
		editor.putBoolean(Constants.IS_FIRSTSTART, loginConfig.isFirstStart()).commit();
		editor.putString(Constants.ENCRYPT_CERT_FILE, loginConfig.getEncryptCertPath()).commit();
		editor.putString(Constants.SIGN_CERT_FILE, loginConfig.getSignCertPath()).commit();
		editor.putInt(Constants.SET_USER_CERT_TYPE, loginConfig.getUserCertType()).commit();
		editor.putString(Constants.USER_DB_NAME, loginConfig.getUserDBName()).commit();
		editor.putBoolean(Constants.MESSAGE_ENCRYPT_KEY, true).commit();
		editor.putInt(Constants.PRE_LOGIN_TYPE,loginConfig.getPreLoginType()).commit();
		editor.putString(Constants.ACCOUNT_LOGIN_USERNAME, loginConfig.getAccountUserName()).commit();
		editor.putString(Constants.ACCOUNT_LOGIN_USERPASS, loginConfig.getAccountUserPass()).commit();
		editor.putString(Constants.DEVICE_PINCODE, loginConfig.getPinCode()).commit();
		editor.putString(Constants.TIMESTAMP, loginConfig.getTimestamp()).commit();
	}

	public void clear() {
		LoginConfig loginConfig = getLoginConfig();
//		loginConfig.setUsername("");
//		loginConfig.setPassword("");
		loginConfig.setAutoLogin(false);
		loginConfig.setNovisible(false);
		loginConfig.setRemember(false);
		loginConfig.setFirstStart(true);
//		loginConfig.setEncryptMessage(false);//默认明文发送

		saveLoginConfig(loginConfig);
	}

	public LoginConfig getLoginConfig() {
		LoginConfig loginConfig = new LoginConfig();

		loginConfig.setXmppHost(sp.getString(Constants.XMPP_HOST, context.getResources().getString(R.string.xmpp_host)));
		loginConfig.setXmppPort(sp.getInt(Constants.XMPP_PORT, context.getResources().getInteger(R.integer.xmpp_port)));

		loginConfig.setUpdateHost(sp.getString(Constants.XMPP_UPDATE_HOST, context.getResources().getString(R.string.xmpp_update_host)));
		loginConfig.setUpdatePort(sp.getInt(Constants.XMPP_UPDATE_PORT, context.getResources().getInteger(R.integer.xmpp_update_port)));

		loginConfig.setUsername(sp.getString(Constants.USERNAME, ""));
		loginConfig.setPassword(sp.getString(Constants.PASSWORD, ""));
		loginConfig.setEncryptCertKey(sp.getString(Constants.ENCRYPT_CERT_KEY, ""));
		loginConfig.setSignCertKey(sp.getString(Constants.SIGN_CERT_KEY, ""));
		loginConfig.setXmppServiceName(sp.getString(Constants.XMPP_SEIVICE_NAME, context.getResources().getString(R.string.xmpp_service_name)));
		loginConfig.setAutoLogin(sp.getBoolean(Constants.IS_AUTOLOGIN, false));
		loginConfig.setNovisible(sp.getBoolean(Constants.IS_NOVISIBLE, false));
		loginConfig.setRemember(sp.getBoolean(Constants.IS_REMEMBER, false));
		loginConfig.setFirstStart(sp.getBoolean(Constants.IS_FIRSTSTART, false));
		loginConfig.setSignCertPath(sp.getString(Constants.SIGN_CERT_FILE, ""));
		loginConfig.setUserCertType(sp.getInt(Constants.SET_USER_CERT_TYPE, Constants.USER_CERT_TYPE_BOTH));
		loginConfig.setUserDBName(sp.getString(Constants.USER_DB_NAME, loginConfig.getUsername()));
		/** 默认发送密文  **/
		loginConfig.setEncryptMessage(sp.getBoolean(Constants.MESSAGE_ENCRYPT_KEY, true));
		loginConfig.setPreLoginType(sp.getInt(Constants.PRE_LOGIN_TYPE, Constants.LOGIN_TYPE_CRYPT));
		loginConfig.setAccountUserName(sp.getString(Constants.ACCOUNT_LOGIN_USERNAME, ""));
		loginConfig.setAccountUserPass(sp.getString(Constants.ACCOUNT_LOGIN_USERPASS, ""));
		loginConfig.setPinCode(sp.getString(Constants.DEVICE_PINCODE, ""));
		loginConfig.setTimestamp(sp.getString(Constants.TIMESTAMP, ""));

		/** 如果用户证书类型是单证书 话，直接取签名证书的地址   **/
		if(loginConfig.getUserCertType() == Constants.USER_CERT_TYPE_SINGLE){
			loginConfig.setSignCertPath(sp.getString(Constants.SIGN_CERT_FILE, ""));
		}else{
			loginConfig.setEncryptCertPath(sp.getString(Constants.ENCRYPT_CERT_FILE, ""));
		}
		return loginConfig;
	}

	// appid
	public void setAppId(String appid) {
		// TODO Auto-generated method stub
		editor.putString("appid", appid);
		editor.commit();
	}

	public String getAppId() {
		return sp.getString("appid", "");
	}

	// user_id
	public void setUserId(String userId) {
		editor.putString("userId", userId);
		editor.commit();
	}

	public String getUserId() {
		return sp.getString("userId", "");
	}

	// channel_id
	public void setChannelId(String ChannelId) {
		editor.putString("ChannelId", ChannelId);
		editor.commit();
	}

	public String getChannelId() {
		return sp.getString("ChannelId", "");
	}

	// nick
	public void setNick(String nick) {
		editor.putString("nick", nick);
		editor.commit();
	}

	public String getNick() {
		return sp.getString("nick", "");
	}

	// 头像图标
	public int getHeadIcon() {
		return sp.getInt("headIcon", 0);
	}

	public void setHeadIcon(int icon) {
		editor.putInt("headIcon", icon);
		editor.commit();
	}

	// 证书登陆时，发送消息是否使用加密，默认设置为明文发送(false)
	public boolean getMessageEncrypt() {
		return sp.getBoolean(Constants.MESSAGE_ENCRYPT_KEY, true);
	}

	public void setMessageEncrypt(boolean icon) {
		editor.putBoolean(Constants.MESSAGE_ENCRYPT_KEY, icon);
		editor.commit();
	}

	// 设置Tag
	public void setTag(String tag) {
		editor.putString("tag", tag);
		editor.commit();
	}

	public String getTag() {
		return sp.getString("tag", "");
	}

	// 是否通知
	public boolean getMsgNotify() {
		return sp.getBoolean(Constants.MESSAGE_NOTIFY_KEY, true);
	}

	public void setMsgNotify(boolean isChecked) {
		editor.putBoolean(Constants.MESSAGE_NOTIFY_KEY, isChecked);
		editor.commit();
	}

	// 新消息是否有声音
	public boolean getMsgSound() {
		return sp.getBoolean(Constants.MESSAGE_SOUND_KEY, true);
	}

	public void setMsgSound(boolean isChecked) {
		editor.putBoolean(Constants.MESSAGE_SOUND_KEY, isChecked);
		editor.commit();
	}

	//发送消息是否加密
	public boolean getMsgFlag() {
		return sp.getBoolean(Constants.MESSAGE_FLAG_KEY, true);
	}

	public void setMsgFlag(boolean isChecked) {
		editor.putBoolean(Constants.MESSAGE_FLAG_KEY, isChecked);
		editor.commit();
	}

	// 刷新是否有声音
	public boolean getPullRefreshSound() {
		return sp.getBoolean(Constants.PULLREFRESH_SOUND_KEY, true);
	}

	public void setPullRefreshSound(boolean isChecked) {
		editor.putBoolean(Constants.PULLREFRESH_SOUND_KEY, isChecked);
		editor.commit();
	}

	// 是否显示自己头像
	public boolean getShowHead() {
		return sp.getBoolean(Constants.SHOW_HEAD_KEY, true);
	}

	public void setShowHead(boolean isChecked) {
		editor.putBoolean(Constants.SHOW_HEAD_KEY, isChecked);
		editor.commit();
	}

	// 表情翻页效果
	public int getFaceEffect() {
		return sp.getInt("face_effects", 3);
	}

	public void setFaceEffect(int effect) {
		if (effect < 0 || effect > 11)
			effect = 3;
		editor.putInt("face_effects", effect);
		editor.commit();
	}
}
