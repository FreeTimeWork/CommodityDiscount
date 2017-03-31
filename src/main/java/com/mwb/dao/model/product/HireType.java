package com.mwb.dao.model.product;

import com.mwb.dao.model.comm.IdInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public enum  HireType implements IdInterface {
    DIRECTIONAL(1, "DIRECTIONAL", "定向"),
    GENERAL(2, "GENERAL", "通用"),
    Magpie(3, "Magpie", "鹊桥");

    private static final Map<String, HireType> code2HireType;

    private int id;
    private String code;
    private String description;

    static {
        code2HireType = new HashMap<String, HireType>();

        for (HireType appDeviceType : HireType.values()) {
            code2HireType.put(appDeviceType.getCode(), appDeviceType);
        }
    }

    public static HireType fromCode(String code) {
        return code2HireType.get(code);
    }

    HireType(int id, String code, String description) {
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
