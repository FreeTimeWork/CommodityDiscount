package com.mwb.dao.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public enum ProductStatus implements IdInterface {
    ORDINARY(1, "ORDINARY", "普通活动"),
    ROB(1, "ROB", "淘抢购"),
    BARGAIN(1, "BARGAIN", "聚划算"),
    TRAILER(2, "TRAILER", "预告商品");

    private static final Map<String, ProductStatus> code2Activity;

    private int id;
    private String code;
    private String description;

    static {
        code2Activity = new HashMap<String, ProductStatus>();

        for (ProductStatus appDeviceType : ProductStatus.values()) {
            code2Activity.put(appDeviceType.getCode(), appDeviceType);
        }
    }

    public static ProductStatus fromCode(String code) {
        return code2Activity.get(code);
    }

    ProductStatus(int id, String code, String description) {
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
