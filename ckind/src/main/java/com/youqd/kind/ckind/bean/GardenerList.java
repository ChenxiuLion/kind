package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/31.
 */
public class GardenerList {
    /**
     * ResultData : [{"ID":1,"UserName":"张老师","UserAccount":"19999999999","UserPassword":null,"Mobile":"13523047743","IMEI":null,"LastLoginTime":"2016-05-07T22:38:55.87","Photo":null,"Introduce":null,"KindergartenID":1,"ClassID":1,"IsSuccess":false,"Ticket":null},{"ID":2,"UserName":"李老师","UserAccount":"11111","UserPassword":null,"Mobile":"10086","IMEI":null,"LastLoginTime":"2016-08-09T00:00:00","Photo":null,"Introduce":null,"KindergartenID":1,"ClassID":1,"IsSuccess":false,"Ticket":null},{"ID":1003,"UserName":"王老师","UserAccount":"1234","UserPassword":null,"Mobile":"10000","IMEI":null,"LastLoginTime":"2016-09-01T00:00:00","Photo":null,"Introduce":null,"KindergartenID":1,"ClassID":1,"IsSuccess":false,"Ticket":null},{"ID":1004,"UserName":"陈老师","UserAccount":"12345","UserPassword":null,"Mobile":"10010","IMEI":null,"LastLoginTime":"2016-08-21T00:00:00","Photo":null,"Introduce":null,"KindergartenID":1,"ClassID":1,"IsSuccess":false,"Ticket":null}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 1
     * UserName : 张老师
     * UserAccount : 19999999999
     * UserPassword : null
     * Mobile : 13523047743
     * IMEI : null
     * LastLoginTime : 2016-05-07T22:38:55.87
     * Photo : null
     * Introduce : null
     * KindergartenID : 1
     * ClassID : 1
     * IsSuccess : false
     * Ticket : null
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
        private String UserAccount;
        private String UserPassword;
        private String Mobile;
        private String IMEI;
        private String LastLoginTime;
        private String Photo;
        private String Introduce;
        private int KindergartenID;

        public String getPostTitle() {
            return PostTitle;
        }

        public void setPostTitle(String postTitle) {
            PostTitle = postTitle;
        }

        private String PostTitle;
        private int ClassID;
        private boolean IsSuccess;
        private String Ticket;

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

        public String getUserAccount() {
            return UserAccount;
        }

        public void setUserAccount(String UserAccount) {
            this.UserAccount = UserAccount;
        }

        public String getUserPassword() {
            return UserPassword;
        }

        public void setUserPassword(String UserPassword) {
            this.UserPassword = UserPassword;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getIMEI() {
            return IMEI;
        }

        public void setIMEI(String IMEI) {
            this.IMEI = IMEI;
        }

        public String getLastLoginTime() {
            return LastLoginTime;
        }

        public void setLastLoginTime(String LastLoginTime) {
            this.LastLoginTime = LastLoginTime;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public String getIntroduce() {
            return Introduce;
        }

        public void setIntroduce(String Introduce) {
            this.Introduce = Introduce;
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

        public boolean isIsSuccess() {
            return IsSuccess;
        }

        public void setIsSuccess(boolean IsSuccess) {
            this.IsSuccess = IsSuccess;
        }

        public String getTicket() {
            return Ticket;
        }

        public void setTicket(String Ticket) {
            this.Ticket = Ticket;
        }
    }
}
