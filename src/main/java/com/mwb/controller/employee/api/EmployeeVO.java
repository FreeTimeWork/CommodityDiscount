package com.mwb.controller.employee.api;

import com.mwb.dao.model.employee.Employee;

/**
 * Created by fangchen.chai on 2017/4/1.
 */
public class EmployeeVO {
    private Integer id;
    private String fullName;
    private String genderName;
    private String mobile;
    private String groupName;
    private Boolean businessPerson;
    private String positionName;

    public static EmployeeVO toVO(Employee employee) {
        EmployeeVO vo = new EmployeeVO();
        vo.setId(employee.getId());
        vo.setMobile(employee.getMobile());
        vo.setFullName(employee.getFullName());
        vo.setGenderName(employee.getGender().name());
        if (employee.getGroup() != null){
            vo.setGroupName(employee.getGroup().getName());
        }
        vo.setPositionName(employee.getPosition().getName());

        vo.setBusinessPerson(employee.getPosition().getId().equals(2));
        return vo;
    }

    public Boolean getBusinessPerson() {
        return businessPerson;
    }

    public void setBusinessPerson(Boolean businessPerson) {
        this.businessPerson = businessPerson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
