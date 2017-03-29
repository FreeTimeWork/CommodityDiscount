package com.mwb.dao.model.position;

import java.io.Serializable;

import com.mwb.dao.model.permission.Permission;

/**
 * Created by Fangchen.chai on 2017/3/29.
 */
public class PositionPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Position position;
    private Permission permission;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
