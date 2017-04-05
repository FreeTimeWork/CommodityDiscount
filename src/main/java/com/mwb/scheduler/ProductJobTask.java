package com.mwb.scheduler;

import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.model.finance.Finance;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.service.dataoke.api.IDaoLaoKeService;
import com.mwb.service.finance.api.IFinanceService;
import com.mwb.service.product.api.IProductService;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */

public class ProductJobTask {

    @Autowired
    private IProductService productService;

    @Autowired
    private IDaoLaoKeService daoLaoKeService;

    @Autowired
    private IFinanceService financeService;

    public void run() throws Exception {
        ProductFilter filter = new ProductFilter();
        List<ProductStatus> statuses = new ArrayList<>();
        statuses.add(ProductStatus.PAY_TRAILER);
        statuses.add(ProductStatus.PAY_END);
        filter.setExcludeStatus(statuses);
        filter.setPaged(false);

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
        }

        Date now = new Date();
        //结束 -- 》代付款
        List<Product> endProducts = productService.getProductByStatus(null, ProductStatus.END);
        if (CollectionUtils.isNotEmpty(endProducts)) {
            for (Product product : endProducts) {
                if (product.getCouponEndTime().after(now)) {
                    Product updateProduct = new Product();
                    updateProduct.setId(product.getId());
                    updateProduct.setStatus(ProductStatus.PAY_WAIT);
                    productService.modifyProduct(updateProduct);

                    Finance finance = financeService.getFinanceByEmployeeId(product.getEmployee().getId());
                    finance.setEndNumber(finance.getEndNumber() - 1);
                    finance.setPayWaitNumber(finance.getPayWaitNumber() + 1);
                    financeService.modifyFinance(finance);
                }
            }
        }

        //即将结束 --》结束
        List<Product> endApproachProducts = productService.getProductByStatus(null, ProductStatus.END_APPROACH);
        if (CollectionUtils.isNotEmpty(endApproachProducts)) {
            for (Product product : endApproachProducts) {
                if (product.getCouponEndTime().after(now)) {
                    Product updateProduct = new Product();
                    updateProduct.setId(product.getId());
                    updateProduct.setSales(product.getSales());
                    updateProduct.setStatus(ProductStatus.END);
                    productService.modifyProduct(updateProduct);

                    Finance finance = financeService.getFinanceByEmployeeId(product.getEmployee().getId());
                    finance.setEndApproachNumber(finance.getEndApproachNumber() - 1);
                    finance.setEndNumber(finance.getEndNumber() + 1);
                    financeService.modifyFinance(finance);
                }
            }
        }

        //即将结束
        List<Product> promoteProducts = productService.getProductByStatus(null, ProductStatus.PROMOTE);
        if (CollectionUtils.isNotEmpty(promoteProducts)) {
            for (Product product : promoteProducts) {
                Date endApproachTime = DateTimeUtility.addDays(product.getCouponEndTime(), -1);
                if (endApproachTime.after(now)) {
                    Product updateProduct = new Product();
                    updateProduct.setId(product.getId());
                    updateProduct.setStatus(ProductStatus.END_APPROACH);
                    productService.modifyProduct(updateProduct);

                    Finance finance = financeService.getFinanceByEmployeeId(product.getEmployee().getId());
                    finance.setPromoteNumber(finance.getPromoteNumber() - 1);
                    finance.setEndApproachNumber(finance.getEndApproachNumber() + 1);
                    financeService.modifyFinance(finance);
                }
            }
        }
    }
}
