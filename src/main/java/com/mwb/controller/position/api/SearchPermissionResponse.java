package com.mwb.controller.position.api;

import java.util.List;

import com.mwb.controller.api.ServiceResponse;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public class SearchPermissionResponse extends ServiceResponse {
    private List<PermissionVO> permissions;

    public List<PermissionVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionVO> permissions) {
        this.permissions = permissions;
    }
}
