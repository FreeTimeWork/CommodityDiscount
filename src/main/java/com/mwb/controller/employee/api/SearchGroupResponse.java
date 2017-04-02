package com.mwb.controller.employee.api;

import java.util.List;

import com.mwb.controller.api.ServiceResponse;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public class SearchGroupResponse extends ServiceResponse{
    List<GroupVO> groups;

    public List<GroupVO> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupVO> groups) {
        this.groups = groups;
    }
}
