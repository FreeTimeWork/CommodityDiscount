package com.mwb.controller.employee.api;

import java.util.ArrayList;
import java.util.List;

import com.mwb.dao.model.employee.Group;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
public class GroupVO {

    private Integer id;
    private String name;
    private String employeeId; //组长employeeId
    private String employeeName;

    public static List<GroupVO> toVOs(List<Group> groups) {
        List<GroupVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(groups)) {
            for (Group group : groups) {

                vos.add(toVO(group));
            }
        }
        return vos;
    }

    public static GroupVO toVO(Group group) {
        GroupVO vo = new GroupVO();
        vo.setId(group.getId());
        vo.setName(group.getName());
        vo.setEmployeeId(group.getEmployeeId());
        vo.setEmployeeName(group.getEmployeeName());
        return vo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
