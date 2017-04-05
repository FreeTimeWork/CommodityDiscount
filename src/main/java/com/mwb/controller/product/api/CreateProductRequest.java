package com.mwb.controller.product.api;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class CreateProductRequest {

    private Integer activityId;            //活动类别id
    private String productId;           //淘宝id
    private String name;                //商品名称
    private String pictureUrl;             //商品主图
    private String supplementPictureUrl;  //补充主图
    private BigDecimal reservePrice;    //商品正常价格
    private Integer sales;              //商品月销量
    private String url;                 //商品链接
    private String activityTime;          //活动开始时间
    private Integer productTypeId;        //商品类型Id

    private Boolean immediately;         //是否拍立减
    private BigDecimal discountPrice;   //卷后价格
    private BigDecimal couponAmount;    //优惠券金额
    private String couponUrl;           //优惠券连接
    private String couponBeginTime;       //优惠券开始时间
    private String couponEndTime;         //优惠券开始时间
    private Integer couponUseNumber;    //领取数量
    private Integer couponSurplusNumber; //剩余数量
    private String condition;           //使用条件

    private String features;            //特色
    private String description;         //备注
    private BigDecimal chargePrice;        //收费单价
    private String createTime;          //提交时间

    private BigDecimal ratio;           //佣金比例
    private String planUrl;             //计划链接
    private Integer hireTypeId;          //佣金类型

    private String qq;
    private BigDecimal storeDescriptionScore; //店铺描述分数
    private BigDecimal serviceScore;        //店铺服务分数
    private BigDecimal speedScore;          //店铺配送分数
    private Integer storeTypeId;             //店铺类型
    private List<String> pictures;          // 图片链接

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getSupplementPictureUrl() {
        return supplementPictureUrl;
    }

    public void setSupplementPictureUrl(String supplementPictureUrl) {
        this.supplementPictureUrl = supplementPictureUrl;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Boolean getImmediately() {
        return immediately;
    }

    public void setImmediately(Boolean immediately) {
        this.immediately = immediately;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCouponUrl() {
        return couponUrl;
    }

    public void setCouponUrl(String couponUrl) {
        this.couponUrl = couponUrl;
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

    public Integer getCouponUseNumber() {
        return couponUseNumber;
    }

    public void setCouponUseNumber(Integer couponUseNumber) {
        this.couponUseNumber = couponUseNumber;
    }

    public Integer getCouponSurplusNumber() {
        return couponSurplusNumber;
    }

    public void setCouponSurplusNumber(Integer couponSurplusNumber) {
        this.couponSurplusNumber = couponSurplusNumber;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getChargePrice() {
        return chargePrice;
    }

    public void setChargePrice(BigDecimal chargePrice) {
        this.chargePrice = chargePrice;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public String getPlanUrl() {
        return planUrl;
    }

    public void setPlanUrl(String planUrl) {
        this.planUrl = planUrl;
    }

    public Integer getHireTypeId() {
        return hireTypeId;
    }

    public void setHireTypeId(Integer hireTypeId) {
        this.hireTypeId = hireTypeId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public BigDecimal getStoreDescriptionScore() {
        return storeDescriptionScore;
    }

    public void setStoreDescriptionScore(BigDecimal storeDescriptionScore) {
        this.storeDescriptionScore = storeDescriptionScore;
    }

    public BigDecimal getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(BigDecimal serviceScore) {
        this.serviceScore = serviceScore;
    }

    public BigDecimal getSpeedScore() {
        return speedScore;
    }

    public void setSpeedScore(BigDecimal speedScore) {
        this.speedScore = speedScore;
    }

    public Integer getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(Integer storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
