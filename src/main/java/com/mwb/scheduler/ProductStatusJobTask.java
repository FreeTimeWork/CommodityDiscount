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
                if (product.getCouponEndTime().after(now)) {
                    productService.modifyProductStatus(product.getId(), ProductStatus.PAY_WAIT);

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
                    productService.modifyProductStatus(product.getId(), ProductStatus.END);

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
                    productService.modifyProductStatus(product.getId(), ProductStatus.END_APPROACH);

                    Finance finance = financeService.getFinanceByEmployeeId(product.getEmployee().getId());
                    finance.setPromoteNumber(finance.getPromoteNumber() - 1);
                    finance.setEndApproachNumber(finance.getEndApproachNumber() + 1);
                    financeService.modifyFinance(finance);
                }
            }
        }
//        //结束 -- 》代付款
//        List<Product> endProducts = productService.getProductByStatus(null, ProductStatus.END);
//        if (CollectionUtils.isNotEmpty(endProducts)) {
//            for (Product product : endProducts) {
//                if (product.getCouponEndTime().after(now)) {
//                    productService.modifyProductStatus(product.getId(), ProductStatus.PAY_WAIT);
//
//                    Finance finance = financeService.getFinanceByEmployeeId(product.getEmployee().getId());
//                    finance.setEndNumber(finance.getEndNumber() - 1);
//                    finance.setPayWaitNumber(finance.getPayWaitNumber() + 1);
//                    financeService.modifyFinance(finance);
//                }
//            }
//        }
    }
}
