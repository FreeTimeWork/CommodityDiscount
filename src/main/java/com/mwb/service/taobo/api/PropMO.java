package com.mwb.service.taobo.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.mwb.dao.model.product.ProductPicture;
import org.apache.commons.collections.CollectionUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-12
 */
public class PropMO {
    @JSONField(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static List<ProductPicture> toPicture(List<PropMO> mos) {
        List<ProductPicture> productPictures = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(mos)) {
            for (PropMO mo : mos){
                ProductPicture productPicture = new ProductPicture();
                productPicture.setUrl(mo.getUrl());
                productPictures.add(productPicture);
            }
        }
        return productPictures;
    }
}
