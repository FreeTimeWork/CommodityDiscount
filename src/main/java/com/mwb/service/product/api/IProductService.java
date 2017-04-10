package com.mwb.service.product.api;

import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductPicture;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.dao.model.product.Store;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public interface IProductService {

    public Product getProductById(Integer id);

    public List<Product> getProductByProductId(String productId);

    public List<Product> getProductByStatus(ProductStatus status);

    public SearchResult<Product> searchProduct(ProductFilter filter, Employee employee);

    public void createProduct(Product product);

    public void modifyProduct(Product product);

    public void modifyProductStatus(Integer id, Integer employeeId, ProductStatus fromStatus, ProductStatus toStatus);

    public SearchResult<ProductVoucher> searchProductVoucher(ProductFilter filter, Employee employee);

    public void createProductVoucher(ProductVoucher voucher, Product product);

    public void createVoucherPicture(VoucherPicture voucherPicture);

    public void createProductPicture(ProductPicture productPicture);

    public void createStore(Store store);
}
