package com.youqd.kind.ckind.bean;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Chenxiu on 2016/12/16.
 */

public class UpdateBean {
    /**
     * ResultData : [{"ID":2,"AppName":"jiazhang","VersionNum":"1.0.1","MinVersionNum":"1.0.0","DownLoadUrl":null,"VersionDescription":"升级看看 "}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    private List<ResultDataBean> ResultData;

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

    public List<ResultDataBean> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean {
        /**
         * ID : 2
         * AppName : jiazhang
         * VersionNum : 1.0.1
         * MinVersionNum : 1.0.0
         * DownLoadUrl : null
         * VersionDescription : 升级看看
         */

        private int ID;
        private String AppName;
        private String VersionNum;
        private String MinVersionNum;
        private String DownLoadUrl;
        private String VersionDescription;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getAppName() {
            return AppName;
        }

        public void setAppName(String AppName) {
            this.AppName = AppName;
        }

        public int getVersionNum() {
            String num = VersionNum.replace(".","");

            return Integer.parseInt(num);
        }
        public String getVersion() {

            return "V"+VersionNum;
        }
        public void setVersionNum(String VersionNum) {
            this.VersionNum = VersionNum;
        }

        public String getMinVersionNum() {
            return MinVersionNum;
        }

        public void setMinVersionNum(String MinVersionNum) {
            this.MinVersionNum = MinVersionNum;
        }

        public String getDownLoadUrl() {
            if(TextUtils.isEmpty(DownLoadUrl)){
                DownLoadUrl = "http://www.baidu.com";
            }
            return DownLoadUrl;
        }

        public void setDownLoadUrl(String DownLoadUrl) {
            this.DownLoadUrl = DownLoadUrl;
        }

        public String getVersionDescription() {
            return VersionDescription;
        }

        public void setVersionDescription(String VersionDescription) {
            this.VersionDescription = VersionDescription;
        }
    }
}
