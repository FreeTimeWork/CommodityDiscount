package com.mwb.service.product;

import java.util.List;

import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.product.Product;
import com.mwb.service.product.api.IProductService;
import org.springframework.stereotype.Service;

/**
 * Created by MengWeiBo on 2017-04-01
 */
@Service("productService")
public class ProductService implements IProductService{

    @Override
    public Product getProductById(Integer id) {
        return null;
    }

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
