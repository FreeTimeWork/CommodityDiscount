package com.mwb.controller.employee.api;

import java.util.List;

import com.mwb.controller.position.api.PermissionVO;
import com.mwb.dao.model.employee.Employee;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by fangchen.chai on 2017/4/1.
 */
public class EmployeeVO {
    private Integer id;
    private String fullName;
    private String genderName;
    private String mobile;
    private String groupName;
    private Integer groupId;
    private Boolean businessPerson;
    private String positionName;
    private Integer positionId;
    private String statusCode;
    private String statusName;
    private List<PermissionVO> permissions;

    public static EmployeeVO toVO(Employee employee) {
        EmployeeVO vo = new EmployeeVO();
        vo.setId(employee.getId());
        vo.setMobile(employee.getMobile());
        vo.setFullName(employee.getFullName());
        vo.setGenderName(employee.getGender().getDescription());
        if (employee.getGroup() != null){
            vo.setGroupName(employee.getGroup().getName());
            vo.setGroupId(employee.getGroup().getId());
        }
        vo.setPositionName(employee.getPosition().getName());
        vo.setPositionId(employee.getPosition().getId());
        vo.setBusinessPerson(employee.getPosition().getId().equals(2));
        vo.setStatusCode(employee.getStatus().getCode());
        vo.setStatusName(employee.getStatus().getDescription());

        if (CollectionUtils.isNotEmpty(employee.getPosition().getPermissions())) {
            List<PermissionVO> vos = PermissionVO.toVOs(employee.getPosition().getPermissions());
            vo.setPermissions(vos);
        }
        return vo;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<PermissionVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionVO> permissions) {
        this.permissions = permissions;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
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
