package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/7/23.
 */
public class GetCheckList {
    /**
     * CheckDay : 2016-07-23
     * CardNO : 0745316644
     * ClassID : 1
     * BabyID : 1
     */

    private String CheckDay;
    private String CardNO;
    private int ClassID;
    private int BabyID;

    public String getCheckDay() {
        return CheckDay;
    }

    public void setCheckDay(String CheckDay) {
        this.CheckDay = CheckDay;
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
}
