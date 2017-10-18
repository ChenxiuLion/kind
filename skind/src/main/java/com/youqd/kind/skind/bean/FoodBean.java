package com.youqd.kind.skind.bean;

/**
 * Created by youqd on 2016/3/24.
 */
public class FoodBean {
    private int id;
    private int indexCode;
    private String foodContent;

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

    public String getFoodContent() {
        return foodContent;
    }

    public void setFoodContent(String foodContent) {
        this.foodContent = foodContent;
    }

    public String getFoodIndexName(){
        String codeName = "";
        switch (indexCode){
            case 0:
                codeName = "早";
                break;
            case 1:
                codeName = "早加";
                break;
            case 2:
                codeName = "午";
                break;
            case 3:
                codeName = "午加";
                break;
            case 4:
                codeName = "晚";
                break;
            default:
                break;
        }
        return codeName + "餐";
    }
}
