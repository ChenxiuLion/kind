package com.youqd.kind.ckind.bean;

import java.io.Serializable;

/**
 * Created by Chenxiu on 2016/7/16.
 */
public class KindBean  implements Serializable{

    /**
     * ID : 1
     * Name : sample string 2
     * Introduction : sample string 3
     * MediaURL : sample string 4
     * ImageURL : sample string 5
     * CreateTime : 2016-09-04T22:07:28.6894531+08:00
     * Remark : sample string 7
     * Status : true
     * Location : sample string 9
     * AgentID : 10
     * AgentName : sample string 11
     * QRCodeLink : sample string 12
     * Logo : sample string 13
     * Telephone : sample string 14
     * VideoViewStartTime : 2016-09-04T22:07:28.6904296+08:00
     * VideoViewEndTime : 2016-09-04T22:07:28.6904296+08:00
     */

    private ResultDataBean ResultData;
    /**
     * ResultData : {"ID":1,"Name":"sample string 2","Introduction":"sample string 3","MediaURL":"sample string 4","ImageURL":"sample string 5","CreateTime":"2016-09-04T22:07:28.6894531+08:00","Remark":"sample string 7","Status":true,"Location":"sample string 9","AgentID":10,"AgentName":"sample string 11","QRCodeLink":"sample string 12","Logo":"sample string 13","Telephone":"sample string 14","VideoViewStartTime":"2016-09-04T22:07:28.6904296+08:00","VideoViewEndTime":"2016-09-04T22:07:28.6904296+08:00"}
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
        private String Name;
        private String Introduction;
        private String MediaURL;
        private String ImageURL;
        private String CreateTime;
        private String Remark;
        private boolean Status;
        private String Location;
        private int AgentID;
        private String AgentName;
        private String QRCodeLink;
        private String Logo;
        private String Telephone;
        private String VideoViewStartTime;
        private String VideoViewEndTime;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getIntroduction() {
            return Introduction;
        }

        public void setIntroduction(String Introduction) {
            this.Introduction = Introduction;
        }

        public String getMediaURL() {
            return MediaURL;
        }

        public void setMediaURL(String MediaURL) {
            this.MediaURL = MediaURL;
        }

        public String getImageURL() {
            return ImageURL;
        }

        public void setImageURL(String ImageURL) {
            this.ImageURL = ImageURL;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public boolean isStatus() {
            return Status;
        }

        public void setStatus(boolean Status) {
            this.Status = Status;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public int getAgentID() {
            return AgentID;
        }

        public void setAgentID(int AgentID) {
            this.AgentID = AgentID;
        }

        public String getAgentName() {
            return AgentName;
        }

        public void setAgentName(String AgentName) {
            this.AgentName = AgentName;
        }

        public String getQRCodeLink() {
            return QRCodeLink;
        }

        public void setQRCodeLink(String QRCodeLink) {
            this.QRCodeLink = QRCodeLink;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public String getTelephone() {
            return Telephone;
        }

        public void setTelephone(String Telephone) {
            this.Telephone = Telephone;
        }

        public String getVideoViewStartTime() {
            return VideoViewStartTime;
        }

        public void setVideoViewStartTime(String VideoViewStartTime) {
            this.VideoViewStartTime = VideoViewStartTime;
        }

        public String getVideoViewEndTime() {
            return VideoViewEndTime;
        }

        public void setVideoViewEndTime(String VideoViewEndTime) {
            this.VideoViewEndTime = VideoViewEndTime;
        }
    }
}
