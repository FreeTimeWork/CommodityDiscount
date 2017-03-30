package com.mwb.dao.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class Product implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer id;                 //编号
    private String productId;          //商品id
    private String name;                //商品价格
    private BigDecimal amount;          //商品价格
    private Bool immediately;           //是否拍立减
    private Integer sales;              //商品月销量
    private String couponUrl;           //优惠券连接
    private BigDecimal serviceAmount;   //服务价
    private Integer createById;         //提交人Id
    private String createByName;        //提交人
    private Date createByTime;          //提交时间
    private BigDecimal couponAmount;    //优惠券金额
    private Integer totalnumber;        //初始数量
    private BigDecimal useDiscountAmount;//卷后价格
    private Date couponBeginTime;       //优惠券开始时间
    private Date couponEndTime;         //优惠券开始时间
    private Integer couponUseNumber;    //领取数量
    private Integer couponSurpluNumber; //领取数量
    private String features;            //特色
    private String description;         //备注
    private String chargeAmount;        //收费单价
    private Activity activity;          //活动类别
    private ProductType type;           //商品类型
    private ProductStatus status;       //状态
    private Store store;                //店铺
    private List<ProductPicture> pictures;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Bool getImmediately() {
        return immediately;
    }

    public void setImmediately(Bool immediately) {
        this.immediately = immediately;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getCouponUrl() {
        return couponUrl;
    }

    public void setCouponUrl(String couponUrl) {
        this.couponUrl = couponUrl;
    }

    public BigDecimal getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(BigDecimal serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public Integer getCreateById() {
        return createById;
    }

    public void setCreateById(Integer createById) {
        this.createById = createById;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateByTime() {
        return createByTime;
    }

    public void setCreateByTime(Date createByTime) {
        this.createByTime = createByTime;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Integer getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(Integer totalnumber) {
        this.totalnumber = totalnumber;
    }

    public BigDecimal getUseDiscountAmount() {
        return useDiscountAmount;
    }

    public void setUseDiscountAmount(BigDecimal useDiscountAmount) {
        this.useDiscountAmount = useDiscountAmount;
    }

    public Date getCouponBeginTime() {
        return couponBeginTime;
    }

    public void setCouponBeginTime(Date couponBeginTime) {
        this.couponBeginTime = couponBeginTime;
    }

    public Date getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(Date couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public Integer getCouponUseNumber() {
        return couponUseNumber;
    }

    public void setCouponUseNumber(Integer couponUseNumber) {
        this.couponUseNumber = couponUseNumber;
    }

    public Integer getCouponSurpluNumber() {
        return couponSurpluNumber;
    }

    public void setCouponSurpluNumber(Integer couponSurpluNumber) {
        this.couponSurpluNumber = couponSurpluNumber;
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

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<ProductPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ProductPicture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
