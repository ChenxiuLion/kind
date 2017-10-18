package com.youqd.kind.ckind.bean;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/27.
 */
public class GetJob {
    /**
     * ResultData : [{"ID":7,"HomeWorkTitle":"回家把3写300遍","Content":"回家把1写100遍，拍照上传提交作业！！！","Photo":null,"PeriodsType":0,"GardenerID":1,"KindergartenID":1,"ClassID":1,"CreateTime":"2016-07-27T00:00:00","StartTime":"2016-07-27 00:00:00.000","EndTime":"2016-07-27 00:00:00.000"}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 7
     * HomeWorkTitle : 回家把3写300遍
     * Content : 回家把1写100遍，拍照上传提交作业！！！
     * Photo : null
     * PeriodsType : 0
     * GardenerID : 1
     * KindergartenID : 1
     * ClassID : 1
     * CreateTime : 2016-07-27T00:00:00
     * StartTime : 2016-07-27 00:00:00.000
     * EndTime : 2016-07-27 00:00:00.000
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
        private String HomeWorkTitle;
        private String Content;
        private String Photo;
        private int PeriodsType;
        private int GardenerID;
        private int KindergartenID;
        private int ClassID;
        private String CreateTime;
        private String StartTime;
        private String EndTime;
        private String Status;

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getHomeWorkTitle() {
            return HomeWorkTitle;
        }

        public void setHomeWorkTitle(String HomeWorkTitle) {
            this.HomeWorkTitle = HomeWorkTitle;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getPhoto() {
            if(TextUtils.isEmpty(Photo)){
                return "";
            }
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public int getPeriodsType() {
            return PeriodsType;
        }

        public void setPeriodsType(int PeriodsType) {
            this.PeriodsType = PeriodsType;
        }

        public int getGardenerID() {
            return GardenerID;
        }

        public void setGardenerID(int GardenerID) {
            this.GardenerID = GardenerID;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }
    }
}
