package com.mwb.controller.login.api;

/**
 * Created by fangchen.chai on 2017/4/3.
 */
public class LoginRequest {

    private String mobile;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
