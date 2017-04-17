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
    private String name;
    @JSONField(name = "price")
    private BigDecimal price;
    @JSONField(name = "shop_price")
    private BigDecimal shopPrice;
    @JSONField(name = "pic_url")
    private String pic_url;
    @JSONField(name = "sale_num")
    private Integer sale_num;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public Integer getSale_num() {
        return sale_num;
    }

    public void setSale_num(Integer sale_num) {
        this.sale_num = sale_num;
    }

    public List<PropMO> getPropMOs() {
        return propMOs;
    }

    public void setPropMOs(List<PropMO> propMOs) {
        this.propMOs = propMOs;
    }

    public BigDecimal getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(BigDecimal shopPrice) {
        this.shopPrice = shopPrice;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
