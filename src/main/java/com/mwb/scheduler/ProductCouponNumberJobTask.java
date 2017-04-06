package com.mwb.scheduler;

import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.model.comm.PagingData;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.service.dataoke.api.IDaoLaoKeService;
import com.mwb.service.product.api.IProductService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */

public class ProductCouponNumberJobTask {

    @Autowired
    private IProductService productService;

    @Autowired
    private IDaoLaoKeService daoLaoKeService;

    public void run() throws Exception {
        ProductFilter filter = new ProductFilter();
        List<ProductStatus> statuses = new ArrayList<>();
        statuses.add(ProductStatus.PAY_TRAILER);
        statuses.add(ProductStatus.PAY_END);
        filter.setExcludeStatus(statuses);
        filter.setPaged(true);
        int i = 1;

        while (true) {
            filter.setPagingData(new PagingData(i++, PagingData.MAX_PAGE_SIZE));

            List<Product> products = productService.searchProduct(filter, null).getResult();
            if (CollectionUtils.isNotEmpty(products)) {
                for (Product product : products) {
                    Product daTaoKeProduct = daoLaoKeService.getDaTaoKeProduct(product.getProductId());

                    if (daTaoKeProduct != null) {
                        if (daTaoKeProduct.getCouponReceiveNumber() != null
                                && daTaoKeProduct.getCouponSurplusNumber() != null) {
                            Product updateProduct = new Product();
                            updateProduct.setId(product.getId());
                            updateProduct.setCouponSurplusNumber(daTaoKeProduct.getCouponSurplusNumber());
                            updateProduct.setCouponReceiveNumber(daTaoKeProduct.getCouponReceiveNumber());

                            productService.modifyProduct(updateProduct);
                        }
                    }
                }
            }else {
                break;
            }

            if (products.size() < PagingData.MAX_PAGE_SIZE) {
                break;
            }
        }
    }
}
