package com.mwb.dao.model.employee;

import java.io.Serializable;

/**
 * Created by Fangchen.chai on 2017/3/29.
 */
public class Group implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    public Group() {
    }

    public Group(Integer id) {
        this.id = id;
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
}
