package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/8/18.
 */
public class GetNews {
    /**
     * ID : 1
     * Title : sample string 2
     * Content : sample string 3
     * Summary : sample string 4
     * MediaURL : sample string 5
     * ImageURL : sample string 6
     * CreateUserID : 7
     * CreateUserName : sample string 8
     * CreateTime : 2016-08-18T23:20:52.5597109+08:00
     * NoticeType : 10
     * SubNoticeType : 11
     * ReadClassID : 12
     * ReadClassName : sample string 13
     * ReadGradeID : 14
     * AgentID : 15
     * KindergartenID : 16
     * LastUpdateUserID : 17
     * LastUpdateUserName : sample string 18
     * LastUpdateTime : 2016-08-18T23:20:52.5597109+08:00
     * CreateUserType : 20
     */

    private NewsModel.ResultDataBean ResultData;
    /**
     * ResultData : {"ID":1,"Title":"sample string 2","Content":"sample string 3","Summary":"sample string 4","MediaURL":"sample string 5","ImageURL":"sample string 6","CreateUserID":7,"CreateUserName":"sample string 8","CreateTime":"2016-08-18T23:20:52.5597109+08:00","NoticeType":10,"SubNoticeType":11,"ReadClassID":12,"ReadClassName":"sample string 13","ReadGradeID":14,"AgentID":15,"KindergartenID":16,"LastUpdateUserID":17,"LastUpdateUserName":"sample string 18","LastUpdateTime":"2016-08-18T23:20:52.5597109+08:00","CreateUserType":20}
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ReusltDataTotal : 3
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;

    public NewsModel.ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(NewsModel.ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultMessage() {
        return ResultMessage;
    }

    public void setResultMessage(String ResultMessage) {
        this.ResultMessage = ResultMessage;
    }

    public int getReusltDataTotal() {
        return ReusltDataTotal;
    }

    public void setReusltDataTotal(int ReusltDataTotal) {
        this.ReusltDataTotal = ReusltDataTotal;
    }
}
