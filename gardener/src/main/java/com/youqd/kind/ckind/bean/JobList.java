package com.youqd.kind.ckind.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youqd.kind.ckind.model.KingVideos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Chenxiu on 2016/10/18.
 */
public class JobList  implements Serializable {
    /**
     * ResultData : [{"ID":5067,"HomeWorkID":6045,"HomeWorkTitle":"作业加起来","Status":true,"BabyID":1,"BabyName":"王小宝","OperationID":2031,"OperationUser":"王小宝的爸爸","OperationTime":"2016-10-18T00:00:00","Photo":"[\"2016/10/18/6cfa57f22110434cb9b4db7841a524fb.jpg\",\"2016/10/18/b440025e52cd45878af9a9a57d8a6fe3.jpg\"]","Video":"[]","Profile":"哦婆婆QQ磨破","Evaluation":null,"EvaluationByID":0,"EvaluationByName":null,"EvaluationByCreateTime":null}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 5067
     * HomeWorkID : 6045
     * HomeWorkTitle : 作业加起来
     * Status : true
     * BabyID : 1
     * BabyName : 王小宝
     * OperationID : 2031
     * OperationUser : 王小宝的爸爸
     * OperationTime : 2016-10-18T00:00:00
     * Photo : ["2016/10/18/6cfa57f22110434cb9b4db7841a524fb.jpg","2016/10/18/b440025e52cd45878af9a9a57d8a6fe3.jpg"]
     * Video : []
     * Profile : 哦婆婆QQ磨破
     * Evaluation : null
     * EvaluationByID : 0
     * EvaluationByName : null
     * EvaluationByCreateTime : null
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

    public static class ResultDataBean implements Serializable {
        private int ID;
        private int HomeWorkID;
        private String HomeWorkTitle;
        private boolean Status;
        private int BabyID;
        private String BabyName;
        private int OperationID;
        private String OperationUser;
        private String OperationTime;
        private String Photo;
        private String Video;
        private String Profile;
        private String Evaluation;
        private int EvaluationByID;
        private String EvaluationByName;
        private String EvaluationByCreateTime;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getHomeWorkID() {
            return HomeWorkID;
        }

        public void setHomeWorkID(int HomeWorkID) {
            this.HomeWorkID = HomeWorkID;
        }

        public String getHomeWorkTitle() {
            return HomeWorkTitle;
        }

        public void setHomeWorkTitle(String HomeWorkTitle) {
            this.HomeWorkTitle = HomeWorkTitle;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public int getBabyID() {
            return BabyID;
        }

        public void setBabyID(int BabyID) {
            this.BabyID = BabyID;
        }

        public String getBabyName() {
            return BabyName;
        }

        public void setBabyName(String BabyName) {
            this.BabyName = BabyName;
        }

        public int getOperationID() {
            return OperationID;
        }

        public void setOperationID(int OperationID) {
            this.OperationID = OperationID;
        }

        public String getOperationUser() {
            return OperationUser;
        }

        public void setOperationUser(String OperationUser) {
            this.OperationUser = OperationUser;
        }

        public String getOperationTime() {
            return OperationTime;
        }

        public void setOperationTime(String OperationTime) {
            this.OperationTime = OperationTime;
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

        public String getProfile() {
            return Profile;
        }

        public List<String> getOkImagePath(){
            List<String> strings = new Gson().fromJson(Photo,
                    new TypeToken<List<String>>(){}.getType());
            return strings;
        }
        public List<KingVideos> getOkVideoPath(){
            List<KingVideos> strings = new Gson().fromJson(Video,
                    new TypeToken<List<KingVideos>>(){}.getType());
            return strings;
        }
        public void setProfile(String Profile) {
            this.Profile = Profile;
        }

        public String getEvaluation() {
            return Evaluation;
        }

        public void setEvaluation(String Evaluation) {
            this.Evaluation = Evaluation;
        }

        public int getEvaluationByID() {
            return EvaluationByID;
        }

        public void setEvaluationByID(int EvaluationByID) {
            this.EvaluationByID = EvaluationByID;
        }

        public String getEvaluationByName() {
            return EvaluationByName;
        }

        public void setEvaluationByName(String EvaluationByName) {
            this.EvaluationByName = EvaluationByName;
        }

        public String getEvaluationByCreateTime() {
            return EvaluationByCreateTime;
        }

        public void setEvaluationByCreateTime(String EvaluationByCreateTime) {
            this.EvaluationByCreateTime = EvaluationByCreateTime;
        }
    }
}
