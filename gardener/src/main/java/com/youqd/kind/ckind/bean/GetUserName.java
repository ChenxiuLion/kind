package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/7/22.
 */
public class GetUserName {
    /**
     * ID : 1
     * Relation : sample string 2
     * BabyID : 3
     * ParentID : 4
     */

    private ResultDataBean ResultData;
    /**
     * ResultData : {"ID":1,"Relation":"sample string 2","BabyID":3,"ParentID":4}
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ReusltDataTotal : 3
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
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

    public static class ResultDataBean {
        private int ID;
        private String Relation;
        private int BabyID;
        private int ParentID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getRelation() {
            return Relation;
        }

        public void setRelation(String Relation) {
            this.Relation = Relation;
        }

        public int getBabyID() {
            return BabyID;
        }

        public void setBabyID(int BabyID) {
            this.BabyID = BabyID;
        }

        public int getParentID() {
            return ParentID;
        }

        public void setParentID(int ParentID) {
            this.ParentID = ParentID;
        }
    }
}
