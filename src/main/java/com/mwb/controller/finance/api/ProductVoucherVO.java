package com.mwb.controller.finance.api;

import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mwb on 2017/4/4 0004.
 */
public class ProductVoucherVO {
    private Integer id;
    private String employeeName;
    private String createTime;
    private String couponBeginTime;       //优惠券开始时间
    private String couponEndTime;         //优惠券结束时间
    private String pictureUrl;             //商品主图
    private String url;                 //商品链接
    private String name;                //商品名称
    private BigDecimal discountPrice;   //卷后价格
    private BigDecimal chargePrice;        //收费单价
    private BigDecimal ratio;           //佣金比例
    private Integer couponUseNumber;    //使用数量
    private Integer couponReceiveNumber; //领取数量
    private BigDecimal shouldChargeAmount;//应收金额
    private BigDecimal payAmount;       //付款金额
    private Integer useRatio;        //使用率

    public static ProductVoucherVO toVO(ProductVoucher voucher) {
        ProductVoucherVO vo = new ProductVoucherVO();
        Product product = voucher.getProduct();
        vo.setId(voucher.getId());
        vo.setEmployeeName(product.getEmployee().getFullName());
        vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCreateTime()));
        vo.setCouponBeginTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponBeginTime()));
        vo.setCouponBeginTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponBeginTime()));
        vo.setPictureUrl(product.getPictureUrl());
        vo.setUrl(product.getUrl());
        vo.setName(product.getName());
        vo.setDiscountPrice(product.getDiscountPrice());
        vo.setChargePrice(product.getChargePrice());
        vo.setRatio(product.getRatio());
        vo.setCouponUseNumber(voucher.getUseNumber());
        vo.setCouponReceiveNumber(voucher.getReceiveNumber());
        vo.setShouldChargeAmount(voucher.getShouldChargeAmount());
        vo.setPayAmount(voucher.getPayAmount());
        vo.setUseRatio((voucher.getUseNumber() * 100) / (voucher.getReceiveNumber() * 100));

        return vo;
    }

    public static List<ProductVoucherVO> toVOs(List<ProductVoucher> vouchers) {
        List<ProductVoucherVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(vouchers)) {
            for (ProductVoucher voucher : vouchers) {
                vos.add(toVO(voucher));
            }
        }
        return vos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCouponBeginTime() {
        return couponBeginTime;
    }

    public void setCouponBeginTime(String couponBeginTime) {
        this.couponBeginTime = couponBeginTime;
    }

    public String getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(String couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getChargePrice() {
        return chargePrice;
    }

    public void setChargePrice(BigDecimal chargePrice) {
        this.chargePrice = chargePrice;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Integer getCouponUseNumber() {
        return couponUseNumber;
    }

    public void setCouponUseNumber(Integer couponUseNumber) {
        this.couponUseNumber = couponUseNumber;
    }

    public Integer getCouponReceiveNumber() {
        return couponReceiveNumber;
    }

    public void setCouponReceiveNumber(Integer couponReceiveNumber) {
        this.couponReceiveNumber = couponReceiveNumber;
    }

    public BigDecimal getShouldChargeAmount() {
        return shouldChargeAmount;
    }

    public void setShouldChargeAmount(BigDecimal shouldChargeAmount) {
        this.shouldChargeAmount = shouldChargeAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getUseRatio() {
        return useRatio;
    }

    public void setUseRatio(Integer useRatio) {
        this.useRatio = useRatio;
    }
}