package com.mwb.controller.product.api;

/**
 * Created by fangchen.chai on 2017/4/5.
 */
public class BaseApproveRequest {

    private Integer productStatusId;
    private Integer productId;
    private String payTime;

    public Integer getProductStatusId() {
        return productStatusId;
    }

    public void setProductStatusId(Integer productStatusId) {
        this.productStatusId = productStatusId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
