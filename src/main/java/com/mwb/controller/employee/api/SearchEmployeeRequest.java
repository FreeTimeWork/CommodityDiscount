package com.mwb.controller.employee.api;

import com.mwb.controller.api.PagingRequest;

/**
 * Created by fangchen.chai on 2017/4/1.
 */
public class SearchEmployeeRequest extends PagingRequest{

    private String fullName;
    private Integer groupId;
    private Integer positionId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }
}
