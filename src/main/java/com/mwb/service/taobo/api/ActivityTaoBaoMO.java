package com.mwb.service.taobo.api;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by mwb on 2017/3/31 0031.
 */
public class ActivityTaoBaoMO {

    @JSONField(name = "activity_id")
    private String activityId;
    @JSONField(name = "coupon_id")
    private String coupon_id;
    @JSONField(name = "total_count")
    private Integer couponTotal;
    @JSONField(name = "applied_count")
    private Integer couponReceiveNumber;
    @JSONField(name = "person_limit_count")
    private String personLimitCount;
    @JSONField(name = "status")
    private String status;
    @JSONField(name = "activity_url")
    private String activityUrl;
    @JSONField(name = "create_user")
    private String createUser;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public Integer getCouponTotal() {
        return couponTotal;
    }

    public void setCouponTotal(Integer couponTotal) {
        this.couponTotal = couponTotal;
    }

    public Integer getCouponReceiveNumber() {
        return couponReceiveNumber;
    }

    public void setCouponReceiveNumber(Integer couponReceiveNumber) {
        this.couponReceiveNumber = couponReceiveNumber;
    }

    public String getPersonLimitCount() {
        return personLimitCount;
    }

    public void setPersonLimitCount(String personLimitCount) {
        this.personLimitCount = personLimitCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
