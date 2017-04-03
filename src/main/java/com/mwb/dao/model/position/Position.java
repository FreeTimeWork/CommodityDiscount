package com.mwb.dao.model.position;

import java.io.Serializable;
import java.util.List;

import com.mwb.dao.model.permission.Permission;

/**
 * Created by Fangchen.chai on 2017/3/29.
 */
public class Position implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private List<Permission> permissions;

    public Position() {
    }

    public Position(Integer id) {
        this.id = id;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
