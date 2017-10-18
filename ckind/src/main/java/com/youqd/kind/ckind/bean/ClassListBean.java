package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/7/25.
 */
public class ClassListBean {
    /**
     * ResultData : [{"ID":6,"CourseName":"饿死我了，我","CourseNum":1,"PeriodsType":0,"GardenerID":0,"GardenerName":"我们","KindergartenID":1,"ClassID":1,"CourseTime":"2016-07-25T00:00:00","CreateTime":"2016-07-24T00:00:00","CreateByID":0,"CreateByName":"111"},{"ID":7,"CourseName":"语文","CourseNum":1,"PeriodsType":0,"GardenerID":0,"GardenerName":"王者","KindergartenID":1,"ClassID":1,"CourseTime":"2016-07-25T00:00:00","CreateTime":"2016-07-24T00:00:00","CreateByID":0,"CreateByName":"11"},{"ID":9,"CourseName":"数学","CourseNum":1,"PeriodsType":0,"GardenerID":0,"GardenerName":"张先生","KindergartenID":1,"ClassID":1,"CourseTime":"2016-07-25T00:00:00","CreateTime":"2016-07-24T00:00:00","CreateByID":0,"CreateByName":null},{"ID":10,"CourseName":"音乐","CourseNum":1,"PeriodsType":0,"GardenerID":0,"GardenerName":"漂亮美眉","KindergartenID":1,"ClassID":1,"CourseTime":"2016-07-25T00:00:00","CreateTime":"2016-07-24T00:00:00","CreateByID":0,"CreateByName":null},{"ID":11,"CourseName":"音乐","CourseNum":1,"PeriodsType":0,"GardenerID":0,"GardenerName":"漂亮美眉","KindergartenID":1,"ClassID":1,"CourseTime":"2016-07-25T00:00:00","CreateTime":"2016-07-24T00:00:00","CreateByID":0,"CreateByName":null}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 6
     * CourseName : 饿死我了，我
     * CourseNum : 1
     * PeriodsType : 0
     * GardenerID : 0
     * GardenerName : 我们
     * KindergartenID : 1
     * ClassID : 1
     * CourseTime : 2016-07-25T00:00:00
     * CreateTime : 2016-07-24T00:00:00
     * CreateByID : 0
     * CreateByName : 111
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
        private String CourseName;
        private int CourseNum;
        private int PeriodsType;
        private int GardenerID;
        private String GardenerName;
        private int KindergartenID;
        private int ClassID;
        private String CourseTime;
        private String CreateTime;
        private int CreateByID;
        private String CreateByName;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCourseName() {
            return CourseName;
        }

        public void setCourseName(String CourseName) {
            this.CourseName = CourseName;
        }

        public int getCourseNum() {
            return CourseNum;
        }

        public void setCourseNum(int CourseNum) {
            this.CourseNum = CourseNum;
        }

        public int getPeriodsType() {
            return PeriodsType;
        }

        public void setPeriodsType(int PeriodsType) {
            this.PeriodsType = PeriodsType;
        }

        public int getGardenerID() {
            return GardenerID;
        }

        public void setGardenerID(int GardenerID) {
            this.GardenerID = GardenerID;
        }

        public String getGardenerName() {
            return GardenerName;
        }

        public void setGardenerName(String GardenerName) {
            this.GardenerName = GardenerName;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public int getClassID() {
            return ClassID;
        }

        public void setClassID(int ClassID) {
            this.ClassID = ClassID;
        }

        public String getCourseTime() {
            return CourseTime;
        }

        public void setCourseTime(String CourseTime) {
            this.CourseTime = CourseTime;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getCreateByID() {
            return CreateByID;
        }

        public void setCreateByID(int CreateByID) {
            this.CreateByID = CreateByID;
        }

        public String getCreateByName() {
            return CreateByName;
        }

        public void setCreateByName(String CreateByName) {
            this.CreateByName = CreateByName;
        }
    }
}
