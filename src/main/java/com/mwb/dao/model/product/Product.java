package com.mwb.dao.model.product;

import com.mwb.dao.model.comm.Bool;
import com.mwb.dao.model.employee.Employee;
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
    private String taoKeId;             //大淘客id
    private String productId;           //淘宝id
    private String name;                //商品名称
    private String picture;             //商品主图
    private BigDecimal reservePrice;    //商品正常价格
    private Integer sales;              //商品月销量
    private String url;                 //商品链接
    private Date activityTime;          //活动开始时间
    private BigDecimal descriptionScore; //商品描述分

    private Bool immediately;           //是否拍立减
    private BigDecimal discountPrice;   //卷后价格
    private BigDecimal couponAmount;    //优惠券金额
    private String couponUrl;           //优惠券连接
    private Date couponBeginTime;       //优惠券开始时间
    private Date couponEndTime;         //优惠券开始时间
    private Integer totalNumber;        //初始数量
    private Integer couponUseNumber;    //领取数量
    private Integer couponSurplusNumber; //剩余数量
    private String condition;           //使用条件

    private String features;            //特色
    private String description;         //备注
    private String chargeAmount;        //收费单价
    private Integer createById;         //提交人Id
    private String createByName;        //提交人
    private Date createByTime;          //提交时间

    private BigDecimal ratio;           //佣金比例
    private String planUrl;             //计划链接
    private HireType hireType;          //佣金类型

    private Employee employee;          //员工
    private Hire hire;                  //佣金
    private Store store;                //店铺
    private ProductStatus status;       //状态
    private Activity activity;          //活动类别
    private ProductType productType;           //商品类型
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

    public BigDecimal getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(BigDecimal descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public String getTaoKeId() {
        return taoKeId;
    }

    public void setTaoKeId(String taoKeId) {
        this.taoKeId = taoKeId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public Bool getImmediately() {
        return immediately;
    }

    public void setImmediately(Bool immediately) {
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

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
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

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
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

    public Hire getHire() {
        return hire;
    }

    public void setHire(Hire hire) {
        this.hire = hire;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public HireType getHireType() {
        return hireType;
    }

    public void setHireType(HireType hireType) {
        this.hireType = hireType;
    }

    public String getPlanUrl() {
        return planUrl;
    }

    public void setPlanUrl(String planUrl) {
        this.planUrl = planUrl;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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
