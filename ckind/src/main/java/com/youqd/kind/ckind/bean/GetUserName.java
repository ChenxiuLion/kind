package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/22.
 */
public class GetUserName {

    /**
     * ResultData : [{"ID":2031,"Relation":"妈妈","BabyID":1,"ParentID":2031}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 2031
     * Relation : 妈妈
     * BabyID : 1
     * ParentID : 2031
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

    public ResultDataBean getResultData(int id) {
        if(ResultData!=null){

            for(ResultDataBean b : ResultData){
                if(id==b.getID()){
                    return b;
                }
            }
            return ResultData.get(0);
        }
        return null;
    }
    public ResultDataBean getResultData() {
        if(ResultData!=null){

            return ResultData.get(0);
        }
        return null;
    }
    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
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
