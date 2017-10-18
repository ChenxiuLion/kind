package com.youqd.kind.ckind.bean;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Chenxiu on 2016/8/7.
 */
public class GetJobInfo {
    /**
     * ResultData : [{"ID":2,"HomeWorkID":1,"HomeWorkTitle":"作业作业作业","Status":true,"BabyID":1,"BabyName":"张山","OperationID":1,"OperationUser":"家长","OperationTime":"2016-07-25T00:00:00","Photo":"sample string 4","Video":"sample string 5","Profile":"宝宝认字有点慢，不过也学会了","Evaluation":null,"EvaluationByID":1,"EvaluationByName":null}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 2
     * HomeWorkID : 1
     * HomeWorkTitle : 作业作业作业
     * Status : true
     * BabyID : 1
     * BabyName : 张山
     * OperationID : 1
     * OperationUser : 家长
     * OperationTime : 2016-07-25T00:00:00
     * Photo : sample string 4
     * Video : sample string 5
     * Profile : 宝宝认字有点慢，不过也学会了
     * Evaluation : null
     * EvaluationByID : 1
     * EvaluationByName : null
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
        private int HomeWorkID;
        private String HomeWorkTitle;
        private boolean Status;
        private int BabyID;
        private String BabyName;
        private int OperationID;
        private String OperationUser="";
        private String OperationTime="";
        private String Photo;
        private String Video;
        private String Profile;
        private String Evaluation="";
        private int EvaluationByID;
        private String EvaluationByName="";
        private String EvaluationByCreateTime;

        public String getEvaluationByCreateTime() {
            return EvaluationByCreateTime;
        }

        public void setEvaluationByCreateTime(String evaluationByCreateTime) {
            EvaluationByCreateTime = evaluationByCreateTime;
        }

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
            if(TextUtils.isEmpty(OperationTime)){
                OperationTime = "";
            }
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
    }
}
