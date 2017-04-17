package com.mwb.controller.frontend.api;

import java.util.List;

import com.mwb.controller.api.ServiceResponse;

public class DataResponse extends ServiceResponse {

    private static final long serialVersionUID = 1L;

    private List<ResourceVO> activities;
    private List<ResourceVO> hireTypes;
    private List<ResourceVO> productStatus;
    private List<ResourceVO> productTypes;
    private List<ResourceVO> storeTypes;
    private List<ResourceVO> employeeStatus;
    private List<ResourceVO> groups;
    private List<ResourceVO> positions;
    private List<ResourceVO> employees;
    private List<ResourceVO> conditions;

    public List<ResourceVO> getActivities() {
        return activities;
    }

    public void setActivities(List<ResourceVO> activities) {
        this.activities = activities;
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

    public List<ResourceVO> getPositions() {
        return positions;
    }

    public void setPositions(List<ResourceVO> positions) {
        this.positions = positions;
    }

    public List<ResourceVO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<ResourceVO> employees) {
        this.employees = employees;
    }

    public List<ResourceVO> getConditions() {
        return conditions;
    }

    public void setConditions(List<ResourceVO> conditions) {
        this.conditions = conditions;
    }
}
