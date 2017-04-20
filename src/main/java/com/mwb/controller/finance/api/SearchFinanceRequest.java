package com.mwb.controller.finance.api;

import com.mwb.controller.api.PagingRequest;

/**
 *  Created by mwb on 2017/4/2 0002.
 */
public class SearchFinanceRequest extends PagingRequest {
    private Integer groupId;
    private Integer employeeId;
    private String conditionId;
    private Boolean orderByAsc;  //是否为升序排序
    private String beginPayTime; //付款开始时间
    private String endPayTime;   //付款结束时间
    private String beginSubmitTime; //付款开始时间
    private String endSubmitTime;   //付款结束时间

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public Boolean getOrderByAsc() {
        return orderByAsc;
    }

    public void setOrderByAsc(Boolean orderByAsc) {
        this.orderByAsc = orderByAsc;
    }

    public String getBeginPayTime() {
        return beginPayTime;
    }

    public void setBeginPayTime(String beginPayTime) {
        this.beginPayTime = beginPayTime;
    }

    public String getEndPayTime() {
        return endPayTime;
    }

    public void setEndPayTime(String endPayTime) {
        this.endPayTime = endPayTime;
    }

    public String getBeginSubmitTime() {
        return beginSubmitTime;
    }

    public void setBeginSubmitTime(String beginSubmitTime) {
        this.beginSubmitTime = beginSubmitTime;
    }

    public String getEndSubmitTime() {
        return endSubmitTime;
    }

    public void setEndSubmitTime(String endSubmitTime) {
        this.endSubmitTime = endSubmitTime;
    }
}
