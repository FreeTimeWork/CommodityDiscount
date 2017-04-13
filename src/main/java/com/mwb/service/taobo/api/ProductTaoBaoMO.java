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
    private List<PropMO> propMOs;

    @JSONField(name = "product_imgs")
    private List<PropMO> imgMOs;

    public List<PropMO> getImgMOs() {
        return imgMOs;
    }

    public void setImgMOs(List<PropMO> imgMOs) {
        this.imgMOs = imgMOs;
    }

    public BigDecimal getName() {
        return name;
    }

    public void setName(BigDecimal name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public BigDecimal getSale_num() {
        return sale_num;
    }

    public void setSale_num(BigDecimal sale_num) {
        this.sale_num = sale_num;
    }

    public List<PropMO> getPropMOs() {
        return propMOs;
    }

    public void setPropMOs(List<PropMO> propMOs) {
        this.propMOs = propMOs;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
