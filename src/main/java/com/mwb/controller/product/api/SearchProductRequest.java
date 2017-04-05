package com.mwb.controller.product.api;

import com.mwb.controller.api.PagingRequest;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class SearchProductRequest extends PagingRequest {
    private Integer id;
    private String productId;
    private String name;
    private Integer statusId;
    private Integer typeId;
    private Integer groupId;
    private Integer employeeId;
    private Boolean orderAsc;
    private String createBeginTime;
    private String createEndTime;
    private String beginFromTime;
    private String beginToTime;
    private String endFromTime;
    private String endToTime;
    private Integer useMinNumber;
    private Integer useMaxNumber;
    private Integer surplusMinNumber;
    private Integer surplusMaxNumber;

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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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

    public String getCreateBeginTime() {
        return createBeginTime;
    }

    public void setCreateBeginTime(String createBeginTime) {
        this.createBeginTime = createBeginTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getBeginFromTime() {
        return beginFromTime;
    }

    public void setBeginFromTime(String beginFromTime) {
        this.beginFromTime = beginFromTime;
    }

    public String getBeginToTime() {
        return beginToTime;
    }

    public void setBeginToTime(String beginToTime) {
        this.beginToTime = beginToTime;
    }

    public String getEndFromTime() {
        return endFromTime;
    }

    public void setEndFromTime(String endFromTime) {
        this.endFromTime = endFromTime;
    }

    public String getEndToTime() {
        return endToTime;
    }

    public void setEndToTime(String endToTime) {
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
}
