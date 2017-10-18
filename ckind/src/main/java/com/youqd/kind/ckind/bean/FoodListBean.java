package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/25.
 */
public class FoodListBean {
    /**
     * ResultData : [{"ID":24,"RecipesName":"面条12","PeriodsType":0,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-25T00:00:00"},{"ID":25,"RecipesName":"面条13","PeriodsType":1,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-25T00:00:00"},{"ID":26,"RecipesName":"面条14","PeriodsType":2,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-25T00:00:00"},{"ID":27,"RecipesName":"面条15","PeriodsType":3,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-25T00:00:00"},{"ID":28,"RecipesName":"面条16","PeriodsType":4,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-25T00:00:00"},{"ID":29,"RecipesName":"面条17","PeriodsType":4,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-26T00:00:00"},{"ID":30,"RecipesName":"面条18","PeriodsType":3,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-26T00:00:00"},{"ID":31,"RecipesName":"面条19","PeriodsType":2,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-26T00:00:00"},{"ID":32,"RecipesName":"面条20","PeriodsType":1,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-26T00:00:00"},{"ID":33,"RecipesName":"面条21","PeriodsType":0,"Photo":"sample string 4","GardenerLeaderID":1,"GardenerLeaderUserName":"sample string 6","KindergartenID":1,"CreateTime":"2016-07-26T00:00:00"}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 24
     * RecipesName : 面条12
     * PeriodsType : 0
     * Photo : sample string 4
     * GardenerLeaderID : 1
     * GardenerLeaderUserName : sample string 6
     * KindergartenID : 1
     * CreateTime : 2016-07-25T00:00:00
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
        private String RecipesName;
        private int PeriodsType;
        private String Photo;
        private int GardenerLeaderID;
        private String GardenerLeaderUserName;
        private int KindergartenID;
        private String CreateTime;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getRecipesName() {
            return RecipesName;
        }

        public void setRecipesName(String RecipesName) {
            this.RecipesName = RecipesName;
        }

        public int getPeriodsType() {
            return PeriodsType;
        }

        public void setPeriodsType(int PeriodsType) {
            this.PeriodsType = PeriodsType;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public int getGardenerLeaderID() {
            return GardenerLeaderID;
        }

        public void setGardenerLeaderID(int GardenerLeaderID) {
            this.GardenerLeaderID = GardenerLeaderID;
        }

        public String getGardenerLeaderUserName() {
            return GardenerLeaderUserName;
        }

        public void setGardenerLeaderUserName(String GardenerLeaderUserName) {
            this.GardenerLeaderUserName = GardenerLeaderUserName;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
