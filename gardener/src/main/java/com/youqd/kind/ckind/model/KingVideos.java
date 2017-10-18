package com.youqd.kind.ckind.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Chenxiu on 2016/8/28.
 */
public class KingVideos implements Parcelable {

    private String VideoPath;

    private String Thumbnail;

    public KingVideos(String videoPath, String thumbnail) {
        VideoPath = videoPath;
        Thumbnail = thumbnail;
    }


    public String getVideoPath() {

        return VideoPath;
    }

    public boolean isVideo(){
        return !TextUtils.isEmpty(VideoPath);
    }

    public void setVideoPath(String videoPath) {
        VideoPath = videoPath;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.VideoPath);
        dest.writeString(this.Thumbnail);
    }

    protected KingVideos(Parcel in) {
        this.VideoPath = in.readString();
        this.Thumbnail = in.readString();
    }

    public static final Creator<KingVideos> CREATOR = new Creator<KingVideos>() {
        @Override
        public KingVideos createFromParcel(Parcel source) {
            return new KingVideos(source);
        }

        @Override
        public KingVideos[] newArray(int size) {
            return new KingVideos[size];
        }
    };
}
