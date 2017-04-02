package com.mwb.controller.product.api;

import com.mwb.controller.api.PagingResponse;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public class SearchProductResponse extends PagingResponse {
    private static final long serialVersionUID = 1L;
    private List<ProductVO> products;

    public List<ProductVO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductVO> products) {
        this.products = products;
    }
}
