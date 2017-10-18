/**
 * @Title：LoginConfig.java
 * @Description：服务器及用户登录信息
 * @author wangyn
 * @since 2014-2-24 下午1:33:41
 */
package com.youqd.kind.skind.model;
import com.youqd.kind.skind.util.Constants;

public class LoginConfig {
	// XMPP服务器IP地址
	private String xmppHost;
	// XMPP服务器端口
	private Integer xmppPort;
	// XMPP服务器名称
	private String xmppServiceName;
	// 登陆账号
	private String username;
	// 登陆密码
	private String password;
	// 会话id
	private String sessionId;
	// 是否记住密码
	private boolean isRemember;
	// 是否自动登录
	private boolean isAutoLogin;
	// 是否隐藏登录
	private boolean isNovisible;
	// 用户连接成功connection
	private boolean isOnline;
	// 是否首次启动
	private boolean isFirstStart;
	//升级服务器地址
	private String updateHost;
	//升级服务器端口
	private Integer updatePort;
	//系统设置的发送消息是否加密，在证书登陆时使用，一般登陆时不使用（一般登陆只能明文发送）。
	private boolean isEncryptMessage = true;

	/** 加密证书密码   **/
	private String encryptCertKey;
	/** 签证证书密码  **/
	private String signCertKey;
	/** 加密证书地址 **/
	private String encryptCertPath;
	/** 签名证书地址  **/
	private String signCertPath;
	/** 用户证书类型  **/
	private int userCertType;			//0 单证书（加密签名用一个证书），1双证书（加密签名分开分成两个证书）
	/** 用户数据库名称    **/
	private String userDBName;

	/**  PIN码      **/
	private String pinCode;				//证书PIN码

	private String timestamp;

	private int preLoginType = Constants.LOGIN_TYPE_CRYPT;	//上一次登录方式
	private String accountUserName;							//明文账户用户名（只有账户登录时才用到)
	private String accountUserPass;							//明文账户用户密码（只有账户登录时才用到)

	public String getUpdateHost() {
		return updateHost;
	}
	public void setUpdateHost(String updateHost) {
		this.updateHost = updateHost;
	}
	public Integer getUpdatePort() {
		return updatePort;
	}
	public void setUpdatePort(Integer updatePort) {
		this.updatePort = updatePort;
	}
	public String getXmppHost() {
		return xmppHost;
	}
	public void setXmppHost(String xmppHost) {
		this.xmppHost = xmppHost;
	}
	public Integer getXmppPort() {
		return xmppPort;
	}
	public void setXmppPort(Integer xmppPort) {
		this.xmppPort = xmppPort;
	}
	public String getXmppServiceName() {
		return xmppServiceName;
	}
	public void setXmppServiceName(String xmppServiceName) {
		this.xmppServiceName = xmppServiceName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public boolean isRemember() {
		return isRemember;
	}
	public void setRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}
	public boolean isAutoLogin() {
		return isAutoLogin;
	}
	public void setAutoLogin(boolean isAutoLogin) {
		this.isAutoLogin = isAutoLogin;
	}
	public boolean isNovisible() {
		return isNovisible;
	}
	public void setNovisible(boolean isNovisible) {
		this.isNovisible = isNovisible;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public boolean isFirstStart() {
		return isFirstStart;
	}
	public void setFirstStart(boolean isFirstStart) {
		this.isFirstStart = isFirstStart;
	}
	public String getEncryptCertPath() {
		return encryptCertPath;
	}
	public void setEncryptCertPath(String encryptCertPath) {
		this.encryptCertPath = encryptCertPath;
	}
	public String getSignCertPath() {
		return signCertPath;
	}
	public void setSignCertPath(String signCertPath) {
		this.signCertPath = signCertPath;
	}
	public String getEncryptCertKey() {
		return encryptCertKey;
	}
	public void setEncryptCertKey(String encryptCertKey) {
		this.encryptCertKey = encryptCertKey;
	}
	public String getSignCertKey() {
		return signCertKey;
	}
	public void setSignCertKey(String signCertKey) {
		this.signCertKey = signCertKey;
	}
	public int getUserCertType() {
		return userCertType;
	}
	public void setUserCertType(int userCertType) {
		this.userCertType = userCertType;
	}
	public String getUserDBName() {
		return userDBName;
	}
	public void setUserDBName(String userDBName) {
		this.userDBName = userDBName;
	}

	public boolean isEncryptMessage() {
		return isEncryptMessage;
	}
	public void setEncryptMessage(boolean isEncryptMessage) {
		this.isEncryptMessage = isEncryptMessage;
	}
	public int getPreLoginType() {
		return preLoginType;
	}
	public void setPreLoginType(int preLoginType) {
		this.preLoginType = preLoginType;
	}
	public String getAccountUserName() {
		return accountUserName;
	}
	public void setAccountUserName(String accountUserName) {
		this.accountUserName = accountUserName;
	}
	public String getAccountUserPass() {
		return accountUserPass;
	}
	public void setAccountUserPass(String accountUserPass) {
		this.accountUserPass = accountUserPass;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
