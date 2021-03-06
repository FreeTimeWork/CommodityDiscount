package com.mwb.controller.product.api;

import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class ProductVO {

    private Integer id;                 //编号
    private String createTime;          //提交时间
    private String pictureUrl;             //商品主图
    private String productId;           //淘宝id
    private String name;                //商品名称
    private BigDecimal chargePrice;        //收费单价
    private BigDecimal discountPrice;   //卷后价格
    private BigDecimal ratio;           //佣金比例
    private String hireTypeName;          //佣金类型
    private String hireTypeCode;          //佣金类型
    private String planUrl;             //计划链接
    private String activityName;          //活动类别
    private String couponBeginTime;       //优惠券开始时间
    private String couponEndTime;         //优惠券开始时间
    private Integer couponUseNumber;    //领取数量
    private Integer couponSurplusNumber; //剩余数量
    private String employeeName;          //提交人
    private String storeTypeName;          //提交人
    private String qq;                  //qq
    private String status;              //状态
    private String statusCode;              //状态Code
    private boolean showSubmit;              //状态提交
    private boolean showReceive;              //状态提交

    public static List<ProductVO> toVOs(List<Product> products, Employee employee) {
        List<ProductVO> productVOs = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(products)) {
            for (Product product : products) {
                productVOs.add(toVO(product, employee));
            }
        }
        return productVOs;
    }

    public static ProductVO toVO(Product product, Employee employee) {
        ProductVO vo = new ProductVO();

        vo.setId(product.getId());
        vo.setCreateTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCreateTime()));
        vo.setPictureUrl(product.getPictureUrl());
        vo.setProductId(product.getProductId());
        vo.setName(product.getName());
        vo.setChargePrice(product.getChargePrice());
        vo.setDiscountPrice(product.getDiscountPrice());
        vo.setRatio(product.getRatio());
        vo.setHireTypeName(product.getHireType().getDescription());
        vo.setHireTypeCode(product.getHireType().getCode());
        vo.setPlanUrl(product.getPlanUrl());
        vo.setActivityName(product.getActivity().getDescription());
        vo.setCouponBeginTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponBeginTime()));
        vo.setCouponEndTime(DateTimeUtility.formatYYYYMMDDHHMMSS(product.getCouponEndTime()));
        vo.setCouponUseNumber(product.getCouponReceiveNumber());
        vo.setCouponSurplusNumber(product.getCouponSurplusNumber());
        vo.setQq(product.getStore().getQq());
        vo.setEmployeeName(product.getEmployee().getFullName());
        vo.setStatus(product.getStatus().getDescription());
        vo.setStatusCode(product.getStatus().getCode());
        vo.setStoreTypeName(product.getStore().getType().getDescription());
        if (product.getEmployee().getId().equals(employee.getId()) || employee.getPosition().getId().equals(1)) {
            vo.setShowSubmit(true);
        }
        if (product.getStatus() == ProductStatus.AUDIT_WAIT &&
                (employee.getPosition().getId().equals(1) || employee.getPosition().getId().equals(4))) {
            vo.setShowReceive(true);
        }
        return vo;
    }

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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

    public BigDecimal getChargePrice() {
        return chargePrice;
    }

    public void setChargePrice(BigDecimal chargePrice) {
        this.chargePrice = chargePrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public String getHireTypeName() {
        return hireTypeName;
    }

    public void setHireTypeName(String hireTypeName) {
        this.hireTypeName = hireTypeName;
    }

    public String getPlanUrl() {
        return planUrl;
    }

    public void setPlanUrl(String planUrl) {
        this.planUrl = planUrl;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHireTypeCode() {
        return hireTypeCode;
    }

    public void setHireTypeCode(String hireTypeCode) {
        this.hireTypeCode = hireTypeCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isShowSubmit() {
        return showSubmit;
    }

    public void setShowSubmit(boolean showSubmit) {
        this.showSubmit = showSubmit;
    }

    public boolean isShowReceive() {
        return showReceive;
    }

    public void setShowReceive(boolean showReceive) {
        this.showReceive = showReceive;
    }
}
