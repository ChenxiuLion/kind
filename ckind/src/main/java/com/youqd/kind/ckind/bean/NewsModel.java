package com.youqd.kind.ckind.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Chenxiu on 2016/7/19.
 */
public class NewsModel {

    /**
     * ResultData : [{"ID":11,"Title":"校园新闻接口测试","Content":"校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试","MediaURL":"../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png;../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png;../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png;../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png;../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png","ImageURL":"../20160628.mp4;20160628.mp4;20160628.mp4","CreateUserID":1,"CreateUserName":"yuanzhangTest1","CreateTime":"2016-06-29T16:36:56.8","NoticeType":1,"SubNoticeType":1,"ReadClassID":1,"ReadClassName":"中二班","ReadGradeID":1,"AgentID":1,"KindergartenID":1,"LastUpdateUserID":1,"LastUpdateUserName":"19999999999","LastUpdateTime":"2016-06-29T15:51:00","CreateUserType":3}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 6
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 11
     * Title : 校园新闻接口测试
     * Content : 校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试校园新闻接口测试
     * MediaURL : ../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png;../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png;../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png;../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png;../Resources/2016/6/30/32b72d7961864e9f932ff5b54a44b4a4.png
     * ImageURL : ../20160628.mp4;20160628.mp4;20160628.mp4
     * CreateUserID : 1
     * CreateUserName : yuanzhangTest1
     * CreateTime : 2016-06-29T16:36:56.8
     * NoticeType : 1
     * SubNoticeType : 1
     * ReadClassID : 1
     * ReadClassName : 中二班
     * ReadGradeID : 1
     * AgentID : 1
     * KindergartenID : 1
     * LastUpdateUserID : 1
     * LastUpdateUserName : 19999999999
     * LastUpdateTime : 2016-06-29T15:51:00
     * CreateUserType : 3
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

    public static class ResultDataBean implements Serializable {
        private int ID;
        private String Title;
        private String Content;
        private String MediaURL;
        private String ImageURL;
        private int CreateUserID;
        private String CreateUserName="";
        private String CreateTime="";
        private int NoticeType;
        private int SubNoticeType;
        private int ReadClassID;
        private String ReadClassName="";
        private int ReadGradeID;
        private int AgentID;
        private int KindergartenID;
        private int LastUpdateUserID;
        private String LastUpdateUserName="";
        private String LastUpdateTime="";
        private String Summary;

        public String getSummary() {
            return Summary+"...";
        }

        public void setSummary(String summary) {
            Summary = summary;
        }

        private int CreateUserType;

        public String getFengmian(){
            if(TextUtils.isEmpty(ImageURL)){

                if(TextUtils.isEmpty(MediaURL)){
                    return null;
                }else{
                    final String[] image = MediaURL.split(";");
                    return image[0];
                }
            }else{
                final String[] image = ImageURL.split(";");
                return image[0];
            }
        }
        public Date getLastUpdateTimeDate() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date d = dateFormat.parse(LastUpdateTime);

                return d;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
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

        public int getCreateUserID() {
            return CreateUserID;
        }

        public void setCreateUserID(int CreateUserID) {
            this.CreateUserID = CreateUserID;
        }

        public String getCreateUserName() {
            return CreateUserName;
        }

        public void setCreateUserName(String CreateUserName) {
            this.CreateUserName = CreateUserName;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getNoticeType() {
            return NoticeType;
        }

        public void setNoticeType(int NoticeType) {
            this.NoticeType = NoticeType;
        }

        public int getSubNoticeType() {
            return SubNoticeType;
        }

        public void setSubNoticeType(int SubNoticeType) {
            this.SubNoticeType = SubNoticeType;
        }

        public int getReadClassID() {
            return ReadClassID;
        }

        public void setReadClassID(int ReadClassID) {
            this.ReadClassID = ReadClassID;
        }

        public String getReadClassName() {
            return ReadClassName;
        }

        public void setReadClassName(String ReadClassName) {
            this.ReadClassName = ReadClassName;
        }

        public int getReadGradeID() {
            return ReadGradeID;
        }

        public void setReadGradeID(int ReadGradeID) {
            this.ReadGradeID = ReadGradeID;
        }

        public int getAgentID() {
            return AgentID;
        }

        public void setAgentID(int AgentID) {
            this.AgentID = AgentID;
        }

        public int getKindergartenID() {
            return KindergartenID;
        }

        public void setKindergartenID(int KindergartenID) {
            this.KindergartenID = KindergartenID;
        }

        public int getLastUpdateUserID() {
            return LastUpdateUserID;
        }

        public void setLastUpdateUserID(int LastUpdateUserID) {
            this.LastUpdateUserID = LastUpdateUserID;
        }

        public String getLastUpdateUserName() {
            return LastUpdateUserName;
        }

        public void setLastUpdateUserName(String LastUpdateUserName) {
            this.LastUpdateUserName = LastUpdateUserName;
        }

        public String getLastUpdateTime() {
            return LastUpdateTime;
        }

        public void setLastUpdateTime(String LastUpdateTime) {
            this.LastUpdateTime = LastUpdateTime;
        }

        public int getCreateUserType() {
            return CreateUserType;
        }

        public void setCreateUserType(int CreateUserType) {
            this.CreateUserType = CreateUserType;
        }
    }
}
