package com.youqd.kind.ckind.bean;

/**
 * Created by chenxiu on 2017/8/6.
 */

public class PostNew {


    /**
     * NoticeType : 1
     * SubNoticeType : 1
     * AgentID : 4
     * KindergartenID : 1011
     * PageIndex : 0
     * PageSize : 10
     * OrderBy : id
     * Ascending : true
     */

    private int NoticeType = 5;
    private int SubNoticeType=1;
    private int AgentID;
    private int KindergartenID;
    private int PageIndex;
    private int PageSize;
    private String OrderBy;
    private boolean Ascending;

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
