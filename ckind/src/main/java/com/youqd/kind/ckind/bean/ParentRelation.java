package com.youqd.kind.ckind.bean;

/**
 * Created by chenxiu on 2017/8/6.
 */

public class ParentRelation
{
    /**
     * Relation : 叔叔
     * BabyID : 41
     * ParentID : 9041
     * ParentName : 宝宝+称呼
     */

    private String Relation;
    private int BabyID;
    private int ParentID;
    private String ParentName;

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String Relation) {
        this.Relation = Relation;
    }

    public int getBabyID() {
        return BabyID;
    }

    public void setBabyID(int BabyID) {
        this.BabyID = BabyID;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int ParentID) {
        this.ParentID = ParentID;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String ParentName) {
        this.ParentName = ParentName;
    }
}
