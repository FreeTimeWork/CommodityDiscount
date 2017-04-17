package com.mwb.service.taobo.api;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2017-04-12
 */
public class CouponTaoBaoMO {

    //taobao.promotion.coupons.get

    @JSONField(name = "coupon_id")
    private String activityId;
    @JSONField(name = "denominations")
    private BigDecimal couponAmount;
    @JSONField(name = "creat_time")
    private String creatTime;
    @JSONField(name = "end_time")
    private String endTime;
    @JSONField(name = "condition")
    private String condition;
    @JSONField(name = "create_channel")
    private String createChannel;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCreateChannel() {
        return createChannel;
    }

    public void setCreateChannel(String createChannel) {
        this.createChannel = createChannel;
    }
}
