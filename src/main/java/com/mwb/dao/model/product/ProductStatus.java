package com.mwb.dao.model.product;

import com.mwb.dao.model.comm.IdInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public enum ProductStatus implements IdInterface {
    AUDIT_WAIT(1, "AUDIT_WAIT", "待审核"),
    AUDIT_RUN(2, "AUDIT_RUN", "审核中"),
    REJECTED(3, "REJECTED", "驳回"),
    TRAILER(4, "TRAILER", "拒绝"),
    TWO_AUDIT(5, "TWO_AUDIT", "待二审"),
    PROMOTE(6, "PROMOTE", "推广中"),
    END_APPROACH(7, "END_APPROACH", "即将结束"),
    END(8, "END", "已结束"),
    PAY_WAIT(3, "PAY_WAIT", "代付款"),
    PAY_RUN(3, "PAY_RUN", "付款中"),
    PAY_TRAILER(3, "PAY_TRAILER", "拒绝付款"),
    PAY_END(3, "PAY_END", "已付款");

    private static final Map<String, ProductStatus> code2ProductStatus;
    private static final Map<Integer, ProductStatus> id2ProductStatus;

    private int id;
    private String code;
    private String description;

    static {
        code2ProductStatus = new HashMap<>();
        id2ProductStatus = new HashMap<>();

        for (ProductStatus appDeviceType : ProductStatus.values()) {
            code2ProductStatus.put(appDeviceType.getCode(), appDeviceType);
            id2ProductStatus.put(appDeviceType.getId(), appDeviceType);
        }
    }

    public static ProductStatus fromCode(String code) {
        return code2ProductStatus.get(code);
    }
    public static ProductStatus fromId(Integer id) {
        return id2ProductStatus.get(id);
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
