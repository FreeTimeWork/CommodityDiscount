package com.mwb.service.taobo.api;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by MengWeiBo on 2017-04-12
 */
public class AccessTokenMO {
    @JSONField(name = "expires_in")
    private String expiresIn;

    @JSONField(name = "access_token")
    private String accessToken;

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
