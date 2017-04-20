package com.mwb.dao.filter;

import java.util.List;

import com.mwb.dao.model.employee.EmployeeStatus;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class FinanceFilter extends SearchFilter {
    private Integer groupId;
    private Integer employeeId;
    private EmployeeStatus status;    //筛选 员工状态
    private Boolean orderByAsc;  //是否为升序排序
    private TimeRange submitDate;
    private TimeRange payDate;
    private String searchRule;

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

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public Boolean getOrderByAsc() {
        return orderByAsc;
    }

    public void setOrderByAsc(Boolean orderByAsc) {
        this.orderByAsc = orderByAsc;
    }

    public TimeRange getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(TimeRange submitDate) {
        this.submitDate = submitDate;
    }

    public TimeRange getPayDate() {
        return payDate;
    }

    public void setPayDate(TimeRange payDate) {
        this.payDate = payDate;
    }

    public String getSearchRule() {
        return searchRule;
    }

    public void setSearchRule(String searchRule) {
        this.searchRule = searchRule;
    }
}
