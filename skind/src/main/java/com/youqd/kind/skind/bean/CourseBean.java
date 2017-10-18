package com.youqd.kind.skind.bean;

/**
 * Created by Administrator on 2016/3/22.
 */
public class CourseBean {
    private int id;
    private int indexCode = 0;
    private String courseName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(int indexCode) {
        this.indexCode = indexCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseIndexName(){
        String code = "";
        switch (indexCode){
            case 0:
                code = "一";
                break;
            case 1:
                code = "二";
                break;
            case 2:
                code = "三";
                break;
            case 3:
                code = "四";
                break;
            default:
                break;
        }
        return "第" + code + "节课";
    }
}
