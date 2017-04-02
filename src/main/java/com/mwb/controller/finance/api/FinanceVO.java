package com.mwb.controller.finance.api;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class FinanceVO {
    private Integer id;
    private Integer ranking;
    private String employeeName;
    private Integer submitNumber;//提报数量
    private Integer averageDaily;//提报率
    private BigDecimal refuseRate;//拒绝率
    private Integer refuseNumber;//拒绝数量
    private Integer twoAuditNumber;//待二审数量
    private Integer promoteNumber;//推广中数量
    private Integer endNumber;//结束数量
    private Integer payWaitNumber;//代付款数量
    private Integer payRunNumber;//付款中数量
    private Integer payTrailerNumber;//拒绝付款数量
    private Integer payEndNumber;//已付款数量
    private BigDecimal guestUnitPrice;//客单价
    private BigDecimal actualChargeAmount;//实收金额
    private BigDecimal shouldChargeAmount;//应收金额

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getSubmitNumber() {
        return submitNumber;
    }

    public void setSubmitNumber(Integer submitNumber) {
        this.submitNumber = submitNumber;
    }

    public Integer getAverageDaily() {
        return averageDaily;
    }

    public void setAverageDaily(Integer averageDaily) {
        this.averageDaily = averageDaily;
    }

    public BigDecimal getRefuseRate() {
        return refuseRate;
    }

    public void setRefuseRate(BigDecimal refuseRate) {
        this.refuseRate = refuseRate;
    }

    public Integer getRefuseNumber() {
        return refuseNumber;
    }

    public void setRefuseNumber(Integer refuseNumber) {
        this.refuseNumber = refuseNumber;
    }

    public Integer getTwoAuditNumber() {
        return twoAuditNumber;
    }

    public void setTwoAuditNumber(Integer twoAuditNumber) {
        this.twoAuditNumber = twoAuditNumber;
    }

    public Integer getPromoteNumber() {
        return promoteNumber;
    }

    public void setPromoteNumber(Integer promoteNumber) {
        this.promoteNumber = promoteNumber;
    }

    public Integer getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(Integer endNumber) {
        this.endNumber = endNumber;
    }

    public Integer getPayWaitNumber() {
        return payWaitNumber;
    }

    public void setPayWaitNumber(Integer payWaitNumber) {
        this.payWaitNumber = payWaitNumber;
    }

    public Integer getPayRunNumber() {
        return payRunNumber;
    }

    public void setPayRunNumber(Integer payRunNumber) {
        this.payRunNumber = payRunNumber;
    }

    public Integer getPayTrailerNumber() {
        return payTrailerNumber;
    }

    public void setPayTrailerNumber(Integer payTrailerNumber) {
        this.payTrailerNumber = payTrailerNumber;
    }

    public Integer getPayEndNumber() {
        return payEndNumber;
    }

    public void setPayEndNumber(Integer payEndNumber) {
        this.payEndNumber = payEndNumber;
    }

    public BigDecimal getGuestUnitPrice() {
        return guestUnitPrice;
    }

    public void setGuestUnitPrice(BigDecimal guestUnitPrice) {
        this.guestUnitPrice = guestUnitPrice;
    }

    public BigDecimal getActualChargeAmount() {
        return actualChargeAmount;
    }

    public void setActualChargeAmount(BigDecimal actualChargeAmount) {
        this.actualChargeAmount = actualChargeAmount;
    }

    public BigDecimal getShouldChargeAmount() {
        return shouldChargeAmount;
    }

    public void setShouldChargeAmount(BigDecimal shouldChargeAmount) {
        this.shouldChargeAmount = shouldChargeAmount;
    }
}
