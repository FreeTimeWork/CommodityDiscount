package com.mwb.dao.model.product.voucher;

import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.product.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  Created by mwv on 2017/4/4 0004.
 */
public class ProductVoucher implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer receiveNumber; //领取数量
    private Integer useNumber; //使用数量
    private BigDecimal payAmount; //付款金额
    private BigDecimal shouldChargeAmount; //应收金额
    private BigDecimal actualChargeAmount;//实收金额
    private Date createTime;
    private BigDecimal conversionRate;//转化率
    private String withoutRate;//外部链接
    private Product product;
    private List<VoucherPicture> pictures;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiveNumber() {
        return receiveNumber;
    }

    public void setReceiveNumber(Integer receiveNumber) {
        this.receiveNumber = receiveNumber;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<VoucherPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<VoucherPicture> pictures) {
        this.pictures = pictures;
    }
}
