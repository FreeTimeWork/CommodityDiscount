package com.mwb.controller.position.api;

import java.util.List;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public class ModifyPositionPermissionRequest {
    private Integer positionId;
    private List<Integer>  permissionIds;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
