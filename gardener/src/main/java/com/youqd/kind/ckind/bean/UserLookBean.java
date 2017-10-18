package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/7/22.
 */
public class UserLookBean {
    /**
     * ID : 1
     * NoticeID : 2
     * ReaderID : 3
     * ClassID : 4
     * ReaderName : sample string 5
     * ReadTime : 2016-07-22T22:16:22.8205547+08:00
     */

    private int NoticeID;
    private int ReaderID;
    private int ClassID;
    private int BabyID;

    public int getBobyID() {
        return BabyID;
    }

    public void setBobyID(int bobyID) {
        this.BabyID = bobyID;
    }

    private String ReaderName;
    private String ReadTime;


    public int getNoticeID() {
        return NoticeID;
    }

    public void setNoticeID(int NoticeID) {
        this.NoticeID = NoticeID;
    }

    public int getReaderID() {
        return ReaderID;
    }

    public void setReaderID(int ReaderID) {
        this.ReaderID = ReaderID;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int ClassID) {
        this.ClassID = ClassID;
    }

    public String getReaderName() {
        return ReaderName;
    }

    public void setReaderName(String ReaderName) {
        this.ReaderName = ReaderName;
    }

    public String getReadTime() {
        return ReadTime;
    }

    public void setReadTime(String ReadTime) {
        this.ReadTime = ReadTime;
    }
}
