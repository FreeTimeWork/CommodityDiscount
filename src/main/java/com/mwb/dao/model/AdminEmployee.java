package com.mwb.dao.model;

import java.io.Serializable;

/**
 * Created by MengWeiBo on 2017-03-28
 */
public class AdminEmployee implements Serializable{
    private static final long serialVersionUID = 1L;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
