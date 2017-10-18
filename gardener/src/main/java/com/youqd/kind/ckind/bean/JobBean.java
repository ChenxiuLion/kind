package com.youqd.kind.ckind.bean;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youqd.kind.ckind.model.KingVideos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youqd on 2016/3/24.
 */
public class JobBean implements Serializable {
    private int id;
    private boolean isFinish;
    private String name;
    private String content;
    private String date;
    private String image;

    private String okText;

    private String okImagePath;

    private String okVideoPath;

    private List<JobList.ResultDataBean> users;

    public List<JobList.ResultDataBean> getUsers() {
        return users;
    }

    public void setUsers(List<JobList.ResultDataBean> users) {
        this.users = users;
    }

    public ArrayList<KingVideos> getOkVideoPath() {
        return  new Gson().fromJson(okVideoPath,new TypeToken<ArrayList<KingVideos>>(){}.getType());
    }

    public void setOkVideoPath(String okVideoPath) {
        this.okVideoPath = okVideoPath;
    }

    private String okLaoshi;

    public String getOkText() {
        if(TextUtils.isEmpty(okText)){
            return "暂无评价";
        }
        return okText;
    }

    public void setOkText(String okText) {
        this.okText = okText;
    }

    public ArrayList<String> getOkImagePath() {
        return new Gson().fromJson(okImagePath,new TypeToken<ArrayList<String>>(){}.getType());
    }

    public void setOkImagePath(String okImagePath) {
        this.okImagePath = okImagePath;
    }

    public String getOkLaoshi() {
        return okLaoshi;
    }

    public void setOkLaoshi(String okLaoshi) {
        this.okLaoshi = okLaoshi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
