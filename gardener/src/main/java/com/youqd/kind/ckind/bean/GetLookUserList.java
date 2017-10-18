package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/7/23.
 */
public class GetLookUserList {
    /**
     * NoticeID : 1
     * ClassID : 1
     * PageIndex : 1
     * PageSize : 1
     * ID : 1
     * OrderBy : sample string 1
     * Ascending : true
     */

    private int NoticeID;
    private int ClassID;
    private int PageIndex;
    private int PageSize;
    private int ID;
    private String OrderBy;
    private boolean Ascending;

    public int getNoticeID() {
        return NoticeID;
    }

    public void setNoticeID(int NoticeID) {
        this.NoticeID = NoticeID;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
