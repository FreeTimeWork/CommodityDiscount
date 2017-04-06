package com.mwb.service.product;

import com.mwb.controller.api.PagingResult;
import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.ProductMapper;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.finance.Finance;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductPicture;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.dao.model.product.Store;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;
import com.mwb.service.finance.api.IFinanceService;
import com.mwb.service.product.api.IProductService;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
@Service("productService")
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private IFinanceService financeService;

    @Override
    public Product getProductById(Integer id) {
        Product product = productMapper.selectProductById(id);

        if (product != null && product.getVoucher() != null) {
            List<VoucherPicture> pictures = productMapper.selectVoucherPictureByVoucherId(product.getVoucher().getId());
            product.getVoucher().setPictures(pictures);
        }

        return product;
    }

    @Override
    @Transactional
    public void createProduct(Product product) {
        createStore(product.getStore());

        productMapper.insertProduct(product);

        if (CollectionUtils.isNotEmpty(product.getPictures())) {
            for (ProductPicture picture : product.getPictures()) {
                createProductPicture(picture);
            }
        }

        Finance finance = financeService.getFinanceByEmployeeId(product.getEmployee().getId());
        if (finance == null) {
            createFinance(product.getEmployee());
        } else {
            int day = DateTimeUtility.daysBetween(finance.getCreateTime(), new Date());
            finance.setSubmitNumber(finance.getSubmitNumber() + 1);
            finance.setAverageDaily(finance.getSubmitNumber() * 100 / day);

            financeService.modifyFinance(finance);
        }
    }

    @Override
    public List<Product> getProductByProductId(String productId) {
        return productMapper.selectProductByProductId(productId);
    }

    @Override
    public List<Product> getProductByStatus(Integer employeeId, ProductStatus status) {
        return productMapper.selectProductByStatus(employeeId, status);
    }

    @Override
    public SearchResult<Product> searchProduct(ProductFilter filter, Employee employee) {
        setPermissionProductFilter(filter, employee);
        SearchResult<Product> result = new SearchResult<>();
        List<Product> products = productMapper.selectProductByFilter(filter);

        result.setResult(products);

        if (filter.isPaged() && filter.getPagingData() != null) {
            int recordNumber = productMapper.countProductByFilter(filter);
            PagingResult pagingResult = new PagingResult(recordNumber, filter.getPagingData());
            result.setPagingResult(pagingResult);
            result.setPaged(true);
        }

        return result;
    }

    private void setPermissionProductFilter(ProductFilter filter, Employee employee) {
        if (employee == null) {
            return;
        }
        Integer positionId = employee.getPosition().getId();
        Integer employeeId = employee.getId();

        if (positionId.equals(2)) {
            filter.setEmployeeId(employeeId);
        } else if (positionId.equals(3)) {
            filter.setGroupId(employee.getGroup().getId());
        } else if (positionId.equals(4)) {
            List<ProductStatus> list = new ArrayList<>();
            list.add(ProductStatus.PAY_RUN);
            list.add(ProductStatus.PAY_TRAILER);
            list.add(ProductStatus.PAY_END);
            filter.setExcludeStatus(list);
        } else if (positionId.equals(5)) {
            List<ProductStatus> list = new ArrayList<>();
            list.add(ProductStatus.PAY_WAIT);
            list.add(ProductStatus.PAY_RUN);
            list.add(ProductStatus.PAY_TRAILER);
            list.add(ProductStatus.PAY_END);
            filter.setIncludeStatus(list);
        }
    }

    private void createFinance(Employee employee) {
        Finance finance = new Finance();
        finance.setSubmitNumber(1);
        finance.setAverageDaily(100);
        finance.setRefuseNumber(0);
        finance.setRefuseRate(0);
        finance.setTwoAuditNumber(0);
        finance.setPromoteNumber(0);
        finance.setEndApproachNumber(0);
        finance.setEndNumber(0);
        finance.setPayWaitNumber(0);
        finance.setPayRunNumber(0);
        finance.setPayTrailerNumber(0);
        finance.setPayEndNumber(0);
        finance.setGuestUnitPrice(BigDecimal.ZERO);
        finance.setActualChargeAmount(BigDecimal.ZERO);
        finance.setShouldChargeAmount(BigDecimal.ZERO);
        finance.setEmployee(employee);
        finance.setCreateTime(new Date());

        financeService.createFinance(finance);
    }

    @Override
    public void modifyProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public SearchResult<ProductVoucher> searchProductVoucher(ProductFilter filter) {
        SearchResult<ProductVoucher> result = new SearchResult<>();
        List<ProductVoucher> vouchers = productMapper.selectProductVoucherByFilter(filter);

        result.setResult(vouchers);

        if (filter.isPaged() && filter.getPagingData() != null) {
            int recordNumber = productMapper.countProductVoucherByFilter(filter);
            PagingResult pagingResult = new PagingResult(recordNumber, filter.getPagingData());
            result.setPagingResult(pagingResult);
            result.setPaged(true);
        }

        return result;
    }

    @Override
    @Transactional
    public void createProductVoucher(ProductVoucher voucher) {
        productMapper.insertProductVoucher(voucher);

        if (CollectionUtils.isNotEmpty(voucher.getPictures())) {
            for (VoucherPicture picture : voucher.getPictures()) {
                createVoucherPicture(picture);
            }
        }

        Finance finance = financeService.getFinanceByEmployeeId(voucher.getProduct().getEmployee().getId());
        finance.setPayRunNumber(finance.getPayRunNumber() - 1);
        finance.setPayEndNumber(finance.getPayEndNumber() + 1);
        finance.setShouldChargeAmount(finance.getShouldChargeAmount().add(voucher.getShouldChargeAmount()));
        finance.setActualChargeAmount(finance.getActualChargeAmount().add(voucher.getActualChargeAmount()));
        finance.setGuestUnitPrice(finance.getActualChargeAmount().divide(new BigDecimal(finance.getPayEndNumber()), 2));

        financeService.modifyFinance(finance);
    }

    @Override
    public void createVoucherPicture(VoucherPicture voucherPicture) {
        productMapper.insertVoucherPicture(voucherPicture);
    }

    @Override
    public void createProductPicture(ProductPicture productPicture) {
        productMapper.insertProductPicture(productPicture);
    }

    @Override
    public void createStore(Store store) {
        productMapper.insertStore(store);
    }


}
