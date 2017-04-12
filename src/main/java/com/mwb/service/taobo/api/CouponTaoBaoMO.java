package com.mwb.service.taobo.api;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by MengWeiBo on 2017-04-12
 */
public class CouponTaoBaoMO {

    //taobao.promotion.coupons.get

    @JSONField(name = "coupon_id")
    private String activityId;
    @JSONField(name = "denominations")
    private String couponAmount;
    @JSONField(name = "creat_time")
    private String creatTime;
    @JSONField(name = "end_time")
    private String endTime;
    @JSONField(name = "condition")
    private Integer condition;
    @JSONField(name = "create_channel")
    private String createChannel;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(String couponAmount) {
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

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public String getCreateChannel() {
        return createChannel;
    }

    public void setCreateChannel(String createChannel) {
        this.createChannel = createChannel;
    }
}
