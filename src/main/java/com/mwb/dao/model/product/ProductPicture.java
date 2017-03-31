package com.mwb.dao.model.product;

import java.io.Serializable;

/**
 * Created by MengWeiBo on 2017-03-29
 */
public class ProductPicture implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String url;
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
