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
    private static final Map<Integer, HireType> id2HireType;

    private int id;
    private String code;
    private String description;

    static {
        code2HireType = new HashMap<>();
        id2HireType = new HashMap<>();

        for (HireType appDeviceType : HireType.values()) {
            code2HireType.put(appDeviceType.getCode(), appDeviceType);
            id2HireType.put(appDeviceType.getId(), appDeviceType);
        }
    }

    public static HireType fromCode(String code) {
        return code2HireType.get(code);
    }
    public static HireType fromId(Integer id) {
        return id2HireType.get(id);
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
