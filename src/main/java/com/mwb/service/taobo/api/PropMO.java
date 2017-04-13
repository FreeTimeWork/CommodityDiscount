package com.mwb.service.taobo.api;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2017-04-12
 */
public class PropMO {
    @JSONField(name = "url")
    private BigDecimal url;

    public BigDecimal getUrl() {
        return url;
    }

    public void setUrl(BigDecimal url) {
        this.url = url;
    }
}
