package com.mwb.dao.model.product;

import com.mwb.dao.model.comm.IdInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public enum Activity implements IdInterface {
    ORDINARY(1, "ORDINARY", "普通活动"),
    ROB(2, "ROB", "淘抢购"),
    BARGAIN(3, "BARGAIN", "聚划算"),
    TRAILER(4, "TRAILER", "预告商品");

    private static final Map<String, Activity> code2Activity;

    private int id;
    private String code;
    private String description;

    static {
        code2Activity = new HashMap<String, Activity>();

        for (Activity appDeviceType : Activity.values()) {
            code2Activity.put(appDeviceType.getCode(), appDeviceType);
        }
    }

    public static Activity fromCode(String code) {
        return code2Activity.get(code);
    }

    Activity(int id, String code, String description) {
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
