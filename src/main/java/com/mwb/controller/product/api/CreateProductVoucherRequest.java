package com.mwb.controller.product.api;

import java.math.BigDecimal;

/**
 *  Created by mwb on 2017/4/4 0004.
 */
public class CreateProductVoucherRequest {
    private Integer id;  //商品 id
    private Integer couponReceiveNumber; //领取数量
    private Integer couponUseNumber; //使用数量
    private BigDecimal payAmount; //付款金额
    private BigDecimal shouldChargeAmount; //付款金额
    private BigDecimal actualChargeAmount;//实收金额
    private BigDecimal conversionRate;//转化率
    private String withoutRate;//外部链接

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponReceiveNumber() {
        return couponReceiveNumber;
    }

    public void setCouponReceiveNumber(Integer couponReceiveNumber) {
        this.couponReceiveNumber = couponReceiveNumber;
    }

    public Integer getCouponUseNumber() {
        return couponUseNumber;
    }

    public void setCouponUseNumber(Integer couponUseNumber) {
        this.couponUseNumber = couponUseNumber;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getShouldChargeAmount() {
        return shouldChargeAmount;
    }

    public void setShouldChargeAmount(BigDecimal shouldChargeAmount) {
        this.shouldChargeAmount = shouldChargeAmount;
    }

    public BigDecimal getActualChargeAmount() {
        return actualChargeAmount;
    }

    public void setActualChargeAmount(BigDecimal actualChargeAmount) {
        this.actualChargeAmount = actualChargeAmount;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getWithoutRate() {
        return withoutRate;
    }

    public void setWithoutRate(String withoutRate) {
        this.withoutRate = withoutRate;
    }
}
