package com.youqd.kind.ckind.model;

import java.io.Serializable;

/**
 * Created by Chenxiu on 2016/8/14.
 */
public class Ping implements Serializable {
    /**
     * ID : 1
     * GrowthDynamicsID : 2
     * Title : sample string 3
     * CommentContent : sample string 4
     * CreateTime : sample string 5
     * CommentUserAccount : sample string 6
     * CommentUserName : sample string 7
     * DeleteFlag : true
     */
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private int GrowthDynamicsID;
    private String Title;
    private String CommentContent;
    private String CreateTime;
    private String CommentUserAccount;
    private String CommentUserName;
    private boolean DeleteFlag;

    public int getGrowthDynamicsID() {
        return GrowthDynamicsID;
    }

    public void setGrowthDynamicsID(int GrowthDynamicsID) {
        this.GrowthDynamicsID = GrowthDynamicsID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String CommentContent) {
        this.CommentContent = CommentContent;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getCommentUserAccount() {
        return CommentUserAccount;
    }

    public void setCommentUserAccount(String CommentUserAccount) {
        this.CommentUserAccount = CommentUserAccount;
    }

    public String getCommentUserName() {
        return CommentUserName;
    }

    public void setCommentUserName(String CommentUserName) {
        this.CommentUserName = CommentUserName;
    }

    public boolean isDeleteFlag() {
        return DeleteFlag;
    }

    public void setDeleteFlag(boolean DeleteFlag) {
        this.DeleteFlag = DeleteFlag;
    }
}
