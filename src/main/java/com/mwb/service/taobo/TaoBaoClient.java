package com.mwb.service.taobo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mwb.dao.model.comm.Log;
import com.mwb.dao.model.product.Product;
import com.mwb.http.AbstractHttpClient;
import com.mwb.service.taobo.api.*;
import com.mwb.util.DateTimeUtility;
import org.springframework.stereotype.Service;

/**
 * Created by MengWeiBo on 2017-04-12
 */
//@Service("taoBaoClient")
public class TaoBaoClient extends AbstractHttpClient implements ITaoBaoClient {
    private static final Log LOG = Log.getLog(TaoBaoClient.class);

    @Value("")
    private String code;
    @Value("")
    private String appKey;
    @Value("")
    private String appSecret;
    @Value("")
    private String redirectUri;

    @Value("http://gw.api.taobao.com/router/rest")
    private String activityUrl;

    @Value("https://oauth.taobao.com/token")
    private String tokenUrl;

    //    @Autowired
//    private
    public static void main(String[] args) throws IOException {
        Map<String, String> props = new HashMap<>();
        props.put("app_key", "12345678");
        props.put("fields", "num_iid,title,nick,price,num");
        props.put("format", "json");
        props.put("method", "taobao.item.seller.get");
        props.put("num_iid", "11223344");
        props.put("session", "test");
        props.put("sign_method", "md5");
        props.put("timestamp", "2016-01-01 12:00:00");
        props.put("v", "2.0");
        System.out.println(signTopRequest(props, "helloworld"));

        System.out.println("66987CB115214E59E6EC978214934FB8");
    }

    @Override
    public AccessTokenMO getAccessToken() {

        AccessTokenMO accessToken = null;
        String url = "https://oauth.taobao.com/token";
        Map<String, String> props = new HashMap<>();
        props.put("grant_type", "authorization_code");
        props.put("code", code);
        props.put("client_id", appKey);
        props.put("client_secret", appSecret);
        props.put("redirect_uri", redirectUri);
        props.put("view", "web");

        try {
            String json = post(url, props, null);
            LOG.info(json);

            accessToken = JSONObject.parseObject(json, AccessTokenMO.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("get accessToken err,e{}", e);
        }

        return accessToken;
    }

    @Override
    public ActivityTaoBaoMO getProductActivity(String activityId, String accessToken) {
        try {
            Map<String, String> props = new HashMap<>();
            String timestamp = DateTimeUtility.formatYYYYMMDDHHMMSS(new Date());
            props.put("method", "taobao.promotion.activity.get");
            props.put("app_key", appKey);
            props.put("sign_method", "md5");
            props.put("timestamp", timestamp);
            props.put("session", accessToken);
            props.put("format", "json");
            props.put("v", "2.0");
            props.put("simplify", "true");

            props.put("activity_id", activityId);
            String sign = signTopRequest(props, appSecret);
            props.put("sign", sign);

            String json = post(activityUrl, props, null);
            JSONArray jsonArray = JSON.parseArray(json);
            if (jsonArray != null && jsonArray.size() > 0){
                JSONObject object = jsonArray.getJSONObject(0);
                ActivityTaoBaoMO mo = JSONObject.parseObject(object.toString(),ActivityTaoBaoMO.class);
                return mo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CouponTaoBaoMO getProductCoupon(String couponId, String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }
        try {
            Map<String, String> props = new HashMap<>();
            String timestamp = DateTimeUtility.formatYYYYMMDDHHMMSS(new Date());
            props.put("method", "taobao.promotion.coupons.get");
            props.put("app_key", appKey);
            props.put("sign_method", "md5");
            props.put("timestamp", timestamp);
            props.put("session", accessToken);
            props.put("format", "json");
            props.put("v", "2.0");
            props.put("simplify", "true");
            props.put("coupon_id", couponId);
            String sign = signTopRequest(props, appSecret);
            props.put("sign", sign);

            String json = post(activityUrl, props, null);
            JSONArray jsonArray = JSON.parseArray(json);
            if (jsonArray != null && jsonArray.size() > 0){
                JSONObject object = jsonArray.getJSONObject(0);
                CouponTaoBaoMO mo = JSONObject.parseObject(object.toString(),CouponTaoBaoMO.class);
                return mo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Product getProduct(String productId) {
        return null;
    }

    @Override
    public Product getProductCoupon(String couponId) {
        return null;
    }

    @Override
    public ProductTaoBaoMO getProductMO(String productId, String accessToken) {
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }
        try {
            Map<String, String> props = new HashMap<>();
            String timestamp = DateTimeUtility.formatYYYYMMDDHHMMSS(new Date());
            props.put("method", "taobao.product.get");
            props.put("app_key", appKey);
            props.put("sign_method", "md5");
            props.put("timestamp", timestamp);
            props.put("session", accessToken);
            props.put("format", "json");
            props.put("v", "2.0");
            props.put("simplify", "true");
            props.put("product_d", productId);
            props.put("fields", "name,price,shop_price,pic_url,sale_num,product_prop_imgs,product_imgs");
            String sign = signTopRequest(props, appSecret);
            props.put("sign", sign);

            String json = post(activityUrl, props, null);
            JSONArray jsonArray = JSON.parseArray(json);
            if (jsonArray != null && jsonArray.size() > 0){
                JSONObject object = jsonArray.getJSONObject(0);
                ProductTaoBaoMO mo = JSONObject.parseObject(object.toString(),ProductTaoBaoMO.class);
                return mo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProduct(String productId, String couponId) {
        return null;
    }

    private static String signTopRequest(Map<String, String> params, String secret) throws IOException {
        // 第一步：检查参数是否已经排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        query.append(secret);
        for (String key : keys) {
            String value = params.get(key);
            if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
                query.append(key).append(value);
            }
        }
        query.append(secret);

        return DigestUtils.md5Hex(query.toString()).toUpperCase();
    }

}
