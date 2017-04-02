package com.mwb.service.product.api;

import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.product.Product;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public interface IProductService {

    public Product getProductById(Integer id);

    public List<Product> getProductByProductId(String productId);

    public SearchResult<Product> searchProduct(ProductFilter filter);

    public void createProduct(Product product);
}
