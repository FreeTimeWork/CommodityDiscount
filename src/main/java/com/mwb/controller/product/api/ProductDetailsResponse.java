package com.mwb.controller.product.api;

import com.mwb.controller.api.ServiceResponse;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductPicture;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class ProductDetailsResponse extends ServiceResponse {
    private static final long serialVersionUID = 1L;

    private Integer id;                 //编号
    private Integer activityId;            //活动类别id
    private String activityName;         //活动类别name
    private String productId;           //淘宝id
    private String createHistory;           //提交历史
    private String name;                //商品名称
    private String pictureUrl;             //商品主图
    private BigDecimal reservePrice;    //商品正常价格
    private Integer sales;              //商品月销量
    private String url;                 //商品链接
    private String activityTime;          //活动开始时间
    private Integer productTypeId;        //商品类型Id
    private String productTypeName;      //商品类型name
    private Integer statusId;             //商品状态Id
    private String statusName;             //商品状态name

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
    private String hireTypeName;        //佣金类型

    private Integer employeeId;          //员工id
    private String employeeName;        //员工name

    private String qq;
    private BigDecimal storeDescriptionScore; //店铺描述分数
    private BigDecimal serviceScore;        //店铺服务分数
    private BigDecimal speedScore;          //店铺配送分数
    private Integer storeTypeId;             //店铺类型
    private String storeTypeName;           //店铺类型
    private List<String> pictures;          // 图片链接

    public static ProductDetailsResponse toResponse(Product product) {
        ProductDetailsResponse response = new ProductDetailsResponse();

        if (product != null) {
            response.setId(product.getId());
            if (product.getActivity() != null) {
                response.setActivityId(product.getActivity().getId());
                response.setActivityName(product.getActivity().getDescription());
            }
            response.setActivityTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getActivityTime()));
            response.setProductId(product.getProductId());
            response.setName(product.getName());
            response.setPictureUrl(product.getPictureUrl());
            response.setReservePrice(product.getReservePrice());
            response.setSales(product.getSales());
            response.setUrl(product.getUrl());
            response.setImmediately(product.getImmediately() != null && product.getImmediately().getValue());
            if (product.getProductType() != null) {
                response.setProductTypeId(product.getProductType().getId());
                response.setProductTypeName(product.getProductType().getDescription());
            }
            if (product.getStatus() != null) {
                response.setStatusId(product.getStatus().getId());
                response.setStatusName(product.getStatus().getDescription());
            }
            response.setDiscountPrice(product.getDiscountPrice());
            response.setCouponAmount(product.getCouponAmount());
            response.setCouponUrl(product.getCouponUrl());
            response.setCouponBeginTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponBeginTime()));
            response.setCouponEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponEndTime()));
            response.setCouponUseNumber(product.getCouponReceiveNumber());
            response.setCouponSurplusNumber(product.getCouponSurplusNumber());
            response.setCondition(product.getCondition());
            response.setFeatures(product.getFeatures());
            response.setDescription(product.getDescription());
            response.setChargePrice(product.getChargePrice());
            response.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponEndTime()));
            response.setRatio(product.getRatio());
            response.setPlanUrl(product.getPlanUrl());
            if (product.getHireType() != null) {
                response.setHireTypeId(product.getHireType().getId());
                response.setHireTypeName(product.getHireType().getDescription());
            }
            if (product.getEmployee() != null) {
                //// TODO: 2017/4/1
//                response.setEmployeeId(product.getEmployee().getId());
                response.setEmployeeName(product.getEmployee().getFullName());
            }
            if (product.getStore() != null) {
                response.setQq(product.getStore().getQq());
                response.setStoreDescriptionScore(product.getStore().getDescriptionScore());
                response.setServiceScore(product.getStore().getServiceScore());
                response.setSpeedScore(product.getStore().getSpeedScore());
                if (product.getStore().getType() != null) {
                    response.setStoreTypeId(product.getStore().getType().getId());
                    response.setStoreTypeName(product.getStore().getType().getDescription());
                }
            }

            if (CollectionUtils.isNotEmpty(product.getPictures())) {
                List<String> pictures = new ArrayList<>();
                for (ProductPicture picture : product.getPictures()) {
                    pictures.add(picture.getUrl());
                }
                response.setPictures(pictures);
            }
        }

        return response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCreateHistory() {
        return createHistory;
    }

    public void setCreateHistory(String createHistory) {
        this.createHistory = createHistory;
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

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getHireTypeName() {
        return hireTypeName;
    }

    public void setHireTypeName(String hireTypeName) {
        this.hireTypeName = hireTypeName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
