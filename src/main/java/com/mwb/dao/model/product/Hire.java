package com.mwb.dao.model.product;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class Hire implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private BigDecimal ratio;           //佣金比例
    private String planUrl;             //计划链接
    private HireType type;              //佣金类型

    //// TODO: 2017/3/31
    private BigDecimal receiveAmount;   //应收金额
    private BigDecimal paymentAmount;   //付款金额
    private BigDecimal useRatio;        //使用率

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getUseRatio() {
        return useRatio;
    }

    public void setUseRatio(BigDecimal useRatio) {
        this.useRatio = useRatio;
    }

    public HireType getType() {
        return type;
    }

    public void setType(HireType type) {
        this.type = type;
    }
}
