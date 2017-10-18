package com.youqd.kind.ckind.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chenxiu on 2016/8/16.
 */
public class PostMessger {
    /**
     * MessageTitle : sample string 2
     * Content : asdasdasdasd
     * ParentID : 1
     * ParentAccount : sample string 6
     * ParentName : sample string 7
     * ParentPhoto : sample string 8
     * GardenerID : 9
     * GardenerAccount : sample string 10
     * GardenerName : sample string 11
     * GardenerPhoto : sample string 12
     * KindergartenID : 1
     * ClassID : 1
     * CreateTime : 2016-08-07T14:54:41
     * SenderType : 5
     */

    private String Content;
    private int ParentID;
    private String ParentAccount;
    private String ParentName;
    private String ParentPhoto;
    private int GardenerID;
    private String GardenerAccount;
    private String GardenerName;
    private String GardenerPhoto;
    private int KindergartenID;
    private int ClassID;
    private String CreateTime;
    private int SenderType = 4;

    public PostMessger(){
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CreateTime = format.format(new Date());
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int ParentID) {
        this.ParentID = ParentID;
    }

    public String getParentAccount() {
        return ParentAccount;
    }

    public void setParentAccount(String ParentAccount) {
        this.ParentAccount = ParentAccount;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String ParentName) {
        this.ParentName = ParentName;
    }

    public String getParentPhoto() {
        return ParentPhoto;
    }

    public void setParentPhoto(String ParentPhoto) {
        this.ParentPhoto = ParentPhoto;
    }

    public int getGardenerID() {
        return GardenerID;
    }

    public void setGardenerID(int GardenerID) {
        this.GardenerID = GardenerID;
    }

    public String getGardenerAccount() {
        return GardenerAccount;
    }

    public void setGardenerAccount(String GardenerAccount) {
        this.GardenerAccount = GardenerAccount;
    }

    public String getGardenerName() {
        return GardenerName;
    }

    public void setGardenerName(String GardenerName) {
        this.GardenerName = GardenerName;
    }

    public String getGardenerPhoto() {
        return GardenerPhoto;
    }

    public void setGardenerPhoto(String GardenerPhoto) {
        this.GardenerPhoto = GardenerPhoto;
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

    public int getSenderType() {
        return SenderType;
    }

    public void setSenderType(int SenderType) {
        this.SenderType = SenderType;
    }
}
