package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/23.
 */
public class UserLookList {

    /**
     * ResultData : [{"ID":1,"UserName":"sample string 2","EntryDate":"2016-09-24T16:28:34.0732421+08:00","Photo":"sample string 4","BornDate":"2016-09-24T16:28:34.0732421+08:00","CardNO":"sample string 6","Gender":true,"LearnStatus":true,"Status":true,"ClassID":10,"ClassName":"sample string 11","GradeID":12,"KindergartenID":13,"KindergartenName":"sample string 14","AttendanceMachineID":15},{"ID":1,"UserName":"sample string 2","EntryDate":"2016-09-24T16:28:34.0732421+08:00","Photo":"sample string 4","BornDate":"2016-09-24T16:28:34.0732421+08:00","CardNO":"sample string 6","Gender":true,"LearnStatus":true,"Status":true,"ClassID":10,"ClassName":"sample string 11","GradeID":12,"KindergartenID":13,"KindergartenName":"sample string 14","AttendanceMachineID":15}]
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ReusltDataTotal : 3
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 1
     * UserName : sample string 2
     * EntryDate : 2016-09-24T16:28:34.0732421+08:00
     * Photo : sample string 4
     * BornDate : 2016-09-24T16:28:34.0732421+08:00
     * CardNO : sample string 6
     * Gender : true
     * LearnStatus : true
     * Status : true
     * ClassID : 10
     * ClassName : sample string 11
     * GradeID : 12
     * KindergartenID : 13
     * KindergartenName : sample string 14
     * AttendanceMachineID : 15
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
        private String UserName;
        private String EntryDate;
        private String Photo;
        private String BornDate;
        private String CardNO;
        private boolean Gender;
        private boolean LearnStatus;
        private boolean Status;
        private int ClassID;
        private String ClassName;
        private int GradeID;
        private int KindergartenID;
        private String KindergartenName;
        private int AttendanceMachineID;

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

        public String getKindergartenName() {
            return KindergartenName;
        }

        public void setKindergartenName(String KindergartenName) {
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
