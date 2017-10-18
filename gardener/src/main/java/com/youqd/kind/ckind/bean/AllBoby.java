package com.youqd.kind.ckind.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chenxiu on 2016/9/24.
 */
public class AllBoby implements Serializable {

    /**
     * ResultData : [{"ID":1,"UserName":"王小宝","EntryDate":"2016-09-08T00:00:00","Photo":"2016/8/23/9e361038925c4e1da9d6fb37dd31dc80.jpg","BornDate":"2012-03-04T10:26:12.233","CardNO":"1952480340","Gender":false,"LearnStatus":true,"Status":true,"ClassID":1,"ClassName":"中二小苹果班","GradeID":1,"KindergartenID":1,"KindergartenName":null,"AttendanceMachineID":1},{"ID":2,"UserName":"张山","EntryDate":"2012-05-06T10:26:12.233","Photo":"2016/8/25/6fec747f2e094ef1867d32705137485d.jpg","BornDate":"2012-05-06T10:26:12.233","CardNO":"1952480341","Gender":false,"LearnStatus":true,"Status":true,"ClassID":1,"ClassName":"中二小苹果班","GradeID":1,"KindergartenID":1,"KindergartenName":null,"AttendanceMachineID":1},{"ID":3,"UserName":"王晓曦","EntryDate":"2012-06-06T10:26:12.233","Photo":"../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png","BornDate":"2012-06-06T10:26:12.233","CardNO":"1952424340","Gender":true,"LearnStatus":true,"Status":true,"ClassID":1,"ClassName":"中二小苹果班","GradeID":1,"KindergartenID":1,"KindergartenName":null,"AttendanceMachineID":0},{"ID":11,"UserName":"王大宝","EntryDate":"2016-06-09T00:00:00","Photo":"../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png","BornDate":"2013-02-01T00:00:00","CardNO":"3258282405","Gender":true,"LearnStatus":true,"Status":true,"ClassID":1,"ClassName":"中二小苹果班","GradeID":1,"KindergartenID":1,"KindergartenName":null,"AttendanceMachineID":0},{"ID":13,"UserName":"王二宝","EntryDate":"2016-06-09T00:00:00","Photo":"2016/8/7/796a458dfc14418089efa7b39e35c7ec.jpg","BornDate":"2013-02-01T00:00:00","CardNO":"1952480341","Gender":true,"LearnStatus":true,"Status":true,"ClassID":1,"ClassName":"中二小苹果班","GradeID":1,"KindergartenID":1,"KindergartenName":null,"AttendanceMachineID":0},{"ID":23,"UserName":"测试","EntryDate":"2016-06-09T00:00:00","Photo":null,"BornDate":"2016-03-04T00:00:00","CardNO":"2436322615","Gender":true,"LearnStatus":true,"Status":true,"ClassID":1,"ClassName":"中二小苹果班","GradeID":1,"KindergartenID":2,"KindergartenName":null,"AttendanceMachineID":1}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 1
     * UserName : 王小宝
     * EntryDate : 2016-09-08T00:00:00
     * Photo : 2016/8/23/9e361038925c4e1da9d6fb37dd31dc80.jpg
     * BornDate : 2012-03-04T10:26:12.233
     * CardNO : 1952480340
     * Gender : false
     * LearnStatus : true
     * Status : true
     * ClassID : 1
     * ClassName : 中二小苹果班
     * GradeID : 1
     * KindergartenID : 1
     * KindergartenName : null
     * AttendanceMachineID : 1
     */

    private List<ResultDataBean> ResultData = new ArrayList<>();

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
        private String UserName;
        private String EntryDate;
        private String Photo;
        private String Images;
        private JobBean jobBean;
        private String Video;

        public String getVideo() {
            return Video;
        }

        public void setVideo(String video) {
            Video = video;
        }

        public JobBean getJobBean() {
            return jobBean;
        }

        public void setJobBean(JobBean jobBean) {
            this.jobBean = jobBean;
        }

        public String getImages() {
            return Images;
        }

        public void setImages(String images) {
            Images = images;
        }
        JobList.ResultDataBean bean;

        public JobList.ResultDataBean getBean() {
            return bean;
        }

        public void setBean(JobList.ResultDataBean bean) {
            this.bean = bean;
        }

        private String BornDate;
        private String CardNO;
        private boolean Gender;
        private boolean LearnStatus;
        private boolean Status;
        private int ClassID;
        private String ClassName;
        private int GradeID;
        private int KindergartenID;
        private Object KindergartenName;
        private int AttendanceMachineID;

        private String tiwen="未晨检";

        private int jiankang=3;

        public String getTiwen() {
            return tiwen;
        }

        public void setTiwen(String tiwen) {
            this.tiwen = tiwen;
        }

        public int getJiankang() {
            return jiankang;
        }

        public void setJiankang(int jiankang) {
            this.jiankang = jiankang;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getEntryDate() {
            return EntryDate;
        }

        public void setEntryDate(String EntryDate) {
            this.EntryDate = EntryDate;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public String getBornDate() {
            return BornDate;
        }

        public void setBornDate(String BornDate) {
            this.BornDate = BornDate;
        }

        public String getCardNO() {
            return CardNO;
        }

        public void setCardNO(String CardNO) {
            this.CardNO = CardNO;
        }

        public boolean isGender() {
            return Gender;
        }

        public void setGender(boolean Gender) {
            this.Gender = Gender;
        }

        public boolean isLearnStatus() {
            return LearnStatus;
        }

        public void setLearnStatus(boolean LearnStatus) {
            this.LearnStatus = LearnStatus;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String ClassName) {
            this.ClassName = ClassName;
        }

        public String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getGradeID() {
            return GradeID;
        }

        public void setGradeID(int GradeID) {
            this.GradeID = GradeID;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public Object getKindergartenName() {
            return KindergartenName;
        }

        public void setKindergartenName(Object KindergartenName) {
            this.KindergartenName = KindergartenName;
        }

        public int getAttendanceMachineID() {
            return AttendanceMachineID;
        }

        public void setAttendanceMachineID(int AttendanceMachineID) {
            this.AttendanceMachineID = AttendanceMachineID;
        }
    }
}
