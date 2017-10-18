package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/10/15.
 */
public class AddWokr {
    /**
     * ID : 1
     * HomeWorkTitle : sample string 2
     * Content : sample string 3
     * Photo : sample string 4
     * PeriodsType : 5
     * GardenerID : 6
     * KindergartenID : 7
     * ClassID : 8
     * CreateTime : 2016-10-15T10:40:26.9200625+08:00
     * StartTime : sample string 10
     * EndTime : sample string 11
     */

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
