package com.mwb.dao.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class Product {
    //Product
    private Integer id;//编号
    private Integer productId;//商品id
    private String name;//商品价格
    private BigDecimal amount;//商品价格
    private Bool immediately;//是否拍立减
    private Integer sales;//商品月销量
    private String couponUrl;//优惠券连接
    private BigDecimal serviceAmount;//服务价
    private Integer createById;//提交人Id
    private String createByName;//提交人
    private Date createByTime;//提交时间
    private BigDecimal couponAmount;//优惠券金额
    private Integer totalnumber;//初始数量
    private BigDecimal useDiscountAmount;//卷后价格
    private Date couponBeginTime;//优惠券开始时间
    private Date couponEndTime;//优惠券开始时间
    private Integer couponUseNumber;//领取数量
    private Integer couponSurpluNumber;//领取数量
    private String features;//特色
    private String description;//备注
    private String chargeAmount;//    收费单价
    private Activity activity;//    活动类别
    private ProductType type; //商品类型
    private ProductStatus status;//状态
    private Store store;//店铺
}
