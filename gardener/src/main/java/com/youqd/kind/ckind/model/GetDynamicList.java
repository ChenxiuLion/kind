package com.youqd.kind.ckind.model;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.youqd.kind.ckind.bean.GetPingList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenxiu on 2016/8/14.
 */
public class GetDynamicList {
    /**
     * ResultData : [{"ID":10,"Photo":"","Video":"[]","Description":"测试一下发布动态吧","ClassID":1,"KindergartenID":1,"PraiseNum":0,"CreateUserType":1,"CreateUserID":1,"CreateUserName":"1王小宝爸爸","CreateTime":"2016-08-14T00:42:12.953","DeleteFlag":false}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 10
     * Photo :
     * Video : []
     * Description : 测试一下发布动态吧
     * ClassID : 1
     * KindergartenID : 1
     * PraiseNum : 0
     * CreateUserType : 1
     * CreateUserID : 1
     * CreateUserName : 1王小宝爸爸
     * CreateTime : 2016-08-14T00:42:12.953
     * DeleteFlag : false
     */

    private List<ResultDataBean> ResultData;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String ResultMessage) {
        this.ResultMessage = ResultMessage;
    }

    public int getReusltDataTotal() {
        return ReusltDataTotal;
    }

    public void setReusltDataTotal(int ReusltDataTotal) {
        this.ReusltDataTotal = ReusltDataTotal;
    }

    public List<ResultDataBean> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean {
        private int ID;
        private String Photo;
        private String Video;
        private String Description;
        private int ClassID;
        private int KindergartenID;
        private int PraiseNum;
        private int CreateUserType;
        private int CreateUserID;
        private String CreateUserName;
        private String CreateTime;
        private boolean DeleteFlag;

        private List<GetPingList.ResultDataBean> Pings  = new ArrayList<>();

        /**
         * 获取评论列表
         * @return
         */
        public List<GetPingList.ResultDataBean> getPings() {
            if(Pings==null){
                Pings  = new ArrayList<>();
            }
            List<GetPingList.ResultDataBean> ping = new ArrayList<>();
            for(GetPingList.ResultDataBean bean : Pings){
                if(TextUtils.isEmpty(bean.getTitle()) || bean.getTitle().equals("0")){
                    ping.add(bean);
                }
            }
            Logger.e("ping\n"+new Gson().toJson(ping));
            return ping;
        }

        public void addPing(GetPingList.ResultDataBean bean){
            Pings.add(bean);
        }

        /**
         * 获取当前用户是否点赞
         * @return
         */
        public boolean isZan(String userAccount){
            for(GetPingList.ResultDataBean bean : getZans()){
                if(bean.getCommentUserAccount().equals(userAccount)){
                    return true;
                }
            }

            return false;
        }

        /**
         * 获取点赞列表
         * @return
         */
        public List<GetPingList.ResultDataBean> getZans() {
            if(Pings==null){
                Pings  = new ArrayList<>();
            }
            List<GetPingList.ResultDataBean> ping = new ArrayList<>();
            for(GetPingList.ResultDataBean bean : Pings){
                if(!TextUtils.isEmpty(bean.getTitle())) {
                    if (bean.getTitle().equals("1")) {
                        ping.add(bean);
                    }
                }
            }
            Logger.e("zan\n"+new Gson().toJson(ping));
            return ping;
        }

        public void setPings(List<GetPingList.ResultDataBean> pings) {
            Pings = pings;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public String getVideo() {
            return Video;
        }

        public void setVideo(String Video) {
            this.Video = Video;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public int getPraiseNum() {
            return PraiseNum;
        }

        public void setPraiseNum(int PraiseNum) {
            this.PraiseNum = PraiseNum;
        }

        public int getCreateUserType() {
            return CreateUserType;
        }

        public void setCreateUserType(int CreateUserType) {
            this.CreateUserType = CreateUserType;
        }

        public int getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(int CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public boolean isDeleteFlag() {
            return DeleteFlag;
        }

        public void setDeleteFlag(boolean DeleteFlag) {
            this.DeleteFlag = DeleteFlag;
        }
    }
}
