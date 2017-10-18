package com.youqd.kind.ckind.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/25.
 */
public class ContactBean implements Serializable {

    private int id;
    private String name;
    private String tel;
    private String position;

    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
