package com.mwb.service.taobo.api;

import com.mwb.dao.model.product.Product;

import java.io.IOException;

/**
 * Created by MengWeiBo on 2017-04-12
 */
public interface ITaoBaoClient {

    //// TODO: 2017/4/12

    public AccessTokenMO getAccessToken();

    public ActivityTaoBaoMO getProductActivity(String activityId, String accessToken);

    public CouponTaoBaoMO getProductCoupon(String couponId, String accessToken);

    public ProductTaoBaoMO getProductMO(String productId, String accessToken);

    /**
     * 抓取商品和优惠卷信息
     * @param couponId
     * @param productId
     * @return
     */
    public Product getProduct(String productId, String couponId);

    /**
     * 抓取商品和优惠卷信息
     * @param productId
     * @return
     */
    public Product getProduct(String productId);

    /**
     * 抓取商品和优惠卷信息
     * @param couponId
     * @return
     */
    public Product getProductCoupon(String couponId);

}
