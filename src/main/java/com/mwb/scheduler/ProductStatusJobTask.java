package com.mwb.scheduler;

import com.mwb.dao.model.finance.Finance;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.service.finance.api.IFinanceService;
import com.mwb.service.product.api.IProductService;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */

public class ProductStatusJobTask {

    @Autowired
    private IProductService productService;

    @Autowired
    private IFinanceService financeService;

    public void run() throws Exception {

        Date now = new Date();
        //结束 -- 》代付款
        List<Product> endProducts = productService.getProductByStatus(null, ProductStatus.END);
        if (CollectionUtils.isNotEmpty(endProducts)) {
            for (Product product : endProducts) {
                int hour = DateTimeUtility.minuteBetween(product.getUpdateStatusTime(), now) / 60;
                if (hour > 24) {
                    productService.modifyProductStatus(product.getId(), ProductStatus.PAY_WAIT);

                    financeService.modifyFinance(product.getEmployee().getId(), ProductStatus.END, ProductStatus.PAY_WAIT);
                }
            }
        }

        //即将结束 --》结束
        List<Product> endApproachProducts = productService.getProductByStatus(null, ProductStatus.END_APPROACH);
        if (CollectionUtils.isNotEmpty(endApproachProducts)) {
            for (Product product : endApproachProducts) {
                Date tomorrow = DateTimeUtility.addDays(DateTimeUtility.getMinTimeOfDay(product.getCouponEndTime()), 1);

                if (now.after(tomorrow)){
                    productService.modifyProductStatus(product.getId(), ProductStatus.END);

                    financeService.modifyFinance(product.getEmployee().getId(), ProductStatus.END_APPROACH, ProductStatus.END);
                }
            }
        }

        //即将结束
        List<Product> promoteProducts = productService.getProductByStatus(null, ProductStatus.PROMOTE);
        if (CollectionUtils.isNotEmpty(promoteProducts)) {
            for (Product product : promoteProducts) {
                int hour = DateTimeUtility.minuteBetween(now, product.getUpdateStatusTime()) / 60;

                if (hour <= 24 && hour > 0) {
                    productService.modifyProductStatus(product.getId(), ProductStatus.END_APPROACH);

                    financeService.modifyFinance(product.getEmployee().getId(), ProductStatus.PROMOTE, ProductStatus.END_APPROACH);
                }
            }
        }

        //// TODO: 2017/4/6
        //复审--》推广
        List<Product> twoAuditProducts = productService.getProductByStatus(null, ProductStatus.TWO_AUDIT);
        if (CollectionUtils.isNotEmpty(twoAuditProducts)) {
            for (Product product : twoAuditProducts) {
                int hour = DateTimeUtility.minuteBetween(product.getUpdateStatusTime(), now) / 60;

                if (hour >= 24) {
                    productService.modifyProductStatus(product.getId(), ProductStatus.PROMOTE);

                    financeService.modifyFinance(product.getEmployee().getId(), ProductStatus.TWO_AUDIT, ProductStatus.PROMOTE);
                }
            }
        }
    }
}