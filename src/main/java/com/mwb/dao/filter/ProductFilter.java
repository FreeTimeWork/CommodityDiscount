package com.mwb.dao.filter;

import com.mwb.dao.model.product.ProductStatus;
import com.mwb.dao.model.product.ProductType;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-03-31
 */
public class ProductFilter extends SearchFilter {

    private Integer id;
    private Integer productId;
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
    private Integer useMinNumber;
    private Integer useMaxNumber;
    private Integer surplusMinNumber;
    private Integer surplusMaxNumber;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
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

    public Integer getUseMinNumber() {
        return useMinNumber;
    }

    public void setUseMinNumber(Integer useMinNumber) {
        this.useMinNumber = useMinNumber;
    }

    public Integer getUseMaxNumber() {
        return useMaxNumber;
    }

    public void setUseMaxNumber(Integer useMaxNumber) {
        this.useMaxNumber = useMaxNumber;
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
}
