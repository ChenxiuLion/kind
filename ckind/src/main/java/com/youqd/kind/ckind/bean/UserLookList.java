package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/23.
 */
public class UserLookList {


    /**
     * ResultData : [{"ID":1,"UserName":"sample string 2","UserAccount":"sample string 3","UserPassword":"sample string 4","Mobile":"sample string 5","IMEI":"sample string 6","Photo":"sample string 7","Status":true,"VideoStartTime":"2016-08-10T22:36:25.3429141+08:00","VideoEndTime":"2016-08-10T22:36:25.3429141+08:00","LastLoginTime":"2016-08-10T22:36:25.3429141+08:00","KindergartenID":12,"MasterParent":13,"IsSuccess":true,"Ticket":"sample string 15","AllBaBy":[{"ID":1,"UserName":"sample string 2","EntryDate":"2016-08-10T22:36:25.3429141+08:00","Photo":"sample string 4","BornDate":"2016-08-10T22:36:25.3429141+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"ClassName":"sample string 10","GradeID":11,"KindergartenID":12,"AttendanceMachineID":13},{"ID":1,"UserName":"sample string 2","EntryDate":"2016-08-10T22:36:25.3429141+08:00","Photo":"sample string 4","BornDate":"2016-08-10T22:36:25.3429141+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"ClassName":"sample string 10","GradeID":11,"KindergartenID":12,"AttendanceMachineID":13}]},{"ID":1,"UserName":"sample string 2","UserAccount":"sample string 3","UserPassword":"sample string 4","Mobile":"sample string 5","IMEI":"sample string 6","Photo":"sample string 7","Status":true,"VideoStartTime":"2016-08-10T22:36:25.3429141+08:00","VideoEndTime":"2016-08-10T22:36:25.3429141+08:00","LastLoginTime":"2016-08-10T22:36:25.3429141+08:00","KindergartenID":12,"MasterParent":13,"IsSuccess":true,"Ticket":"sample string 15","AllBaBy":[{"ID":1,"UserName":"sample string 2","EntryDate":"2016-08-10T22:36:25.3429141+08:00","Photo":"sample string 4","BornDate":"2016-08-10T22:36:25.3429141+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"ClassName":"sample string 10","GradeID":11,"KindergartenID":12,"AttendanceMachineID":13},{"ID":1,"UserName":"sample string 2","EntryDate":"2016-08-10T22:36:25.3429141+08:00","Photo":"sample string 4","BornDate":"2016-08-10T22:36:25.3429141+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"ClassName":"sample string 10","GradeID":11,"KindergartenID":12,"AttendanceMachineID":13}]}]
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
     * UserAccount : sample string 3
     * UserPassword : sample string 4
     * Mobile : sample string 5
     * IMEI : sample string 6
     * Photo : sample string 7
     * Status : true
     * VideoStartTime : 2016-08-10T22:36:25.3429141+08:00
     * VideoEndTime : 2016-08-10T22:36:25.3429141+08:00
     * LastLoginTime : 2016-08-10T22:36:25.3429141+08:00
     * KindergartenID : 12
     * MasterParent : 13
     * IsSuccess : true
     * Ticket : sample string 15
     * AllBaBy : [{"ID":1,"UserName":"sample string 2","EntryDate":"2016-08-10T22:36:25.3429141+08:00","Photo":"sample string 4","BornDate":"2016-08-10T22:36:25.3429141+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"ClassName":"sample string 10","GradeID":11,"KindergartenID":12,"AttendanceMachineID":13},{"ID":1,"UserName":"sample string 2","EntryDate":"2016-08-10T22:36:25.3429141+08:00","Photo":"sample string 4","BornDate":"2016-08-10T22:36:25.3429141+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"ClassName":"sample string 10","GradeID":11,"KindergartenID":12,"AttendanceMachineID":13}]
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
        private String Photo;
        private boolean Status;
        private String VideoStartTime;
        private String VideoEndTime;
        private String LastLoginTime;
        private int KindergartenID;
        private int MasterParent;
        private boolean IsSuccess;
        private String Ticket;
        /**
         * ID : 1
         * UserName : sample string 2
         * EntryDate : 2016-08-10T22:36:25.3429141+08:00
         * Photo : sample string 4
         * BornDate : 2016-08-10T22:36:25.3429141+08:00
         * CardNO : sample string 6
         * LearnStatus : true
         * Status : true
         * ClassID : 9
         * ClassName : sample string 10
         * GradeID : 11
         * KindergartenID : 12
         * AttendanceMachineID : 13
         */

        private List<AllBaByBean> AllBaBy;

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

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public String getVideoStartTime() {
            return VideoStartTime;
        }

        public void setVideoStartTime(String VideoStartTime) {
            this.VideoStartTime = VideoStartTime;
        }

        public String getVideoEndTime() {
            return VideoEndTime;
        }

        public void setVideoEndTime(String VideoEndTime) {
            this.VideoEndTime = VideoEndTime;
        }

        public String getLastLoginTime() {
            return LastLoginTime;
        }

        public void setLastLoginTime(String LastLoginTime) {
            this.LastLoginTime = LastLoginTime;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public int getMasterParent() {
            return MasterParent;
        }

        public void setMasterParent(int MasterParent) {
            this.MasterParent = MasterParent;
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

        public List<AllBaByBean> getAllBaBy() {
            return AllBaBy;
        }

        public void setAllBaBy(List<AllBaByBean> AllBaBy) {
            this.AllBaBy = AllBaBy;
        }

        public static class AllBaByBean {
            private int ID;
            private String UserName;
            private String EntryDate;
            private String Photo;
            private String BornDate;
            private String CardNO;
            private boolean LearnStatus;
            private boolean Status;
            private int ClassID;
            private String ClassName;
            private int GradeID;
            private int KindergartenID;
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

            public int getAttendanceMachineID() {
                return AttendanceMachineID;
            }

            public void setAttendanceMachineID(int AttendanceMachineID) {
                this.AttendanceMachineID = AttendanceMachineID;
            }
        }
    }
}
