package com.mwb.dao.model.employee;

import java.io.Serializable;
import java.util.Date;

import com.mwb.dao.model.position.Position;

/**
 *
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String fullName;
    private Gender gender;
    private String mobile;
    private String password;
    private Date createTime;
    private Group group;
    private Position position;
    private EmployeeStatus status;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender=" + gender +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", group='" + group + '\'' +
                ", position=" + position +
                ", status=" + status +
                '}';
    }
}
