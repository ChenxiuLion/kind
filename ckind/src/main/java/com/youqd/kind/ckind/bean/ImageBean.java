package com.youqd.kind.ckind.bean;

/**
 * Created by Chenxiu on 2016/7/27.
 */
public class ImageBean {

    private int type = 0; //0为图片 1为视频

    private String path;

    private String suo;

    public String getSuo() {
        return suo;
    }

    public void setSuo(String suo) {
        this.suo = suo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }
    public String getImage() {

        return path.replace(
                "Video","Thumbnail").replace(".mp4",".jpg");
    }

    public void setPath(String path) {
        this.path = path;
    }
}
