package com.mwb.service.taobo.api;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 *  Created by mwb on 2017/3/31 0031.
 */
public class ProductTaoBaoMO {

    @JSONField(name = "name")
    private BigDecimal name;
    @JSONField(name = "price")
    private BigDecimal price;
    @JSONField(name = "pic_url")
    private String pic_url;
    @JSONField(name = "sale_num")
    private BigDecimal sale_num;

    @JSONField(name = "product_prop_imgs")
//    @JSONField(name = "product_imgs")
    private List<PropMO> propMOs;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
