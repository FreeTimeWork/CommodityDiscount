package com.mwb.dao.model.product;

import com.mwb.dao.model.comm.IdInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public enum StoreType implements IdInterface {
    TAOBAO(1, "TAOBAO", "淘宝"),
    TMALL(2, "TMALL", "天猫");

    private static final Map<String, StoreType> code2StoreType;

    private int id;
    private String code;
    private String description;

    static {
        code2StoreType = new HashMap<String, StoreType>();

        for (StoreType appDeviceType : StoreType.values()) {
            code2StoreType.put(appDeviceType.getCode(), appDeviceType);
        }
    }

    public static StoreType fromCode(String code) {
        return code2StoreType.get(code);
    }

    StoreType(int id, String code, String description) {
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
