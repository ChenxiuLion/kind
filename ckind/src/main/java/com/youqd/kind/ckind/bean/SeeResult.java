package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/31.
 */
public class SeeResult {
    /**
     * ResultData : [{"ID":3,"Location":"校园","VideoLink":"sample string 3","VideoPhoto":"sample str","VideoType":5,"AgentID":1,"ClassID":1,"KindergartenID":1,"VideoViewStartTime":"2016-07-31T00:00:00","VideoViewEndTime":"2016-07-31T00:00:00"},{"ID":4,"Location":"教室","VideoLink":"sample string 3","VideoPhoto":"sample str","VideoType":5,"AgentID":1,"ClassID":1,"KindergartenID":1,"VideoViewStartTime":"2016-07-31T00:00:00","VideoViewEndTime":"2016-07-31T00:00:00"},{"ID":5,"Location":"操场","VideoLink":"sample string 3","VideoPhoto":"sample str","VideoType":5,"AgentID":1,"ClassID":1,"KindergartenID":1,"VideoViewStartTime":"2016-07-31T00:00:00","VideoViewEndTime":"2016-07-31T00:00:00"},{"ID":6,"Location":"食堂","VideoLink":"sample string 3","VideoPhoto":"sample str","VideoType":5,"AgentID":1,"ClassID":1,"KindergartenID":1,"VideoViewStartTime":"2016-07-31T00:00:00","VideoViewEndTime":"2016-07-31T00:00:00"}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 3
     * Location : 校园
     * VideoLink : sample string 3
     * VideoPhoto : sample str
     * VideoType : 5
     * AgentID : 1
     * ClassID : 1
     * KindergartenID : 1
     * VideoViewStartTime : 2016-07-31T00:00:00
     * VideoViewEndTime : 2016-07-31T00:00:00
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
        private String Location;
        private String VideoLink;
        private String VideoPhoto;
        private int VideoType;
        private int AgentID;
        private int ClassID;
        private int KindergartenID;
        private String VideoViewStartTime;
        private String VideoViewEndTime;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String Location) {
            this.Location = Location;
        }

        public String getVideoLink() {
            return VideoLink;
        }

        public void setVideoLink(String VideoLink) {
            this.VideoLink = VideoLink;
        }

        public String getVideoPhoto() {
            return VideoPhoto;
        }

        public void setVideoPhoto(String VideoPhoto) {
            this.VideoPhoto = VideoPhoto;
        }

        public int getVideoType() {
            return VideoType;
        }

        public void setVideoType(int VideoType) {
            this.VideoType = VideoType;
        }

        public int getAgentID() {
            return AgentID;
        }

        public void setAgentID(int AgentID) {
            this.AgentID = AgentID;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
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
