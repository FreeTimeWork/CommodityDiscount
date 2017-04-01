package com.mwb.service.dataoke;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mwb.dao.model.comm.Log;
import com.mwb.dao.model.product.*;
import com.mwb.http.AbstractHttpClient;
import com.mwb.service.dataoke.api.IDaoLaoKeService;
import com.mwb.service.dataoke.api.ProductMO;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

/**
 *  Created by mwb on 2017/3/31 0031.
 */

public class DaoLaoKeService extends AbstractHttpClient implements IDaoLaoKeService{
    private static final Log LOG = Log.getLog(DaoLaoKeService.class);

    @Value("${app.key:8jdrk90okh}")
    private String appKey;

    private static String detailUrl = "http://api.dataoke.com/index.php?r=port/index";

    public Product getParsProduct(String productId){
        LOG.info("get productId discount info.");
        ProductMO mo = getProductMO(productId);
        Product product;
        try {
            product = toProduct(mo);
        } catch (Exception e) {
            LOG.error("get productId discount info toProduct is err:{}", mo);
            e.printStackTrace();

            product = new Product();
        }

        return product;
    }

    private ProductMO getProductMO(String productId){
        try {
            String jsonResult = get(getUrl(productId),null,null);
            JSONObject jsonObject = JSON.parseObject(jsonResult);

            String result = jsonObject.getString("result");
            if (StringUtils.isBlank(result)){
                return null;
            }

            ProductMO mo = JSONObject.parseObject(result, ProductMO.class);

            LOG.error("get productId discount info mo:{}", mo);
            return mo;
        } catch (Exception e) {
            LOG.error("get productId discount info is err.");
            e.printStackTrace();
            return null;
        }
    }

    private Product toProduct(ProductMO mo) throws Exception {
        Product product = new Product();
        Store store = new Store();

        product.setTaoKeId(mo.getTaoKeId());
        product.setProductId(mo.getProductId());
        product.setName(mo.getName());
        product.setPicture(mo.getPicture());
        product.setProductType(ProductType.fromId(mo.getTypeId()));
        product.setReservePrice(mo.getReservePrice());
        product.setDiscountPrice(mo.getDiscountPrice());
        store.setStoreId(mo.getStoreId());
        store.setType(mo.getIsTmall() == 1 ? StoreType.TMALL : StoreType.TAOBAO);
        product.setSales(mo.getSales());
        product.setDescriptionScore(mo.getDescriptionScore());
        if (mo.getGeneral().compareTo(BigDecimal.ZERO) > 0){
            product.setHireType(HireType.GENERAL);
            product.setRatio(mo.getGeneral());
        }else {
            product.setHireType(HireType.Magpie);
            product.setRatio(mo.getMagpie());
        }
        product.setPlanUrl(mo.getPlanUrl());
        product.setCouponAmount(mo.getCouponAmount());
        product.setCouponEndTime(DateTimeUtility.parseYYYYMMDDHHMMSS(mo.getCouponEndTime()));
        product.setCouponSurplusNumber(mo.getCouponSurplusNumber());
        product.setCouponUseNumber(mo.getCouponUseNumber());
        product.setCondition(mo.getCondition());

        return  product;
    }
    private String getUrl(String productId){
        return detailUrl + "&appkey=" + appKey + "&v=1&id=" + productId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
