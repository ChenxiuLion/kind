package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/7/30.
 */
public class PostJob {

    /**
     * ID : 1
     * HomeWorkID : 1
     * HomeWorkTitle : sample string 2
     * Status : true
     * BabyID : 3
     * BabyName : sample string 4
     * OperationID : 1
     * OperationUser : sample string 5
     * OperationTime : 2016-08-09T20:21:59.9171328+08:00
     * Photo : sample string 6
     * Video : sample string 7
     * Profile : sample string 8
     * Evaluation : sample string 9
     * EvaluationByID : 1
     * EvaluationByName : sample string 10
     */

    private int ID;
    private int HomeWorkID;
    private String HomeWorkTitle;
    private boolean Status;
    private int BabyID;
    private String BabyName;
    private int OperationID;
    private String OperationUser;
    private String OperationTime;
    private String Photo;
    private String Video;
    private String Profile;
    private String Evaluation;
    private int EvaluationByID;
    private String EvaluationByName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHomeWorkID() {
        return HomeWorkID;
    }

    public void setHomeWorkID(int HomeWorkID) {
        this.HomeWorkID = HomeWorkID;
    }

    public String getHomeWorkTitle() {
        return HomeWorkTitle;
    }

    public void setHomeWorkTitle(String HomeWorkTitle) {
        this.HomeWorkTitle = HomeWorkTitle;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public int getBabyID() {
        return BabyID;
    }

    public void setBabyID(int BabyID) {
        this.BabyID = BabyID;
    }

    public String getBabyName() {
        return BabyName;
    }

    public void setBabyName(String BabyName) {
        this.BabyName = BabyName;
    }

    public int getOperationID() {
        return OperationID;
    }

    public void setOperationID(int OperationID) {
        this.OperationID = OperationID;
    }

    public String getOperationUser() {
        return OperationUser;
    }

    public void setOperationUser(String OperationUser) {
        this.OperationUser = OperationUser;
    }

    public String getOperationTime() {
        return OperationTime;
    }

    public void setOperationTime(String OperationTime) {
        this.OperationTime = OperationTime;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String Video) {
        this.Video = Video;
    }

    public String getProfile() {
        return Profile;
    }

    public void setProfile(String Profile) {
        this.Profile = Profile;
    }

    public String getEvaluation() {
        return Evaluation;
    }

    public void setEvaluation(String Evaluation) {
        this.Evaluation = Evaluation;
    }

    public int getEvaluationByID() {
        return EvaluationByID;
    }

    public void setEvaluationByID(int EvaluationByID) {
        this.EvaluationByID = EvaluationByID;
    }

    public String getEvaluationByName() {
        return EvaluationByName;
    }

    public void setEvaluationByName(String EvaluationByName) {
        this.EvaluationByName = EvaluationByName;
    }
}
