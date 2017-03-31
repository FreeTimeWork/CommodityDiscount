package com.mwb.dao.model.product;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class Store implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer storeId; //卖家id
    private String qq;
    private BigDecimal descriptionScore;
    private BigDecimal serviceScore;
    private BigDecimal speedScore;
    private StoreType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public BigDecimal getDescriptionScore() {
        return descriptionScore;
    }

    public void setDescriptionScore(BigDecimal descriptionScore) {
        this.descriptionScore = descriptionScore;
    }

    public BigDecimal getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(BigDecimal serviceScore) {
        this.serviceScore = serviceScore;
    }

    public BigDecimal getSpeedScore() {
        return speedScore;
    }

    public void setSpeedScore(BigDecimal speedScore) {
        this.speedScore = speedScore;
    }

    public StoreType getType() {
        return type;
    }

    public void setType(StoreType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
