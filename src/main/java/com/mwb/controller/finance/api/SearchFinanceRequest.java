package com.mwb.controller.finance.api;

import com.mwb.controller.api.PagingRequest;

/**
 *  Created by mwb on 2017/4/2 0002.
 */
public class SearchFinanceRequest extends PagingRequest {
    private Integer groupId;
    private Integer employeeId;
    private Integer statusId;    //筛选 员工状态
    private Boolean orderByAsc;  //是否为升序排序
    private String beginPayTime; //付款开始时间
    private String endPayTime;   //付款结束时间
    private boolean excel;

    public boolean isExcel() {
        return excel;
    }

    public void setExcel(boolean excel) {
        this.excel = excel;
    }

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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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
}
