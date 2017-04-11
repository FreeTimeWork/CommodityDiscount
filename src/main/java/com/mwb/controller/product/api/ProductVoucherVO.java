package com.mwb.controller.product.api;

import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private BigDecimal payAmount;//应收金额
    private BigDecimal shouldChargeAmount;//应收金额
    private BigDecimal actualChargeAmount;       //付款金额
    private BigDecimal conversionRate;        //使用率
    private String payTime;        //使用率
    private Integer useRatio;        //使用率
    private boolean showSubmit;        //使用率
    private List<String> pictures;        //使用率

    public static ProductVoucherVO toVO(ProductVoucher voucher,Employee employee) {
        if (voucher == null) {
            return null;
        }
        ProductVoucherVO vo = new ProductVoucherVO();
        Product product = voucher.getProduct();
        vo.setId(voucher.getId());
        vo.setEmployeeName(product.getEmployee().getFullName());
        vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCreateTime()));
        vo.setCouponBeginTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponBeginTime()));
        vo.setCouponEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponEndTime()));
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
        vo.setActualChargeAmount(voucher.getActualChargeAmount());
        vo.setConversionRate(voucher.getConversionRate());
        vo.setPayTime(DateTimeUtility.formatYYYYMMDD(voucher.getPayTime()));
        if (voucher.getReceiveNumber().equals(0)) {
            vo.setUseRatio(0);
        }else {
            vo.setUseRatio(voucher.getUseNumber() * 100 / voucher.getReceiveNumber());
        }
        if (employee != null &&
                (voucher.getProduct().getStatus() == ProductStatus.PAY_RUN)
                && (employee.getPosition().getId().equals(1)||employee.getPosition().getId().equals(5))){
            vo.setShowSubmit(true);
        }
        List<String> pictures = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(voucher.getPictures())) {
            for (VoucherPicture picture : voucher.getPictures()) {
                pictures.add(picture.getUrl());
            }
        }
        vo.setPictures(pictures);
        return vo;
    }

    public static List<ProductVoucherVO> toVOs(List<ProductVoucher> vouchers, Employee employee) {
        List<ProductVoucherVO> vos = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(vouchers)) {
            for (ProductVoucher voucher : vouchers) {
                vos.add(toVO(voucher, employee));
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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Integer getUseRatio() {
        return useRatio;
    }

    public boolean getShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(boolean showSubmit) {
        this.showSubmit = showSubmit;
    }

    public void setUseRatio(Integer useRatio) {
        this.useRatio = useRatio;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
