/**
 * @Title：Constants.java
 * @Description：定义程序中所有使用到的静态常量
 * @author wangyn
 * @since 2014-2-24 上午11:32:25
 */
package com.youqd.kind.ckind.util;

public class Constants {

	/**  来用判断是否是通过登陆界面登陆,ture登陆界面登陆，false重连登陆     **/
	public static boolean IS_UI_LOGIN = true;
	public static final String RE_LOGIN_STR = "\nreconnection";

	public static int MAX_ROOM_CHAT_NUM = 50;				//最大获取离线记录数
	public static int MAX_FILE_SIZE = 1024 * 1024 * 20;		//最大发送或者共享文件20M

	/**登陆提示*/
	public static final int LOGIN_SECCESS = 0;// 成功
	public static final int HAS_NEW_VERSION = 1;// 发现新版本
	public static final int IS_NEW_VERSION = 2;// 当前版本为最新
	public static final int LOGIN_ERROR_ACCOUNT_PASS = 3;// 账号或者密码错误
	public static final int SERVER_UNAVAILABLE = 4;// 无法连接到服务器
	public static final int LOGIN_ERROR = 5;// 连接失败

	/**  连接XMPP服务器超时时间(秒)     **/
	public static final int CONN_OUT_TIME = 8;

	/**  软件名称，用来标识一个软件    **/
	public static final String SOFT_NAME = "KSIM_ANDROID";
	public static final String VERSIN_NAME = "version.txt";

	/**  证书地址,签名证书，加密证书,SPKM时服务器的公钥证书   **/
	public static final String SIGN_CERT_FILE = "signCertFile";
	public static final String ENCRYPT_CERT_FILE = "encryptCertFile";
	/** 用户的证书类型，用来判断是双证书还是单证书    **/
	public static final String SET_USER_CERT_TYPE = "setUserCertType";

	/** 选择证书时参数名称   **/
	public static final String SEL_CERT_FILE = "selFile";

	/** 客户端登陆类型  PC  **/
	public static final String CLIENT_TYPE_PC = "KSIM";

	/** 登陆方式(明文和密文)   **/
	public static final int LOGIN_TYPE_NORMAL = 0;
	public static final int LOGIN_TYPE_CRYPT = 1;

	/**  后台密码  **/
	public static final String CERNTER_PASS = "";

	public static final int PAGE_SIZE = 15;

	public static final String USER_KEY = "ksim.user.key";
	public static final String USER_JID_KEY = "user_jid";					//跳转Activity或者发布广播时传送USERJID的参数名字

	public static final String NEW_MESSAGE_ACTION = "roster.new.message";
	public static final String NEW_SYSTEM_MESSAGE_ACTION = "new.system";
	/**  收到一条系统推送的密钥     **/
	public static final String NEW_SYSTEM_CHATKEY = "new.system.chatkey";
	/**  推送消息，改变读取状态   **/
	public static final String SYSTEM_MESSAGE_STATUS_ACTION = "system.status";
	public static final String NEW_FILE_ACTION = "roster.new.file";
	public static final String ROSTER_SUBSCRIPTION = "roster.subscribe";

	public static final String ROSTER_PRESENCE_CHANGED = "roster.presence.changed";
	public static final String ROSTER_PRESENCE_CHANGED_KEY = "roster.presence.changed.key";

	/** 有消息内容改变，提示相应界面更新  **/
	public static final String MESSAGE_EDIT_ACTION = "message.edit";

	/**
	 * 花名册有删除的ACTION和KEY
	 */
	public static final String ROSTER_DELETED = "roster.deleted";
	public static final String ROSTER_DELETED_KEY = "roster.deleted.key";

	/**
	 * 花名册有更新的ACTION和KEY
	 */
	public static final String ROSTER_UPDATED = "roster.updated";
	public static final String ROSTER_UPDATED_KEY = "roster.updated.key";

	public static final String ACTION_SYS_MSG = "action_sys_msg";
	public static final String ACTION_RECONNECT_STATE = "action_reconnect_state";
	public static final String RECONNECT_STATE = "reconnect_state";
	/**
	 * 花名册有增加的ACTION和KEY
	 */
	public static final String ROSTER_ADDED = "roster.added";
	public static final String ROSTER_RELOADED = "roster.reloaded";
	public static final String ROSTER_ADDED_KEY = "roster.added.key";

	/**   会话参数             **/
	public static final String CHAT_TO_KEY = "chat_to_key";						//和谁聊天，谁的JID
	public static final String CHAT_REALNAME_KEY = "chat_to_name";					//和谁聊天，谁的真实名称

	/** 组织架构状态改变   **/
	public static final String CONTACT_STATUS_CHANGE = "contact_status_change";
	public static final String CONTACT_DEPTORUSER_CHANGE = "contact_dept_or_user_change";
	/** 传送更新状态对象   **/
	public static final String CONTACT_STATUS_KEY = "contact.status.key";
	//消息相关
	public static final String IMMESSAGE_KEY = "immessage.key";
	public static final String SYSTEM_MESSAGE_KEY = "system.message.key";
	public static final String IMFILE_KEY = "imfile.key";
	public static final String KEY_TIME = "immessage.time";
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	public static final int MESSAGE_TYPE_RECEIVE = 0;
	public static final int MESSAGE_TYPE_SEND = 1;
	//默认域名
	public static final String DEFAULT_SERVER_NAME = "koal.zz";
	//日期格式
	public static final String MS_FORMART = "yyyy-MM-dd HH:mm:ss";
	//会话文件最长保存时间
	public static final int FILE_MAX_SAVE_DAY = 180;
	//消息状态
	public static final int ADD_FRIEND = 1;// 好友请求
	public static final int SYS_MSG = 2; // 系统消息
	public static final int CHAT_MSG = 3;// 聊天消息
	public static final int READ = 1;
	public static final int UNREAD = 0;
	public static final int All = 2;
	//消息类型
	public static final String TEXT_TYPE = "TEXT";
	public static final String IMAGES_TYPE = "IMAGE";
	public static final String SOUND_TYPE = "SOUND";
	public static final String FILE_TYPE = "FILE";

	/**下载文件状态，发送广播的ACTION*/
	public static final String ACTION_FILE_DOWN_STATE="file.down.status";


	//图片、语音的存储路径
	public static final String EXTERN_PATH = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
	public static final String IMAGES_PATH = EXTERN_PATH + "/KSIM/images";
	public static final String SOUNDS_PATH = EXTERN_PATH + "/KSIM/sounds";
	public static final String FILE_PATH = EXTERN_PATH + "/KSIM/files";
	public static final String DATABASE_PATH = EXTERN_PATH + "/KSIM/database";
	public static final String AVATAR_DIR = EXTERN_PATH + "/KSIM/avatar";
	public static final String UPDATE_DIR = EXTERN_PATH + "/KSIM/update";
	public static final String LOG_DIR = EXTERN_PATH + "/KSIM/logs";
	public static final String CERT_DIR = EXTERN_PATH + "/KSIM/certs";
	public static final String CONTACT_DIR = EXTERN_PATH + "/KSIM/contact";

	/** 解密后的文件地址   **/
	public static final String DECODE_TEMP_DIR = EXTERN_PATH + "/KSIM/decode";

	/** 服务端签名证书地址  **/
	public static final String SERVER_PUBLIC_SIGN_PATH = "spkm_server.cer";

	public static final String PHOTO_ORIGINAL_DIR = EXTERN_PATH + "/KSIM/orginal_photo";
	public static final String PHOTO_THUMBNAIL_DIR = EXTERN_PATH + "/KSIM/thumbnail_photo";

	/**  项目启动时有必要添加的文件夹   **/
	public static final String[] DEFAULT_PATH = new String[]{IMAGES_PATH,SOUNDS_PATH,DATABASE_PATH,AVATAR_DIR,UPDATE_DIR,LOG_DIR,PHOTO_ORIGINAL_DIR,PHOTO_THUMBNAIL_DIR,CERT_DIR,DECODE_TEMP_DIR,FILE_PATH,CONTACT_DIR};

	//mnt/sdcard/... 为了兼容Android 2 版本的路径显示效果
	public static final String OLD_IMAGES_PATH = "mnt/sdcard/KSIM/images";
	public static final String OLD_SOUNDS_PATH = "mnt/sdcard/KSIM/sounds";
	//SharedPerferences ID
	public static final String SHARED_PREFERENCES_ID = "ksim_sp_id";

	//服务器参数信息 ID
	public static final String XMPP_HOST = "ksim_xmpp_host";
	public static final String XMPP_PORT = "ksim_xmpp_port";
	public static final String XMPP_UPDATE_HOST="ksim.xmpp.update.host";
	public static final String XMPP_UPDATE_PORT="ksim.xmpp.update.port";
	public static final String XMPP_SEIVICE_NAME = "ksim_xmpp_service_name";
	public static final String USER_DB_NAME = "user_db_name";						//用户数据库名称
	public static final String IS_AUTOLOGIN = "isAutoLogin";// 是否自动登录
	public static final String IS_NOVISIBLE = "isNovisible";// 是否隐身
	public static final String IS_REMEMBER = "isRemember";// 是否记住账户密码
	public static final String IS_ONLINE = "isOnline";
	public static final String IS_FIRSTSTART = "isFirstStart";// 是否首次启动
	public static final String USERNAME = "ksim_username";
	public static final String PASSWORD = "ksim_password";
	public static final String ENCRYPT_CERT_KEY = "ksim_encrypt_cert_key";			//加密证书KEY
	public static final String SIGN_CERT_KEY = "ksim_sign_cert_key";				//签名证书KEY 
	public static final String PRE_LOGIN_TYPE = "per_login_type";								//上一次登陆方式 
	public static final String ACCOUNT_LOGIN_USERNAME = "account_login_username";				//账户登陆用户名
	public static final String ACCOUNT_LOGIN_USERPASS = "account_login_userpass";				//账户登陆用户密码
	public static final String ECRYPT_DEVICE_TYPE = "ecrypt_device_type";						//设备类型
	public static final String DEVICE_PINCODE = "device_pincode";								//设备PIN码

	public static final String SERVER_SYS_ID_KEY = "server_sys_id_key";					//房间传送参数的KEY
	public static final String MESSAGE_NOTIFY_KEY = "message_notify";
	public static final String MESSAGE_SOUND_KEY = "message_sound";
	public static final String MESSAGE_ENCRYPT_KEY = "message_ecrypt";					//证书登陆是发送消息类型
	public static final String MESSAGE_FLAG_KEY = "message_flag";
	public static final String SHOW_HEAD_KEY = "show_head";
	public static final String PULLREFRESH_SOUND_KEY = "pullrefresh_sound";

	/**  下面是一些状态信息   **/

	/**接收到的消息*/
	public static final int MESSAGE_TYPE＿RECEIVER = 0;
	/**发送的消息*/
	public static final int MESSAGE_TYPE＿SEND = 1;
	/**文本消息*/
	public static final int MESSAGE_CONTENT_TYPE＿TEXT = 0;
	/**图片消息*/
	public static final int MESSAGE_CONTENT_TYPE＿IMAGE = 1;
	/**语音消息*/
	public static final int MESSAGE_CONTENT_TYPE＿VOICE = 2;
	/**文件消息*/
	public static final int MESSAGE_CONTENT_TYPE＿File = 3;
	/**未读消息状态*/
	public static final int MESSAGE_STATUS_UNREAD = 0;
	/**已读消息状态*/
	public static final int MESSAGE_STATUS_READED = 1;
	/**存档消息状态*/
	public static final int MESSAGE_STATUS_STORE = 1;
	/**聊天消息类型*/
	public static final int MESSAGE_FLAG_CHAT = 0;
	/**系统消息类型*/
	public static final int MESSAGE_FLAG_SYSTEM = 1;

	/**消息发送成功标记*/
	public static final int MESSAGE_TRACE_SEND = 0;
	/**消息已送达标记*/
	public static final int MESSAGE_TRACE_ARRIVE = 1;
	/**消息已阅读标记*/
	public static final int MESSAGE_TRACE_READ =2;
	/**消息传输错误标记*/
	public static final int MESSAGE_TRACE_ERROR =3;

	public static final String ACTIVITY_IS_TOP="activity_is_top";

	/**  文件下载状态    **/
	public static final int FILE_STATUS_NODOWN = 0;
	public static final int FILE_STATUS_DOWN = 1;
	public static final int FILE_STATUS_ERROR = 2;

	/**   用户证书类型   **/
	public static final int USER_CERT_TYPE_SINGLE = 0;				//单证书
	public static final int USER_CERT_TYPE_BOTH = 1;				//双证书

	/**  更新类型  0 联系人更新，1房间更新    **/
	public static final int ROSTER_UPDATE = 0;						//ROSTER 更新
	public static final int ROSTER_ADD = 2;							//添加
	public static final int ROSTER_DEL = 3;							//删除
	public static final int ROSTER_INIT = 4;						//初始化
	public static final int ROSTER_RELOAD = 5;						//重新加载

	public static final int CONTACT_INIT = 10;						//CONTACT 初始化
	public static final int CONTACT_EXTEND = 11;						//CONTACT 加载组成
	public static final int CONTACT_RELOAD = 12;						//CONTACT 重新加载
	public static final int CONTACT_EDIT_STATUS = 13;				//CONTACT 状态列新


	public static final int ROOM_INIT = 21;						//ROOM请求初始化
	public static final int ROOM_DATA = 22;						//ROOM数据更新

	/** 中文空格   **/
	public static final String CHINA_EMPTY = "　";
	public static final String GROUP_MYFRIEND_NAME = "我的好友";

	/**  末分类的常量 ,用于连接服务器等   **/
	public static final int PACKET_TIMEOUT = 30000;
	public static final String XMPP_IDENTITY_NAME = "xx";
	public static final String XMPP_IDENTITY_TYPE = "phone";

	/**  当前连接服务器状态  **/
	public static final int CURR_CONN_STATUS_SUCCESS = 0;
	public static final int CURR_CONN_STATUS_FAILTURE = 1;

	/**  网络相关   **/
	public static final int CONN_CONNECTED = 0;							//连接成功
	public static final int CONN_DISCONNECTED = -1;						//连接失败
	public static final int CONN_CONNECTING = 1;						//连接中
	public static final String CONN_PONG_TIMEOUT = "连接超时";			//连接超时
	public static final String CONN_NETWORK_ERROR = "网络错误";			//网络错误
	public static final String CONN_LOGOUT = "手动退出";					//手动退出
	public static final int CONN_RECONNECT_MINNUM = 2;					//重新连接时间间隔(秒)
	public static final int CONN_RECONNECT_MAXIMUM = 10 * 60;			//最大重连时间间隔(秒)
	public static final String CONN_RECONNECT_ALARM = "com.koal.sim.RECONNECT_ALARM";	//重新连接的Activity名字

	/** 获取证书地址   **/
	public static String CERT_FULL_PATH = "http://${ip}:${port}/certificate/${userName}.cer";
	/** 得到用户匹配SN地址  **/
	public static String USER_SN_PATH = "http://${ip}:${port}/ksim/SNServlet?type=${type}&userName=${userName}";
	/**  根据用户名称得到证书或者返回找不到证书的服务地址   **/
	public static String USER_CERT_SERVER_PATH = "http://${ip}:${port}/ksim/SNServlet?userName=${userName}";
	/**  软件更新请求的服务器目录地址   **/
	public static String SERVER_UPDATE_PATH = "http://${ip}:${port}/ksim/update/";
	/**  用户反馈信息       **/
	public static String FEEDBACK_PATH = "http://${ip}:${port}/ksim/feedback?softType=1&submitUser=${submitUser}&msg=${msg}&version=${version}";

	/**  软件上传文件地址   **/
	public static String SERVER_UPFILEDATA_PATH = "http://${ip}:${port}/ksim/fileUpload";

	/**  软件上传文件地址   **/
	public static String SERVER_UPFILE_PATH = "http://${ip}:${port}/ksim/fileStreamUpload";

	/**  软件下载文件地址   **/
	public static String SERVER_DOWNLOAD_PATH = "http://${ip}:${port}/ksim/fileDownload?filePath=${filePath}";

	/** 下载全部的组织机构和联系人地址**/
	public static String DOWNLOAD_ALL_CONTACT_PATH="http://${ip}:${port}/ksim/contactListServlet";

	/** 下载更新的组织机构和联系人地址**/
	public static String DOWNLOAD_UPDATE_CONTACT_PATH = "http://${ip}:${port}/ksim/contactModifyServlet?timestamp=${timestamp}";

	/** 调用接口查询用户信息**/
	public static final String searchUeserUrl = "http://${ip}:${port}/ksim/searchUser?searchType=${searchType}&searchKey=${searchKey}&pageNum=${pageNum}&pageSize=${pageSize}&userStatus=${userStatus}";

	/***   多人会话相关      **/
	public static final String ROOM_ADD_ACTION = "room_add_action";			//添加房间
	public static final String ROOM_JOIN_ACTION = "room_mjoin_action";		//加入房间
	public static final String ROOM_LEAVE_ACTION = "room_mleave_action";	//退出房间
	public static final String ROOM_KICKED_ACTION = "room_mkicked_action";	//退出房间
	public static final String ROOM_JID_KEY = "room_jid";					//跳转Activity或者发布广播时传送ROOMJID的参数名字
	public static final String ROOM_NAME_KEY = "room_name";					//跳转Activity或者发布广播时传送ROOMNAME的参数名字
	public static final String MEMBER_ISLINE_KEY = "member_isline";			//请求MemberActivity时的参数名字，false 请求所有用户，true请求在线用户
	public static final String MEMBER_SEL_KEY = "member_sel_result";		//请求MemberActivity返回的结果参数名字
	public static final String ROOM_PASS_KEY = "room_pass_result";			//请求Activity是到房间密码，返回的结果参数名字
	public static final String ROOM_ADD_MEMBER = "add";						//房间设置中邀请人操作
	public static final String ROOM_DEL_MEMBER = "del";						//房间设置中踢除人操作
	public static final String NEW_ROOM_MSG_ACTION = "new_room_msg_action";	//接收到新消息广播名称
	public static final String ROOM_MSG_KEY = "room_msg_key";				//接收到新消息之后，传送给广播的参数名称
	public static final String ROOM_SYS_CODE_KEY = "room_sys_code_key";		//房间传送参数的KEY
	public static final String ROOM_SYS_STATUS_KEY = "room_sys_status_key";	//跳转Activity或者发布广播时传送ROOMJID的参数名字
	public static final String ROOM_SYS_JSON_KEY = "room_sys_json_jid";		//接收服务器传来的JSON串参数名字

	public static final String FILE_PATH_KEY = "file_path_key";				//用户传送文件地址的参数
	public static final String SD_PATH_KEY = "sd_path_key";					//传送SD卡的参数
	public static final String FILE_SUFFIX_KEY = "file_suffix_key";			//传送文件扩展名的参数
	public static final String FILE_MULTI_KEY = "file_multi_key";			//选择文件时，是否支持多选的参数

	public static final String ECRYPT_DEVICE_TYPE_KEY = "ecrypt_device_type_key";	//加密设备类型
	public static final String ECRYPT_DEVICE_TEXT_KEY = "ecrypt_device_text_key";	//加密设备类型文字
	public static final String ECRYPT_CERT_INFO_KEY = "ecrypt_cert_info_key";		//加密设备详细

	public static final long LOGIN_MAX_OUT_TIME = 25 * 1000L;				//登陆最大等待时间
	public static final int PIC_MAX_SIZE = 60;								//发送图片最大KB

	/**    自定义组              **/
	public static final String CG_SEL_GROUP_KEY = "cg_sel_group_key";		//选择组参数

	/**   请求ActivityForResult 编码   **/
	public static final int ROOM_PASS_RESULT_CODE = 1001;		//请求RoomJoinPassActivity编码

	/**    好友管理    	***/
	public static final int CG_USER_MANAGER_CODE = 0;						//用户管理
	public static final int CG_GROUP_MANAGER_CODE = 1;						//分组管理
	public static final int CG_CONTACT_MANAGER_CODE = 2;					//联系人管理
	public static final String CG_GROUP_NAME_KEY = "cg_group_name_key";		//传送分组名称KEY
	public static final String CG_GROUP_TYPE_KEY = "cg_group_type_key";		//传送给CGGroupSelActivity 选择用户组，还是更新用户组
	/**  请求CGGroupSelActivity时是选择分组还是修改分组     **/
	public static final int CG_SEL_GROUP_VAL = 0;
	public static final int CG_EDIT_GROUP_VAL = 1;

	public static final String CG_USER_TYPE_KEY = "cg_user_type_key";		//传送给CGUserInfoActivity 添加用户还是删除用户
	/** 请求CGUserInfoActivity时，添加用户还是删除用户   **/
	public static final int CG_ADD_USER_VAL = 0;
	public static final int CG_DEL_USER_VAL = 1;


	/**  正则表达式       **/

	/**    判断字符串必需包含大写字母，小写字母，数字，而且数字长度为6-12位         **/
	public static final String REGEXP_PASS = "^(?!\\D+$)(?![^a-z]+$)(?![^A-Z]+$).{6,12}$";

	public static String TIMESTAMP = "contact_timestamp";

	/** 群信息对应的广播标志 **/
	public static final String ROOM_GROUP = "room_broadcast_flag";
	/** 群共享文件对应的广播标志**/
	public static final String ROOM_SHARE_FILE = "room_share_file";

}
