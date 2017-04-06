package com.mwb.dao.filter;

import com.mwb.dao.model.product.ProductStatus;
import com.mwb.dao.model.product.ProductType;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-03-31
 */
public class ProductFilter extends SearchFilter {

    private Integer id;
    private String productId;
    private String name;
    private Integer groupId;
    private Integer employeeId;
    private Boolean orderAsc;
    private Date createBeginTime;
    private Date createEndTime;
    private Date beginFromTime;
    private Date beginToTime;
    private Date endFromTime;
    private Date endToTime;
    private Integer receiveMinNumber;
    private Integer receiveMaxNumber;
    private Integer surplusMinNumber;
    private Integer surplusMaxNumber;
    private BigDecimal minDiscountPrice;   //卷后价格
    private BigDecimal maxDiscountPrice;   //卷后价格
    private BigDecimal minChargePrice;        //服务费用
    private BigDecimal maxChargePrice;        //服务费用

    private BigDecimal minPayPrice;   //付款价格
    private BigDecimal maxPayPrice;   //付款价格
    private ProductType type;
    private ProductStatus status;
    private List<ProductStatus> excludeStatus;
    private List<ProductStatus> includeStatus;

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
        if (StringUtils.isNotBlank(name)) {
            this.name = "%" + name + "%";
        }
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

    public Boolean getOrderAsc() {
        return orderAsc;
    }

    public void setOrderAsc(Boolean orderAsc) {
        this.orderAsc = orderAsc;
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

    public Integer getReceiveMinNumber() {
        return receiveMinNumber;
    }

    public void setReceiveMinNumber(Integer receiveMinNumber) {
        this.receiveMinNumber = receiveMinNumber;
    }

    public Integer getReceiveMaxNumber() {
        return receiveMaxNumber;
    }

    public void setReceiveMaxNumber(Integer receiveMaxNumber) {
        this.receiveMaxNumber = receiveMaxNumber;
    }

    public Integer getSurplusMinNumber() {
        return surplusMinNumber;
    }

    public void setSurplusMinNumber(Integer surplusMinNumber) {
        this.surplusMinNumber = surplusMinNumber;
    }

    public Integer getSurplusMaxNumber() {
        return surplusMaxNumber;
    }

    public void setSurplusMaxNumber(Integer surplusMaxNumber) {
        this.surplusMaxNumber = surplusMaxNumber;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<ProductStatus> getIncludeStatus() {
        return includeStatus;
    }

    public void setIncludeStatus(List<ProductStatus> includeStatus) {
        this.includeStatus = includeStatus;
    }

    public List<ProductStatus> getExcludeStatus() {
        return excludeStatus;
    }

    public void setExcludeStatus(List<ProductStatus> excludeStatus) {
        this.excludeStatus = excludeStatus;
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
}
