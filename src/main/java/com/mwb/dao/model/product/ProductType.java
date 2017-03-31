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
    COSMETICS(4, "COSMETICS", "化妆品"),
    OCCUPY(5, "OCCUPY", "居家"),
    SHOES(6, "SHOES", "鞋包配饰"),
    FOOD(7, "FOOD", "美食"),
    CAR(8, "CAR", "文体车品"),
    APPLIANCE(9, "APPLIANCE", "数码家电");

    private static final Map<String, ProductType> code2Activity;

    private int id;
    private String code;
    private String description;

    static {
        code2Activity = new HashMap<String, ProductType>();

        for (ProductType appDeviceType : ProductType.values()) {
            code2Activity.put(appDeviceType.getCode(), appDeviceType);
        }
    }

    public static ProductType fromCode(String code) {
        return code2Activity.get(code);
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

