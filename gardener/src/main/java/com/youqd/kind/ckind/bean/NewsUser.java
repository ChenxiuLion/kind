package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/7/22.
 */
public class NewsUser {
    /**
     * ResultData : 2012
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ReusltDataTotal : 3
     */

    private int ResultData;
    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;

    public int getResultData() {
        return ResultData;
    }

    public void setResultData(int ResultData) {
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
