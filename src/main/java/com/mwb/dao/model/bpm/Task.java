package com.mwb.dao.model.bpm;


import java.util.List;

/**
 * Created by Fangchen.chai on 2017/4/5.
 */
public class Task {
    private Integer id;
    private Integer employeeId;
    private List<Variable> variables;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public List<Variable> getVariables() {
        return variables;
    }

    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
}
