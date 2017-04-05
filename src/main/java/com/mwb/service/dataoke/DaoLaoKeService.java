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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by MengWeiBo on 2017-03-29
 */

@Service("daoLaoKeService")
public class DaoLaoKeService extends AbstractHttpClient implements IDaoLaoKeService {
    private static final Log LOG = Log.getLog(DaoLaoKeService.class);

    @Value("8jdrk90okh")
    private String appKey;

    private static String detailUrl = "http://api.dataoke.com/index.php?r=port/index";

    public void setDaTaoKeProduct(Product product) {
        LOG.info("get productId discount info.");
        ProductMO mo = getProductMO(product.getProductId());

        try {
            toProduct(mo, product);
        } catch (Exception e) {
            LOG.error("get productId discount info toProduct is err:{}", mo);
        }
    }

    @Override
    public Product getDaTaoKeProduct(String productId) {
        LOG.info("get productId discount info.");
        ProductMO mo = getProductMO(productId);
        if (mo == null) {
            return null;
        }
        Product product = new Product();
        try {
            toProduct(mo, product);
        } catch (Exception e) {
            LOG.error("get productId discount info toProduct is err:{}", mo);
            return null;
        }
        return product;
    }

    private ProductMO getProductMO(String productId) {
        try {
            if (StringUtils.isBlank(productId)) {
                return null;
            }

            String jsonResult = get(getUrl(productId), null, null);
            JSONObject jsonObject = JSON.parseObject(jsonResult);

            String result = jsonObject.getString("result");
            if (StringUtils.isBlank(result)) {
                return null;
            }

            ProductMO mo = JSONObject.parseObject(result, ProductMO.class);

            LOG.info("get productId discount info mo:{}", mo);
            return mo;
        } catch (Exception e) {
            LOG.error("get productId discount info is err.");
            e.printStackTrace();
            return null;
        }
    }

    private void toProduct(ProductMO mo, Product product) throws Exception {
        if (mo == null) {
            return;
        }
        if (product == null) {
            product = new Product();
        }

        Store store = product.getStore();
        if (store == null) {
            store = new Store();
            product.setStore(store);
        }

        product.setTaoKeId(mo.getTaoKeId());
        product.setProductId(mo.getProductId());
        product.setName(mo.getName());
        product.setPictureUrl(mo.getPicture());
        product.setProductType(ProductType.fromId(mo.getTypeId()));
        product.setReservePrice(mo.getReservePrice());
        product.setDiscountPrice(mo.getDiscountPrice());
        store.setStoreId(mo.getStoreId());
        store.setType(mo.getIsTmall() == 1 ? StoreType.TMALL : StoreType.TAOBAO);
        product.setSales(mo.getSales());
        if (mo.getGeneral().compareTo(BigDecimal.ZERO) > 0) {
            product.setHireType(HireType.GENERAL);
            product.setRatio(mo.getGeneral());
        } else {
            product.setHireType(HireType.Magpie);
            product.setRatio(mo.getMagpie());
        }
        product.setPlanUrl(mo.getPlanUrl());
        product.setCouponAmount(mo.getCouponAmount());
        product.setCouponEndTime(DateTimeUtility.parseYYYYMMDDHHMMSS(mo.getCouponEndTime()));
        product.setCouponSurplusNumber(mo.getCouponSurplusNumber());
        product.setCouponReceiveNumber(mo.getCouponReceiveNumber());
        product.setCondition(mo.getCondition());
        product.setStore(store);

    }

    private String getUrl(String productId) {
        return detailUrl + "&appkey=" + appKey + "&v=2&id=" + productId;
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
