package com.youqd.kind.ckind.model;

/**
 * Created by Chenxiu on 2016/8/25.
 */
public class LeaveResult {
    /**
     * ResultCode : 0
     * ResultMessage : 未找到考勤卡对应的宝宝
     * ResultData : null
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private Object ResultData;
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

    public Object getResultData() {
        return ResultData;
    }

    public void setResultData(Object ResultData) {
        this.ResultData = ResultData;
    }

    public int getReusltDataTotal() {
        return ReusltDataTotal;
    }

    public void setReusltDataTotal(int ReusltDataTotal) {
        this.ReusltDataTotal = ReusltDataTotal;
    }
}
