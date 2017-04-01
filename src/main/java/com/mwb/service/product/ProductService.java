package com.mwb.service.product;

import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.product.Product;
import com.mwb.service.product.api.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
@Service("productService")
public class ProductService implements IProductService{

    @Override
    public List<Product> getProductByProductId(String productId) {
        return null;
    }

    @Override
    public SearchResult<Product> searchProduct(ProductFilter filter) {
        return null;
    }

    @Override
    public void createProduct(Product product) {

    }
}
