package com.mwb.dao.model.employee;

import java.util.HashMap;
import java.util.Map;

import com.mwb.dao.model.comm.IdInterface;

/**
 * Created by Fangchen.chai on 2017/3/29.
 */
public enum EmployeeStatus implements IdInterface {
    IN_POSITION		(1, "IN_POSITION", "在职"),
    OUT_OF_POSITION	(2, "OUT_OF_POSITION", "离职");

    private static final Map<String, EmployeeStatus> code2EmployeeStatus;
    private static final Map<Integer, EmployeeStatus> id2EmployeeStatus;

    private int id;
    private String code;
    private String description;

    static {
        code2EmployeeStatus = new HashMap<String, EmployeeStatus>();
        id2EmployeeStatus =  new HashMap<Integer, EmployeeStatus>();

        for (EmployeeStatus employeeStatus : EmployeeStatus.values()) {
            code2EmployeeStatus.put(employeeStatus.getCode(), employeeStatus);
            id2EmployeeStatus.put( employeeStatus.getId(), employeeStatus);
        }

    }

    public static EmployeeStatus fromCode(String code) {
        if (code != null) {
            return code2EmployeeStatus.get(code);
        }else {
            return null;
        }
    }

    public static EmployeeStatus fromId(Integer id) {
        if (id != null) {
            return id2EmployeeStatus.get(id);
        }else {
            return null;
        }
    }

    EmployeeStatus(int id, String code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
