package com.mwb.controller.product.api;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class GrabRequest {
    private String productUrl;
    private String couponUrl;

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getCouponUrl() {
        return couponUrl;
    }

    public void setCouponUrl(String couponUrl) {
        this.couponUrl = couponUrl;
    }
}
