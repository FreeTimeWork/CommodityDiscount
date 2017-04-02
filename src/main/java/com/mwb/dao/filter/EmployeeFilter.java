package com.mwb.dao.filter;

import org.apache.commons.lang.StringUtils;

/**
 * Created by fangchen.chai on 2017/4/1.
 */
public class EmployeeFilter extends SearchFilter {

    private String fullName;
    private Integer groupId;
    private Integer positionId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (StringUtils.isNotEmpty(fullName)){
            this.fullName = fullName+"%";
        }
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
