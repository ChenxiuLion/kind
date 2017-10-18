package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/8/14.
 */
public class AddDynamic {
    /**
     * ID : 1
     * Photo : sample string 2
     * Video : sample string 3
     * Description : sample string 4
     * ClassID : 5
     * KindergartenID : 6
     * PraiseNum : 7
     * CreateUserType : 8
     * CreateUserID : 9
     * CreateUserName : sample string 10
     * CreateTime : 2016-08-13T21:40:19.6828593+08:00
     * DeleteFlag : true
     */

    private String Photo;
    private String Video;
    private String Description;
    private int ClassID;
    private int KindergartenID;
    private int PraiseNum;
    private int CreateUserType;
    private int CreateUserID;
    private String CreateUserName;
    private String CreateTime;
    private boolean DeleteFlag;
    private String CreatePhoto;

    public String getCreatePhoto() {
        return CreatePhoto;
    }

    public void setCreatePhoto(String createPhoto) {
        CreatePhoto = createPhoto;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }

    public int getKindergartenID() {
        return KindergartenID;
    }

    public void setKindergartenID(int KindergartenID) {
        this.KindergartenID = KindergartenID;
    }

    public int getPraiseNum() {
        return PraiseNum;
    }

    public void setPraiseNum(int PraiseNum) {
        this.PraiseNum = PraiseNum;
    }

    public int getCreateUserType() {
        return CreateUserType;
    }

    public void setCreateUserType(int CreateUserType) {
        this.CreateUserType = CreateUserType;
    }

    public int getCreateUserID() {
        return CreateUserID;
    }

    public void setCreateUserID(int CreateUserID) {
        this.CreateUserID = CreateUserID;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String CreateUserName) {
        this.CreateUserName = CreateUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public boolean isDeleteFlag() {
        return DeleteFlag;
    }

    public void setDeleteFlag(boolean DeleteFlag) {
        this.DeleteFlag = DeleteFlag;
    }
}
