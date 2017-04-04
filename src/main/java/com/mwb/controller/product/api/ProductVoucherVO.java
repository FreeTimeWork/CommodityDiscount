package com.mwb.controller.product.api;

import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  Created by Administrator on 2017/4/4 0004.
 */
public class ProductVoucherVO {
    private Integer id;
    private Integer receiveNumber; //领取数量
    private Integer useNumber; //使用数量
    private BigDecimal payAmount; //付款金额
    private BigDecimal shouldChargeAmount; //应收金额
    private BigDecimal actualChargeAmount;//实收金额
    private BigDecimal conversionRate;//转化率
    private String withoutRate;//外部链接
    private List<String> pictures;

    public static ProductVoucherVO toVO(ProductVoucher voucher) {
        if (voucher == null) {
            return null;
        }
        ProductVoucherVO vo = new ProductVoucherVO();

        vo.setId(voucher.getProduct().getId());
        vo.setReceiveNumber(voucher.getReceiveNumber());
        vo.setUseNumber(voucher.getUseNumber());
        vo.setPayAmount(voucher.getPayAmount());
        vo.setShouldChargeAmount(voucher.getShouldChargeAmount());
        vo.setActualChargeAmount(voucher.getActualChargeAmount());
        vo.setConversionRate(voucher.getConversionRate());
        vo.setWithoutRate(voucher.getWithoutRate());
        List<String> pictures = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(voucher.getPictures())) {
            for (VoucherPicture picture : voucher.getPictures()) {
                pictures.add(picture.getUrl());
            }
        }
        vo.setPictures(pictures);

        return vo;
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

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
