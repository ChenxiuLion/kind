package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/8/6.
 */
public class UpdataBoby {
    /**
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ResultData : true
     * ReusltDataTotal : 3
     */

    private int ResultCode;
    private String ResultMessage;
    private boolean ResultData;
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

    public boolean isResultData() {
        return ResultData;
    }

    public void setResultData(boolean ResultData) {
        this.ResultData = ResultData;
    }

    public int getReusltDataTotal() {
        return ReusltDataTotal;
    }

    public void setReusltDataTotal(int ReusltDataTotal) {
        this.ReusltDataTotal = ReusltDataTotal;
    }
}
