package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/8/14.
 */
public class GetPingList {
    /**
     * ResultData : [{"ID":14,"GrowthDynamicsID":13,"Title":null,"CommentContent":"TM女嘟嘟嘟嘟特服","CreateTime":"2016/8/14 12:58:48","CommentUserAccount":"1999999999","CommentUserName":"1王小宝爸爸","DeleteFlag":false},{"ID":13,"GrowthDynamicsID":13,"Title":null,"CommentContent":"偷摸头特特肚饿","CreateTime":"2016/8/14 12:58:42","CommentUserAccount":"1999999999","CommentUserName":"1王小宝爸爸","DeleteFlag":false},{"ID":12,"GrowthDynamicsID":13,"Title":null,"CommentContent":"UNO头额嘟嘟嘟","CreateTime":"2016/8/14 12:58:37","CommentUserAccount":"1999999999","CommentUserName":"1王小宝爸爸","DeleteFlag":false}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 9
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 14
     * GrowthDynamicsID : 13
     * Title : null
     * CommentContent : TM女嘟嘟嘟嘟特服
     * CreateTime : 2016/8/14 12:58:48
     * CommentUserAccount : 1999999999
     * CommentUserName : 1王小宝爸爸
     * DeleteFlag : false
     */

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
        private int ID;
        private int GrowthDynamicsID;
        private String Title;
        private String CommentContent;
        private String CreateTime;
        private String CommentUserAccount;
        private String CommentUserName;
        private boolean DeleteFlag;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getGrowthDynamicsID() {
            return GrowthDynamicsID;
        }

        public void setGrowthDynamicsID(int GrowthDynamicsID) {
            this.GrowthDynamicsID = GrowthDynamicsID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getCommentContent() {
            return CommentContent;
        }

        public void setCommentContent(String CommentContent) {
            this.CommentContent = CommentContent;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCommentUserAccount() {
            return CommentUserAccount;
        }

        public void setCommentUserAccount(String CommentUserAccount) {
            this.CommentUserAccount = CommentUserAccount;
        }

        public String getCommentUserName() {
            return CommentUserName;
        }

        public void setCommentUserName(String CommentUserName) {
            this.CommentUserName = CommentUserName;
        }

        public boolean isDeleteFlag() {
            return DeleteFlag;
        }

        public void setDeleteFlag(boolean DeleteFlag) {
            this.DeleteFlag = DeleteFlag;
        }
    }
}
