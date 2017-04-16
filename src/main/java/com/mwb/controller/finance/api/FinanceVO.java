package com.mwb.controller.finance.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mwb.dao.model.finance.Finance;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class FinanceVO {
    private Integer id;
    private Integer ranking;
    private String employeeName;
    private Integer employeeId;
    private Integer submitNumber;//提报数量
    private Integer averageDaily;//提报率
    private Integer refuseRate;//拒绝率
    private Integer refuseNumber;//拒绝数量
    private Integer twoAuditNumber;//待二审数量
    private Integer promoteNumber;//推广中数量
    private Integer endApproachNumber;//即将结束数量
    private Integer endNumber;//结束数量
    private Integer payWaitNumber;//代付款数量
    private Integer payRunNumber;//付款中数量
    private Integer payTrailerNumber;//拒绝付款数量
    private Integer payEndNumber;//已付款数量
    private Integer settlementNumber;//已结账数量
    private BigDecimal guestUnitPrice;//客单价
    private BigDecimal actualChargeAmount;//实收金额
    private BigDecimal shouldChargeAmount;//应收金额

    public static FinanceVO toVO(Finance finance) {
        FinanceVO vo = new FinanceVO();
        vo.setId(finance.getId());
        vo.setEmployeeId(finance.getEmployee().getId());
        vo.setEmployeeName(finance.getEmployee().getFullName());
        vo.setSubmitNumber(finance.getSubmitNumber());
        vo.setAverageDaily(finance.getAverageDaily());
        vo.setRefuseNumber(finance.getRefuseNumber());
        vo.setRefuseRate(finance.getRefuseRate());
        vo.setTwoAuditNumber(finance.getTwoAuditNumber());
        vo.setPromoteNumber(finance.getPromoteNumber());
        vo.setEndApproachNumber(finance.getEndApproachNumber());
        vo.setEndNumber(finance.getEndNumber());
        vo.setPayWaitNumber(finance.getPayWaitNumber());
        vo.setPayRunNumber(finance.getPayRunNumber());
        vo.setPayEndNumber(finance.getPayEndNumber());
        vo.setSettlementNumber(finance.getSettlementNumber());
        vo.setPayTrailerNumber(finance.getPayTrailerNumber());
        vo.setGuestUnitPrice(finance.getGuestUnitPrice());
        vo.setActualChargeAmount(finance.getActualChargeAmount());
        vo.setShouldChargeAmount(finance.getShouldChargeAmount());

        return vo;
    }

    public static List<FinanceVO> toVOs(List<Finance> finances) {
        List<FinanceVO> vos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(finances)) {
            for (Finance finance : finances) {
                vos.add(toVO(finance));
            }
        }
        return vos;
    }

    public FinanceVO() {

        this.submitNumber = 0;
        this.averageDaily = 0;
        this.refuseRate = 0;
        this.refuseNumber = 0;
        this.twoAuditNumber = 0;
        this.promoteNumber = 0;
        this.endApproachNumber = 0;
        this.endNumber = 0;
        this.payWaitNumber = 0;
        this.payRunNumber = 0;
        this.payTrailerNumber = 0;
        this.payEndNumber = 0;
        this.settlementNumber = 0;
        this.guestUnitPrice = BigDecimal.ZERO;
        this.actualChargeAmount = BigDecimal.ZERO;
        this.shouldChargeAmount = BigDecimal.ZERO;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

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

    public Integer getRefuseRate() {
        return refuseRate;
    }

    public void setRefuseRate(Integer refuseRate) {
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

    public Integer getEndApproachNumber() {
        return endApproachNumber;
    }

    public void setEndApproachNumber(Integer endApproachNumber) {
        this.endApproachNumber = endApproachNumber;
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

    public Integer getSettlementNumber() {
        return settlementNumber;
    }

    public void setSettlementNumber(Integer settlementNumber) {
        this.settlementNumber = settlementNumber;
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
