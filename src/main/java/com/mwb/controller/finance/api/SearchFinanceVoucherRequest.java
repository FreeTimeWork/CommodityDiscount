package com.mwb.controller.finance.api;

import com.mwb.controller.api.PagingRequest;

import java.math.BigDecimal;

/**
 *   Created by mwb on 2017/4/2 0002.
 */
public class SearchFinanceVoucherRequest extends PagingRequest {
    private Integer productTypeId;
    private Integer groupId;
    private Integer employeeId;
    private Boolean orderByAsc;
    private String createBeginTime;     //提交时间
    private String createEndTime;
    private String beginFromTime;       //优惠券开始时间
    private String beginToTime;
    private String endFromTime;        //优惠券结束时间
    private String endToTime;
    private BigDecimal minChargePrice;        //服务费用
    private BigDecimal maxChargePrice;        //服务费用
    private Integer minUseNumber;    //领取数量
    private Integer maxUseNumber;    //领取数量
    private Integer minSurplusNumber; //剩余数量
    private Integer maxSurplusNumber; //剩余数量
    private BigDecimal minDiscountPrice;   //卷后价格
    private BigDecimal maxDiscountPrice;   //卷后价格
    private BigDecimal minPayPrice;   //付款价格
    private BigDecimal maxPayPrice;   //付款价格
    private Integer id;
    private String productId;
    private String name;

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getOrderByAsc() {
        return orderByAsc;
    }

    public void setOrderByAsc(Boolean orderByAsc) {
        this.orderByAsc = orderByAsc;
    }

    public String getCreateBeginTime() {
        return createBeginTime;
    }

    public void setCreateBeginTime(String createBeginTime) {
        this.createBeginTime = createBeginTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getBeginFromTime() {
        return beginFromTime;
    }

    public void setBeginFromTime(String beginFromTime) {
        this.beginFromTime = beginFromTime;
    }

    public String getBeginToTime() {
        return beginToTime;
    }

    public void setBeginToTime(String beginToTime) {
        this.beginToTime = beginToTime;
    }

    public String getEndFromTime() {
        return endFromTime;
    }

    public void setEndFromTime(String endFromTime) {
        this.endFromTime = endFromTime;
    }

    public String getEndToTime() {
        return endToTime;
    }

    public void setEndToTime(String endToTime) {
        this.endToTime = endToTime;
    }

    public BigDecimal getMinChargePrice() {
        return minChargePrice;
    }

    public void setMinChargePrice(BigDecimal minChargePrice) {
        this.minChargePrice = minChargePrice;
    }

    public BigDecimal getMaxChargePrice() {
        return maxChargePrice;
    }

    public void setMaxChargePrice(BigDecimal maxChargePrice) {
        this.maxChargePrice = maxChargePrice;
    }

    public Integer getMinUseNumber() {
        return minUseNumber;
    }

    public void setMinUseNumber(Integer minUseNumber) {
        this.minUseNumber = minUseNumber;
    }

    public Integer getMaxUseNumber() {
        return maxUseNumber;
    }

    public void setMaxUseNumber(Integer maxUseNumber) {
        this.maxUseNumber = maxUseNumber;
    }

    public Integer getMinSurplusNumber() {
        return minSurplusNumber;
    }

    public void setMinSurplusNumber(Integer minSurplusNumber) {
        this.minSurplusNumber = minSurplusNumber;
    }

    public Integer getMaxSurplusNumber() {
        return maxSurplusNumber;
    }

    public void setMaxSurplusNumber(Integer maxSurplusNumber) {
        this.maxSurplusNumber = maxSurplusNumber;
    }

    public BigDecimal getMinDiscountPrice() {
        return minDiscountPrice;
    }

    public void setMinDiscountPrice(BigDecimal minDiscountPrice) {
        this.minDiscountPrice = minDiscountPrice;
    }

    public BigDecimal getMaxDiscountPrice() {
        return maxDiscountPrice;
    }

    public void setMaxDiscountPrice(BigDecimal maxDiscountPrice) {
        this.maxDiscountPrice = maxDiscountPrice;
    }

    public BigDecimal getMinPayPrice() {
        return minPayPrice;
    }

    public void setMinPayPrice(BigDecimal minPayPrice) {
        this.minPayPrice = minPayPrice;
    }

    public BigDecimal getMaxPayPrice() {
        return maxPayPrice;
    }

    public void setMaxPayPrice(BigDecimal maxPayPrice) {
        this.maxPayPrice = maxPayPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
