package com.mwb.dao.model.product;

import com.mwb.dao.model.comm.IdInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public enum ProductType implements IdInterface {
    WOMAN(1, "WOMAN", "女裝"),
    MAN(2, "MAN", "男裝"),
    UNDERWEAR(3, "UNDERWEAR", "內衣"),
    MATERNAL(4, "MATERNAL", "母婴"),
    COSMETICS(5, "COSMETICS", "化妆品"),
    OCCUPY(6, "OCCUPY", "居家"),
    SHOES(7, "SHOES", "鞋包配饰"),
    FOOD(8, "FOOD", "美食"),
    CAR(9, "CAR", "文体车品"),
    APPLIANCE(10, "APPLIANCE", "数码家电");

    private static final Map<String, ProductType> code2Activity;
    private static final Map<Integer, ProductType> id2Activity;

    private int id;
    private String code;
    private String description;

    static {
        code2Activity = new HashMap<>();
        id2Activity = new HashMap<>();

        for (ProductType appDeviceType : ProductType.values()) {
            code2Activity.put(appDeviceType.getCode(), appDeviceType);
            id2Activity.put(appDeviceType.getId(), appDeviceType);
        }
    }

    public static ProductType fromCode(String code) {
        return code2Activity.get(code);
    }

    public static ProductType fromId(Integer id) {
        return id2Activity.get(id);
    }

    ProductType(int id, String code, String description) {
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

