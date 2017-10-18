package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/16.
 */
public class LoginBean {


    /**
     * ID : 1
     * UserName : sample string 2
     * UserAccount : sample string 3
     * UserPassword : sample string 4
     * Mobile : sample string 5
     * IMEI : sample string 6
     * Photo : sample string 7
     * Status : true
     * VideoStartTime : 2016-07-24T10:57:28.1537188+08:00
     * VideoEndTime : 2016-07-24T10:57:28.1537188+08:00
     * LastLoginTime : 2016-07-24T10:57:28.1537188+08:00
     * KindergartenID : 12
     * MasterParent : 13
     * IsSuccess : true
     * Ticket : sample string 15
     * AllBaBy : [{"ID":1,"UserName":"sample string 2","EntryDate":"2016-07-24T10:57:28.1556719+08:00","Photo":"sample string 4","BornDate":"2016-07-24T10:57:28.1556719+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"GradeID":10,"KindergartenID":11,"AttendanceMachineID":12},{"ID":1,"UserName":"sample string 2","EntryDate":"2016-07-24T10:57:28.1556719+08:00","Photo":"sample string 4","BornDate":"2016-07-24T10:57:28.1556719+08:00","CardNO":"sample string 6","LearnStatus":true,"Status":true,"ClassID":9,"GradeID":10,"KindergartenID":11,"AttendanceMachineID":12}]
     */

    private int ID;
    private String UserName;
    private String UserAccount;

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
    private String Relation;

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String relation) {
        Relation = relation;
    }

    /**
     * ID : 1
     * UserName : sample string 2
     * EntryDate : 2016-07-24T10:57:28.1556719+08:00
     * Photo : sample string 4
     * BornDate : 2016-07-24T10:57:28.1556719+08:00
     * CardNO : sample string 6
     * LearnStatus : true
     * Status : true
     * ClassID : 9
     * GradeID : 10
     * KindergartenID : 11
     * AttendanceMachineID : 12
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
        private int GradeID;
        private int KindergartenID;
        private int AttendanceMachineID;
        private String schoolName;

        public String getClassName() {
            return ClassName;
        }

        public void setClassName(String className) {
            ClassName = className;
        }

        private String ClassName;
        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
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
