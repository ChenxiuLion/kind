package com.youqd.kind.ckind.bean;

import java.util.List;

/**
 * Created by Chenxiu on 2016/8/14.
 */
public class PingList {
    /**
     * GrowthDynamicsID : [13,12,11]
     * OrderBy : id
     * Ascending : false
     */

    private String OrderBy = "id";
    private boolean Ascending = true;
    private List<Integer> GrowthDynamicsID;

    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String OrderBy) {
        this.OrderBy = OrderBy;
    }

    public boolean isAscending() {
        return Ascending;
    }

    public void setAscending(boolean Ascending) {
        this.Ascending = Ascending;
    }

    public List<Integer> getGrowthDynamicsID() {
        return GrowthDynamicsID;
    }

    public void setGrowthDynamicsID(List<Integer> GrowthDynamicsID) {
        this.GrowthDynamicsID = GrowthDynamicsID;
    }
}
