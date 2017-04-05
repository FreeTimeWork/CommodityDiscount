package com.mwb.dao.filter;

import com.mwb.dao.model.product.ProductType;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  Created by mwb on 2017/4/4 0004.
 */
public class ProductVoucherFilter extends SearchFilter{
    private ProductType productType;
    private Integer groupId;
    private Integer employeeId;
    private Boolean orderByAsc;
    private Date createBeginTime;     //提交时间
    private Date createEndTime;
    private Date beginFromTime;       //优惠券开始时间
    private Date beginToTime;
    private Date endFromTime;        //优惠券结束时间
    private Date endToTime;
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
    private Integer productId;
    private String name;

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public Date getCreateBeginTime() {
        return createBeginTime;
    }

    public void setCreateBeginTime(Date createBeginTime) {
        this.createBeginTime = createBeginTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    public Date getBeginFromTime() {
        return beginFromTime;
    }

    public void setBeginFromTime(Date beginFromTime) {
        this.beginFromTime = beginFromTime;
    }

    public Date getBeginToTime() {
        return beginToTime;
    }

    public void setBeginToTime(Date beginToTime) {
        this.beginToTime = beginToTime;
    }

    public Date getEndFromTime() {
        return endFromTime;
    }

    public void setEndFromTime(Date endFromTime) {
        this.endFromTime = endFromTime;
    }

    public Date getEndToTime() {
        return endToTime;
    }

    public void setEndToTime(Date endToTime) {
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isNotBlank(name)){
            this.name = "%" + name +"%";
        }
    }
}
