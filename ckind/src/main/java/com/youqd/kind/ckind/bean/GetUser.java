package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/7/22.
 */
public class GetUser {
    /**
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ReusltDataTotal : 3
     */

    private LoginBean ResultData;

    public LoginBean getResultData() {
        return ResultData;
    }

    public void setResultData(LoginBean resultData) {
        ResultData = resultData;
    }

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;

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
