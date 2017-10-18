package com.youqd.kind.ckind.model;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Chenxiu on 2016/8/21.
 */
public class RecordInfo {
    /**
     * ResultData : [{"ID":1,"CheckTime":"2016-08-21T01:30:14.2890625+08:00","CheckType":2,"Photo":"sample string 3","CardNO":"sample string 4","ClassID":5,"BabyID":6,"UserName":"sample string 7","Weight":1,"Temperature":1,"Height":8,"HealthStatus":1,"Reason":"sample string 9","Area":"sample string 10","AgentName":"sample string 11","KindergartenName":"sample string 12","StatisticalRate":"sample string 13"},{"ID":1,"CheckTime":"2016-08-21T01:30:14.2890625+08:00","CheckType":2,"Photo":"sample string 3","CardNO":"sample string 4","ClassID":5,"BabyID":6,"UserName":"sample string 7","Weight":1,"Temperature":1,"Height":8,"HealthStatus":1,"Reason":"sample string 9","Area":"sample string 10","AgentName":"sample string 11","KindergartenName":"sample string 12","StatisticalRate":"sample string 13"}]
     * ResultCode : 0
     * ResultMessage : sample string 1
     * ReusltDataTotal : 3
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 1
     * CheckTime : 2016-08-21T01:30:14.2890625+08:00
     * CheckType : 2
     * Photo : sample string 3
     * CardNO : sample string 4
     * ClassID : 5
     * BabyID : 6
     * UserName : sample string 7
     * Weight : 1.0
     * Temperature : 1.0
     * Height : 8.0
     * HealthStatus : 1
     * Reason : sample string 9
     * Area : sample string 10
     * AgentName : sample string 11
     * KindergartenName : sample string 12
     * StatisticalRate : sample string 13
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
        private String CheckTime;
        private int CheckType;
        private String Photo;
        private String CardNO;
        private int ClassID;
        private int BabyID;
        private String UserName;
        private double Weight;
        private double Temperature;
        private double Height;
        private int HealthStatus;
        private String Reason;
        private String Area;
        private String AgentName;
        private String KindergartenName;
        private String StatisticalRate;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCheckTime() {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy年M月d日 H点m分");
            try {
                return format2.format(format.parse(CheckTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return CheckTime;
        }

        public void setCheckTime(String CheckTime) {
            this.CheckTime = CheckTime;
        }

        public int getCheckType() {
            return CheckType;
        }

        public void setCheckType(int CheckType) {
            this.CheckType = CheckType;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public String getCardNO() {
            return CardNO;
        }

        public void setCardNO(String CardNO) {
            this.CardNO = CardNO;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public int getBabyID() {
            return BabyID;
        }

        public void setBabyID(int BabyID) {
            this.BabyID = BabyID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public double getWeight() {
            return Weight;
        }

        public void setWeight(double Weight) {
            this.Weight = Weight;
        }

        public double getTemperature() {
            return Temperature;
        }

        public void setTemperature(double Temperature) {
            this.Temperature = Temperature;
        }

        public double getHeight() {
            return Height;
        }

        public void setHeight(double Height) {
            this.Height = Height;
        }

        public int getHealthStatus() {
            return HealthStatus;
        }

        public void setHealthStatus(int HealthStatus) {
            this.HealthStatus = HealthStatus;
        }

        public String getReason() {
            if(TextUtils.isEmpty(Reason)){
                return "无";
            }
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getArea() {
            return Area;
        }

        public void setArea(String Area) {
            this.Area = Area;
        }

        public String getAgentName() {
            return AgentName;
        }

        public void setAgentName(String AgentName) {
            this.AgentName = AgentName;
        }

        public String getKindergartenName() {
            return KindergartenName;
        }

        public void setKindergartenName(String KindergartenName) {
            this.KindergartenName = KindergartenName;
        }

        public String getStatisticalRate() {
            return StatisticalRate;
        }

        public void setStatisticalRate(String StatisticalRate) {
            this.StatisticalRate = StatisticalRate;
        }
    }
}
