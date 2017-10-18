package com.youqd.kind.ckind.bean;

/**
 *
 * 新闻实体类
 * Created by Chenxiu on 2016/7/19.
 */
public class NewBean {

    /**
     * Title : sample string 1
     * Content : sample string 2
     * CreateUserID : 1
     * CreateUserName : sample string 3
     * NoticeType : 1
     * SubNoticeType : 1
     * ReadClassID : 1
     * ReadGradeID : 1
     * AgentID : 1
     * KindergartenID : 1
     * CreateTimeStart : 2016-07-19T19:50:22.5336406+08:00
     * CreateTimeEnd : 2016-07-19T19:50:22.5336406+08:00
     * LastUpdateTimeStart : 2016-07-19T19:50:22.5336406+08:00
     * LastUpdateTimeEnd : 2016-07-19T19:50:22.5336406+08:00
     * ParentID : 1
     * ReadUserType : 4
     * PageIndex : 1
     * PageSize : 1
     * ID : 1
     * OrderBy : sample string 5
     * Ascending : true
     */

    private String Title="";
    private String Content="";
    private int CreateUserID=1;
    private String CreateUserName;
    private int NoticeType=1;
    private int SubNoticeType=1;
    private int ReadClassID;
    private int ReadGradeID;
    private int AgentID=1;
    private int KindergartenID;
    private String CreateTimeStart;
    private String CreateTimeEnd;
    private String LastUpdateTimeStart;
    private String LastUpdateTimeEnd;
    private int ParentID;
    private int ReadUserType;
    private int PageIndex;
    private int PageSize;
    private String OrderBy="ID";
    private boolean Ascending=false;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
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

    public int getNoticeType() {
        return NoticeType;
    }

    public void setNoticeType(int NoticeType) {
        this.NoticeType = NoticeType;
    }

    public int getSubNoticeType() {
        return SubNoticeType;
    }

    public void setSubNoticeType(int SubNoticeType) {
        this.SubNoticeType = SubNoticeType;
    }

    public int getReadClassID() {
        return ReadClassID;
    }

    public void setReadClassID(int ReadClassID) {
        this.ReadClassID = ReadClassID;
    }

    public int getReadGradeID() {
        return ReadGradeID;
    }

    public void setReadGradeID(int ReadGradeID) {
        this.ReadGradeID = ReadGradeID;
    }

    public int getAgentID() {
        return AgentID;
    }

    public void setAgentID(int AgentID) {
        this.AgentID = AgentID;
    }

    public int getKindergartenID() {
        return KindergartenID;
    }

    public void setKindergartenID(int KindergartenID) {
        this.KindergartenID = KindergartenID;
    }

    public String getCreateTimeStart() {
        return CreateTimeStart;
    }

    public void setCreateTimeStart(String CreateTimeStart) {
        this.CreateTimeStart = CreateTimeStart;
    }

    public String getCreateTimeEnd() {
        return CreateTimeEnd;
    }

    public void setCreateTimeEnd(String CreateTimeEnd) {
        this.CreateTimeEnd = CreateTimeEnd;
    }

    public String getLastUpdateTimeStart() {
        return LastUpdateTimeStart;
    }

    public void setLastUpdateTimeStart(String LastUpdateTimeStart) {
        this.LastUpdateTimeStart = LastUpdateTimeStart;
    }

    public String getLastUpdateTimeEnd() {
        return LastUpdateTimeEnd;
    }

    public void setLastUpdateTimeEnd(String LastUpdateTimeEnd) {
        this.LastUpdateTimeEnd = LastUpdateTimeEnd;
    }

    public int getParentID() {
        return ParentID;
    }

    public void setParentID(int ParentID) {
        this.ParentID = ParentID;
    }

    public int getReadUserType() {
        return ReadUserType;
    }

    public void setReadUserType(int ReadUserType) {
        this.ReadUserType = ReadUserType;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String OrderBy) {
        this.OrderBy = OrderBy;
    }

    public boolean isAscending() {
        return Ascending;
    }

    public void setAscending(boolean Ascending) {
        this.Ascending = Ascending;
    }
}
