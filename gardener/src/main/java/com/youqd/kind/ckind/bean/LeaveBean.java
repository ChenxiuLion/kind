package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/8/25.
 */
public class LeaveBean {
    /**
     * CheckTime : 2016-08-24
     * CheckType : 4
     * CardNO : sample string 4
     * ClassID : 5
     * BabyID : 6
     * UserName : sample string 7
     * Reason : sample string 9
     */

    private String CheckTime;
    private int CheckType =4 ;
    private String CardNO;
    private int ClassID;
    private int BabyID;
    private String UserName;
    private String Reason;

    public String getCheckTime() {
        return CheckTime;
    }

    public void setCheckTime(String CheckTime) {
        this.CheckTime = CheckTime;
    }

    public int getCheckType() {
        return CheckType;
    }

    public void setCheckType(int CheckType) {
        this.CheckType = CheckType;
    }

    public String getCardNO() {
        return CardNO;
    }

    public void setCardNO(String CardNO) {
        this.CardNO = CardNO;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }

    public int getBabyID() {
        return BabyID;
    }

    public void setBabyID(int BabyID) {
        this.BabyID = BabyID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }
}
