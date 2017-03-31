package com.mwb.controller.frontend.api;

import com.mwb.controller.api.ServiceResponse;

import java.util.List;

public class DataResponse extends ServiceResponse {

    private static final long serialVersionUID = 1L;

    private List<ResourceVO> activitys;
    private List<ResourceVO> hireTypes;
    private List<ResourceVO> productStatus;
    private List<ResourceVO> productTypes;
    private List<ResourceVO> storeTypes;
    private List<ResourceVO> employeeStatus;
    private List<ResourceVO> groups;

    public List<ResourceVO> getActivitys() {
        return activitys;
    }

    public void setActivitys(List<ResourceVO> activitys) {
        this.activitys = activitys;
    }

    public List<ResourceVO> getHireTypes() {
        return hireTypes;
    }

    public void setHireTypes(List<ResourceVO> hireTypes) {
        this.hireTypes = hireTypes;
    }

    public List<ResourceVO> getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(List<ResourceVO> productStatus) {
        this.productStatus = productStatus;
    }

    public List<ResourceVO> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ResourceVO> productTypes) {
        this.productTypes = productTypes;
    }

    public List<ResourceVO> getStoreTypes() {
        return storeTypes;
    }

    public void setStoreTypes(List<ResourceVO> storeTypes) {
        this.storeTypes = storeTypes;
    }

    public List<ResourceVO> getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(List<ResourceVO> employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public List<ResourceVO> getGroups() {
        return groups;
    }

    public void setGroups(List<ResourceVO> groups) {
        this.groups = groups;
    }
}
