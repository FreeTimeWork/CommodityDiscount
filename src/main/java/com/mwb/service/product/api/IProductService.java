package com.mwb.service.product.api;

import com.mwb.dao.model.product.Product;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public interface IProductService {

    public List<Product> getProductByProductId(String productId);

    public void createProduct(Product product);
}
