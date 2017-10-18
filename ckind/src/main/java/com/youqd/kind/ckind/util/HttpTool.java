package com.youqd.kind.ckind.util;


import android.os.Environment;
import android.text.TextUtils;

import com.cosfund.library.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.youqd.kind.ckind.activity.AddFamilyActivity;
import com.youqd.kind.ckind.base.BaseApplication;
import com.youqd.kind.ckind.bean.AddDynamic;
import com.youqd.kind.ckind.bean.GetLookUserList;
import com.youqd.kind.ckind.bean.LeaveBean;
import com.youqd.kind.ckind.bean.LoginBean;
import com.youqd.kind.ckind.bean.MessgerList;
import com.youqd.kind.ckind.bean.NewBean;
import com.youqd.kind.ckind.bean.NewsModel;
import com.youqd.kind.ckind.bean.ParentRelation;
import com.youqd.kind.ckind.bean.PingList;
import com.youqd.kind.ckind.bean.PostJob;
import com.youqd.kind.ckind.bean.PostMessger;
import com.youqd.kind.ckind.bean.PostNew;
import com.youqd.kind.ckind.bean.UserLookBean;
import com.youqd.kind.ckind.constant.Constant;
import com.youqd.kind.ckind.fragment.RecordFragment;
import com.youqd.kind.ckind.model.Ping;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * 网络交互
 * Created by Chenxiu on 2016/6/12.
 */
public class HttpTool {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient mOkHttpClient;

    public static HttpTool getInstance() {
        return new HttpTool();
    }

    public HttpTool(){
        this.mOkHttpClient = new OkHttpClient();
    }


    /**
     * 用户登录
     * @param name 账号
     * @param password 密码
     * @param callback 回调
     */
    public void doLogin(String name,String password,KingCallback callback){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("UserAccount",name);
        jsonObject.addProperty("UserPassword",password);

        doPost(Constant.BASE_URL+"api/Parent/Login",jsonObject.toString(),callback);
    }

    /**
     * 获取园丁通讯录
     * @param name
     * @param kindId
     * @param classid
     * @param callback
     */
    public void doGardener(String name,String kindId,String classid,KingCallback callback){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("UserName",name);
        jsonObject.addProperty("KindergartenID",kindId);
        jsonObject.addProperty("ClassID",classid);
        doPost(Constant.BASE_URL+"api/Gardener/SearchList",jsonObject.toString(),callback);
    }


    /**
     * 获取园丁通讯录
     * @param callback
     */
    public void doUpdata(KingCallback callback){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("AppName","jiazhang");
        doPost(Constant.BASE_URL+"api/AppVersion/SearchList",jsonObject.toString(),callback);
    }
    /**
     * 获取留言列表
     * @param kindId
     * @param classid
     * @param callback
     */
    public void doMessgerListg(String ParentID,String kindId,String classid,KingCallback callback){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("ParentID",ParentID);
        jsonObject.addProperty("KindergartenID",kindId);
        jsonObject.addProperty("ClassID",classid);
        doPost(Constant.BASE_URL+"api/TeacherMessage/SearchList",jsonObject.toString(),callback);
    }


    public void doMessgerListg(String ParentID,KingCallback callback){
        doGet(Constant.BASE_URL+"api/TeacherMessage/GetParentToGardenerLast?parentId="+ParentID,callback);
    }
    /**
     * 获取留言列表
     * @param kindId
     * @param classid
     * @param callback
     */
    public void doMessgerListg(String ParentID,String GardenerId,String kindId,String classid,KingCallback callback){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("ParentID",ParentID);
        jsonObject.addProperty("KindergartenID",kindId);
        jsonObject.addProperty("ClassID",classid);
        jsonObject.addProperty("GardenerId",GardenerId);
        doPost(Constant.BASE_URL+"api/TeacherMessage/SearchList",jsonObject.toString(),callback);
    }
    /**
     * 留言
     * @param callback
     */
    public void addMessger(PostMessger messger, KingCallback callback){
        doPost(Constant.BASE_URL+"api/TeacherMessage/Add",new Gson().toJson(messger).toString(),callback);
    }
    /**
     * 留言
     * @param callback
     */
    public void lookMessger(MessgerList.Gardener data, KingCallback callback){
        ArrayList<Integer> mIds = new ArrayList<>();
        if(data.getGardenerType() == 1){
            for(MessgerList.Gardener.Messger messger : data.getmData()){
                mIds.add(messger.getId());
            }
        }else{
            mIds.add(data.getId());
        }
        doPost(Constant.BASE_URL+"api/TeacherMessage/BatchUpdateStatus",new Gson().toJson(mIds),callback);
    }
    /**
     * 请假
     * @param callback
     */
    public void doLeave(LeaveBean messger, KingCallback callback){
        doPost(Constant.BASE_URL+"api/AttendanceRecord/Add",new Gson().toJson(messger).toString(),callback);
    }

    /**
     * 获取看宝宝界面
     * @param classid
     * @param kinId
     * @param callback
     */
    public void doSeeList(String classid,String kinId,KingCallback callback){
        JsonObject jsonObject= new JsonObject();
      //  jsonObject.addProperty("ClassID",classid);
        jsonObject.addProperty("KindergartenID",kinId);
/*        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        jsonObject.addProperty("VideoViewStartTime",dateFormat.format(new Date()));*/
        doPost(Constant.BASE_URL+"api/Video/SearchList",jsonObject.toString(),callback);
    }

    /**
     * 获取看宝宝界面
     * @param callback
     */
    public void getPatriarch(String babyid,KingCallback callback){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("BabyID",babyid);
        doPost(Constant.BASE_URL+"api/ParentRelation/SearchList",jsonObject.toString(),callback);
    }

    /**
     * 获取看宝宝界面
     * @param callback
     */
    public void AddPing(Ping ping, KingCallback callback){
        doPost(Constant.BASE_URL+"api/GrowthDynamicsComment/Add",new Gson().toJson(ping),callback);
    }


    /**
     * 获取看宝宝界面
     * @param callback
     */
    public void getPing(PingList ping, KingCallback callback){
        doPost(Constant.BASE_URL+"api/GrowthDynamicsComment/SearchList",new Gson().toJson(ping),callback);
    }
    /**
     * 获取看宝宝界面
     * @param callback
     */
    public void growthDynamics(String KindergartenID,int type,String classid,int pager,KingCallback callback){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("KindergartenID",KindergartenID);
        jsonObject.addProperty("OrderBy","id");
        jsonObject.addProperty("PageIndex",pager);
        if(type == 2) {
            jsonObject.addProperty("PraiseNum", 1);
        }else{
            jsonObject.addProperty("ClassId", classid);
        }
        jsonObject.addProperty("PageSize",10);
        jsonObject.addProperty("Ascending",false);
        doPost(Constant.BASE_URL+"api/GrowthDynamics/SearchPagedListWithComment",jsonObject.toString(),callback);
    }



    public void getWDMessger(String KindergartenID,String userId,int pager,KingCallback callback){
        JsonObject jsonObject= new JsonObject();
        jsonObject.addProperty("KindergartenID",KindergartenID);
        jsonObject.addProperty("OrderBy","id");
        jsonObject.addProperty("ParentID",userId);
        jsonObject.addProperty("PageIndex",pager);
        jsonObject.addProperty("PageSize",10);
        jsonObject.addProperty("Status",0);
        jsonObject.addProperty("SenderType",4);
        jsonObject.addProperty("Ascending",false);
        doPost(Constant.BASE_URL+"api/TeacherMessage/SearchList",jsonObject.toString(),callback);
    }
    /**
     * 提交作业
     * @param callback 回调
     */
    public void doPostJob(PostJob job, KingCallback callback){
        doPost(Constant.BASE_URL+"api/HomeWorkRecord/Add",new Gson().toJson(job),callback);
    }

    /**
     * 提交作业
     * @param callback 回调
     */
    public void doAddDynamic(AddDynamic job, KingCallback callback){
        doPost(Constant.BASE_URL+"api/GrowthDynamics/Add",new Gson().toJson(job),callback);
    }

    public void getUserInfo(String id,KingCallback callback){
        doGet(Constant.BASE_URL+"api/Parent/"+id,callback);
    }
    public void getNoticeInfo(String id,KingCallback callback){
        doGet(Constant.BASE_URL+"api/Notice/"+id,callback);
    }

    public void getClassName(String id,KingCallback callback){
        doGet(Constant.BASE_URL+"api/Class/"+id,callback);
    }

    public void getUserName(String id,String bobyId,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("ParentID",id);
        jsonObject.addProperty("BabyID",bobyId);
        doPost(Constant.BASE_URL+"api/ParentRelation/SearchList",jsonObject.toString(),callback);
    }

    public void getGradeName(String id,KingCallback callback){
        doGet(Constant.BASE_URL+"api/Grade/"+id,callback);
    }
    /**
     * 获取各类新闻接口
     * @param callback 回调
     */
    public void doNews(PostNew news, KingCallback callback){
        if(news.getNoticeType()==5) {
            doPost(Constant.BASE_URL + "api/Notice/GetKindergartenNoticePagedList", new Gson().toJson(news), callback);
        }else if(news.getNoticeType()==6){
            doPost(Constant.BASE_URL + "api/Notice/GetClassNoticePagedList", new Gson().toJson(news), callback);
        }else if(news.getNoticeType()==4){
            doPost(Constant.BASE_URL + "api/Notice/GetParentNoticePagedList", new Gson().toJson(news), callback);
        }
    }
    /**
     * 获取各类新闻接口
     * @param callback 回调
     */
    public void doNews(NewBean news, KingCallback callback){
        if(news.getNoticeType()==5) {
            doPost(Constant.BASE_URL + "api/Notice/GetKindergartenNoticePagedList", new Gson().toJson(news), callback);
        }else if(news.getNoticeType()==6){
            doPost(Constant.BASE_URL + "api/Notice/GetClassNoticePagedList", new Gson().toJson(news), callback);
        }else if(news.getNoticeType()==4){
            doPost(Constant.BASE_URL + "api/Notice/GetParentNoticePagedList", new Gson().toJson(news), callback);
        }
    }
    public void getXT(NewBean news, KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("NoticeType",4);
        jsonObject.addProperty("SubNoticeType",2);
        jsonObject.addProperty("OrderBy","id");
        jsonObject.addProperty("Ascending",false);
        jsonObject.addProperty("PageIndex",news.getPageIndex());
        jsonObject.addProperty("PageSize",5);
        doPost(Constant.BASE_URL+"api/Notice/GetParentNoticePagedList",jsonObject.toString(),callback);
    }
    /**
     * 获取幼儿园信息
     * @param id 幼儿园id
     * @param callback 回调
     */
    public void getKindInfo(String id , KingCallback callback){
        doGet(Constant.BASE_URL+"api/Kindergarten/"+id,callback);
    }


    public void doUserLook(NewsModel.ResultDataBean bean, int userId,int babyId,String userName, KingCallback callback){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        UserLookBean lookBean = new UserLookBean();
        lookBean.setNoticeID(bean.getID());
        lookBean.setReaderID(userId);
        lookBean.setClassID(bean.getReadClassID());
        lookBean.setReaderName(userName);
        lookBean.setBobyID(babyId);
        lookBean.setReadTime(format.format(new Date(System.currentTimeMillis())));
        doPost(Constant.BASE_URL+"api/NoticeReader/Add",new Gson().toJson(lookBean),callback);
    }

    public void getUserLookList(String nId,String babyid, KingCallback callback){
        doGet(Constant.BASE_URL+
                "api/NoticeReader/GetParentInfoByBaby?noticeId="+nId+"&babyId="+babyid,callback);
    }




    public void getBabyQiandao(String date,String card,String classId,String babyId,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("CheckDay",date);
            jsonObject.addProperty("CardNO",card);
            jsonObject.addProperty("ClassID",classId);
            jsonObject.addProperty("BabyID",babyId);
        doPost(Constant.BASE_URL +"api/AttendanceRecord/SearchList",jsonObject.toString(),callback);
    }
    public void getFoodlist(String id,String date,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("KindergartenID",id);
        jsonObject.addProperty("StartTime",date);
        doPost(Constant.BASE_URL +"api/BabyRecipes/SearchList",jsonObject.toString(),callback);
    }

    public void AddFamily(AddFamilyActivity.AddFamily b, KingCallback callback){
        doPost(Constant.BASE_URL +"api/Parent/AddParent",new Gson().toJson(b),callback);
    }

    public void addFamily2(ParentRelation data,KingCallback callback){
        doPost(Constant.BASE_URL +"api/ParentRelation/Add",new Gson().toJson(data),callback);

    }
    public void GetRecord(RecordFragment.GetRecord b, KingCallback callback){
        doPost(Constant.BASE_URL +"api/AttendanceRecord/SearchList",new Gson().toJson(b),callback);
    }

    public void getClasslist(String kid,String classId,String date,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("KindergartenID",kid);
        jsonObject.addProperty("ClassID",classId);
        jsonObject.addProperty("StartTime",date+" 00:00:00");
        jsonObject.addProperty("EndTime",date+" 23:59:00");
        doPost(Constant.BASE_URL +"api/CourseTable/SearchList",jsonObject.toString(),callback);
    }

    /**
     * 修改密码
     * @param account
     * @param old
     * @param newp
     * @param isForce
     * @param callback
     */
    public void updataPwd(String account,String old,String newp,boolean isForce,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("UserAccount",account);
        jsonObject.addProperty("UserOldPassword",old);
        jsonObject.addProperty("UserNewPassword",newp);
        jsonObject.addProperty("IsForce",isForce);
        doPost(Constant.BASE_URL +"api/Parent/ResetPassword",jsonObject.toString(),callback);
    }
    public void upDataBoby(LoginBean.AllBaByBean baByBean,KingCallback callback){
        doPut(Constant.BASE_URL+"api/Baby/"+baByBean.getID(),new Gson().toJson(baByBean),callback);
    }
    /**
     * 获取文件夹
     * <p/>
     * by chenxiu
     * 2015-12-15
     */
    public File getFiles(String path) {
        File file = new File(path);
        //如果目标文件夹不存在则创建
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
    public static final String FILE_URL = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ckind";

    public void getFile(String url,FileDownloadListener aaa) {
        String name = null;
        for(String str : url.split("/")){
            name = str;
        }
        getFiles(FILE_URL);
        FileDownloader.getImpl().create(url).setPath(FILE_URL + name).setListener(aaa).start();
    }

    public void upUser(LoginBean bean , KingCallback callback){
        doPut(Constant.BASE_URL+"api/Parent/"+bean.getID(),new Gson().toJson(bean),callback);
    }

    public void doDeleteMessger(String id,KingCallback callback){

    }
    public void doDeleteDynamic(String id,KingCallback callback){
        doDelete(Constant.BASE_URL+"api/GrowthDynamics/"+id,callback);
    }
    public void doDeletePing(String id,KingCallback callback){
        doDelete(Constant.BASE_URL+"api/GrowthDynamicsComment/"+id,callback);
    }
    public void getJoblist(String GardenerID,String kid,String classId,String date,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("GardenerID",GardenerID);
        jsonObject.addProperty("KindergartenID",kid);
        jsonObject.addProperty("ClassID",classId);
        jsonObject.addProperty("CreateTimeStart",date+" 00:00:00");
        jsonObject.addProperty("CreateTimeEnd",date+" 23:59:00");
        doPost(Constant.BASE_URL +"api/ClassHomeWork/SearchList",jsonObject.toString(),callback);
    }

    public void doPostFeedback(String content,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Content",content);
        jsonObject.addProperty("CreateUserType",1);
        jsonObject.addProperty("CreateUserId",UserManage.getInstance().getUser().getID());
        jsonObject.addProperty("CreateOn",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        doPost(Constant.BASE_URL+"api/Comment/Add",jsonObject.toString(),callback);
    }

    public void getJobInfolist(String BabyID,String OperationTimeStart,String OperationTimeEnd,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Status",true);
        jsonObject.addProperty("BabyID",BabyID);
        jsonObject.addProperty("OperationTimeStart",OperationTimeStart+" 00:00:00");
        jsonObject.addProperty("OperationTimeEnd",OperationTimeEnd+" 23:59:00");
        doPost(Constant.BASE_URL +"api/HomeWorkRecord/SearchList",jsonObject.toString(),callback);
    }
    /**
     * put请求
     * @param url 地址
     * @param json 参数
     * @param callback 回调
     */
    public void doPut(String url,String json,KingCallback callback){
        RequestBody body = RequestBody.create(JSON, json);
        LogUtils.json(json);
        Request.Builder builder = new Request.Builder();
        builder.put(body);
        builder.url(url);
        Request request =  builder.build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public void getCode(String phone,KingCallback callback){

        doPost(Constant.BASE_URL + "api/Parent/GetCheckCode?mobile="+phone,"[]",callback);
    }

    public void onForgetAccount(String Mobile,String UserNewPassword,String CheckCode,KingCallback callback){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Mobile",Mobile);
        jsonObject.addProperty("UserNewPassword",UserNewPassword);
        jsonObject.addProperty("CheckCode",CheckCode);
        doPost(Constant.BASE_URL + "api/Parent/ModifyPassword",jsonObject.toString(),callback);
    }
    /**
     * post请求
     * @param url 地址
     * @param json 参数
     * @param callback 回调
     */
    public void doPost(String url,String json,KingCallback callback){
        RequestBody body = RequestBody.create(JSON, json);
        LogUtils.json(json);
        Request.Builder builder = new Request.Builder();
        builder.post(body);
        builder.url(url);
        Request request =  builder.build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * post请求
     * @param url 地址
     * @param callback 回调
     */
    public void doDelete(String url,KingCallback callback){
        Request.Builder builder = new Request.Builder();
        builder.delete();
        builder.url(url);
        Request request =  builder.build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * get请求
     * @param url 地址
     * @param callback 回调
     */
    public void doGet(String url,KingCallback callback){

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        String token =  BaseApplication.getInstance().getToken();
        Request request =  builder.build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

}
