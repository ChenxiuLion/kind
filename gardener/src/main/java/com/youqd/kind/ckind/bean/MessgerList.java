package com.youqd.kind.ckind.bean;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Chenxiu on 2016/8/16.
 */
public class MessgerList {
    /**
     * ResultData : [{"ID":15,"MessageTitle":null,"Content":"我是老师 你知道吗","ParentID":1,"NoticeID":0,"ParentAccount":null,"ParentName":null,"ParentPhoto":null,"GardenerID":1,"GardenerAccount":null,"GardenerName":null,"GardenerPhoto":null,"KindergartenID":1,"ClassID":1,"CreateTime":"2016-08-07T14:54:41","Status":0,"SenderType":0,"DeleteFlag":false},{"ID":16,"MessageTitle":"sample string 2","Content":"asdasdasdasd","ParentID":1,"NoticeID":0,"ParentAccount":"sample string 6","ParentName":"sample string 7","ParentPhoto":"sample string 8","GardenerID":9,"GardenerAccount":"sample string 10","GardenerName":"sample string 11","GardenerPhoto":"sample string 12","KindergartenID":1,"ClassID":1,"CreateTime":"2016-08-16T00:00:00","Status":0,"SenderType":5,"DeleteFlag":false}]
     * ResultCode : 1
     * ResultMessage : 执行成功
     * ReusltDataTotal : 0
     */

    private int ResultCode;
    private String ResultMessage;
    private int ReusltDataTotal;
    /**
     * ID : 15
     * MessageTitle : null
     * Content : 我是老师 你知道吗
     * ParentID : 1
     * NoticeID : 0
     * ParentAccount : null
     * ParentName : null
     * ParentPhoto : null
     * GardenerID : 1
     * GardenerAccount : null
     * GardenerName : null
     * GardenerPhoto : null
     * KindergartenID : 1
     * ClassID : 1
     * CreateTime : 2016-08-07T14:54:41
     * Status : 0
     * SenderType : 0
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


    private TreeMap<Integer, Gardener> mGardenerMap = new TreeMap<>();

    public List<Gardener> getMessgers(){
        mGardenerMap.clear();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ResultDataBean bean : getResultData()) {
            Gardener gardener = null;
            if (mGardenerMap.get(bean.getParentID()) == null) {
                gardener = new Gardener();
                gardener.setGardenerID(bean.getParentID());
                if(bean.isMessger()) {
                    gardener.setGardenerAccount(bean.getParentAccount());
                    gardener.setGardenerName(bean.getParentName());
                    gardener.setGardenerPhoto(bean.getParentPhoto());
                } else{
                    gardener.setGardenerAccount(bean.getContent());
                    gardener.setGardenerName(bean.getMessageTitle());
                    gardener.setGardenerPhoto(bean.getCreateTime());
                }
                try {
                    long time = format.parse(bean.getCreateTime()).getTime();
                    if(time>gardener.getGardenerTime())
                        gardener.setGardenerTime(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                gardener.setId(bean.getParentID());
                gardener.setGardenerType(bean.isMessger()?1:2);
                mGardenerMap.put(bean.getParentID(), gardener);
            }
            gardener = mGardenerMap.get(bean.getParentID());
            Gardener.Messger messger = new Gardener.Messger();
            messger.setContent(bean.getContent());
            messger.setCreateTime(bean.getCreateTime());
            messger.setUser(bean.getSenderType() == 5);
            messger.setId(bean.getID());
            if(bean.isMessger()) {
                gardener.getData().add(messger);
            }
        }
        Iterator iter = mGardenerMap.entrySet().iterator();
        List<Gardener> data = new ArrayList<Gardener>();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Gardener val = (Gardener) entry.getValue();
            data.add(val);
        }
        return data;
    }

    public List<ResultDataBean> getNotice(){
        List<ResultDataBean> data = new ArrayList<>();
        for (ResultDataBean bean : getResultData()) {
            if(bean.getNoticeID()>0){
                data.add(bean);
            }
        }
        return data;
    }
    public class SortComparator implements Comparator {
        @Override
        public int compare(Object lhs, Object rhs) {
            Gardener a = (Gardener) lhs;
            Gardener b = (Gardener) rhs;

            return (b.getGardenerTime()> a.getGardenerTime()?0:1);
        }
    }
    public static class Gardener  implements Comparable<Gardener>{
        private int GardenerID;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private String GardenerAccount;
        private String GardenerName;
        private String GardenerPhoto;
        private long GardenerTime;
        private int GardenerType;

        public long getGardenerTime() {
            return GardenerTime;
        }

        public void setGardenerTime(long gardenerTime) {
            GardenerTime = gardenerTime;
        }

        public int getGardenerType() {
            return GardenerType;
        }

        public void setGardenerType(int gardenerType) {
            GardenerType = gardenerType;
        }

        public List<Messger> getmData() {
            return mData;
        }

        public void setmData(List<Messger> mData) {
            this.mData = mData;
        }

        private List<Messger> mData = new ArrayList<>();

        public List<Messger> getData() {
            if(mData == null){
                mData = new ArrayList<>();
            }
            return mData;
        }

        public void setData(List<Messger> mData) {
            this.mData = mData;
        }

        public int getGardenerID() {
            return GardenerID;
        }

        public void setGardenerID(int gardenerID) {
            GardenerID = gardenerID;
        }

        public String getGardenerAccount() {
            return GardenerAccount;
        }

        public void setGardenerAccount(String gardenerAccount) {
            GardenerAccount = gardenerAccount;
        }

        public String getGardenerName() {
            return GardenerName;
        }

        public void setGardenerName(String gardenerName) {
            GardenerName = gardenerName;
        }

        public String getGardenerPhoto() {
            return GardenerPhoto;
        }

        public void setGardenerPhoto(String gardenerPhoto) {
            GardenerPhoto = gardenerPhoto;
        }

        @Override
        public int compareTo(@NonNull Gardener another) {
            /**
             * compareTo()：大于0表示前一个数据比后一个数据大， 0表示相等，小于0表示前一个数据小于后一个数据
             * 相等时会走到equals()，这里讲姓名年龄都一样的对象当作一个对象
             */
            int num = Long.valueOf(this.getGardenerTime()).compareTo(another.getGardenerTime());//先比较年龄
            return num;
        }
        public static class Messger {
            private String MessageTitle;
            private String Content;
            private String CreateTime;
            private boolean isUser;
            private int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMessageTitle() {
                return MessageTitle;
            }

            public void setMessageTitle(String messageTitle) {
                MessageTitle = messageTitle;
            }

            public String getContent() {
                return Content;
            }

            public void setContent(String content) {
                Content = content;
            }

            public boolean isUser() {
                return isUser;
            }

            public void setUser(boolean user) {
                isUser = user;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String createTime) {
                CreateTime = createTime;
            }
        }
    }

    public static class ResultDataBean {
        private int ID;
        private String MessageTitle;
        private String Content;
        private int ParentID;
        private int NoticeID;
        private String ParentAccount;
        private String ParentName;
        private String ParentPhoto;
        private int GardenerID;
        private String GardenerAccount;
        private String GardenerName;
        private String GardenerPhoto;
        private int KindergartenID;
        private int ClassID;
        private String CreateTime;
        private int Status;
        private int SenderType;
        private boolean DeleteFlag;

        public boolean isMessger(){
            return GardenerID!=0;
        }


        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getMessageTitle() {
            return MessageTitle;
        }

        public void setMessageTitle(String MessageTitle) {
            this.MessageTitle = MessageTitle;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public int getParentID() {
            return ParentID;
        }

        public void setParentID(int ParentID) {
            this.ParentID = ParentID;
        }

        public int getNoticeID() {
            return NoticeID;
        }

        public void setNoticeID(int NoticeID) {
            this.NoticeID = NoticeID;
        }

        public String getParentAccount() {
            return ParentAccount;
        }

        public void setParentAccount(String ParentAccount) {
            this.ParentAccount = ParentAccount;
        }

        public String getParentName() {
            return ParentName;
        }

        public void setParentName(String ParentName) {
            this.ParentName = ParentName;
        }

        public String getParentPhoto() {
            return ParentPhoto;
        }

        public void setParentPhoto(String ParentPhoto) {
            this.ParentPhoto = ParentPhoto;
        }

        public int getGardenerID() {
            if(GardenerID==0){
                return NoticeID;
            }
            return GardenerID;
        }

        public void setGardenerID(int GardenerID) {
            this.GardenerID = GardenerID;
        }

        public String getGardenerAccount() {
            return GardenerAccount;
        }

        public void setGardenerAccount(String GardenerAccount) {
            this.GardenerAccount = GardenerAccount;
        }

        public String getGardenerName() {
            return GardenerName;
        }

        public void setGardenerName(String GardenerName) {
            this.GardenerName = GardenerName;
        }

        public String getGardenerPhoto() {
            return GardenerPhoto;
        }

        public void setGardenerPhoto(String GardenerPhoto) {
            this.GardenerPhoto = GardenerPhoto;
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

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public int getSenderType() {
            return SenderType;
        }

        public void setSenderType(int SenderType) {
            this.SenderType = SenderType;
        }

        public boolean isDeleteFlag() {
            return DeleteFlag;
        }

        public void setDeleteFlag(boolean DeleteFlag) {
            this.DeleteFlag = DeleteFlag;
        }
    }
}
