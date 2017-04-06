package com.mwb.dao.mapper;

import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductPicture;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.dao.model.product.Store;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public interface ProductMapper {

    public List<Product> selectProductByFilter(@Param("filter") ProductFilter filter);

    public int countProductByFilter(@Param("filter") ProductFilter filter);

    public Product selectProductById(@Param("id") Integer id);

    public List<Product> selectProductByProductId(@Param("productId") String productId);

    public List<Product> selectProductByStatus(
            @Param("employeeId") Integer employeeId, @Param("status") ProductStatus status);

    public void updateProduct(Product product);

    public void insertProduct(Product product);

    public List<ProductVoucher> selectProductVoucherByFilter(@Param("filter") ProductFilter filter);

    public int countProductVoucherByFilter(@Param("filter") ProductFilter filter);

    public void insertProductVoucher(ProductVoucher productVoucher);

    public void insertVoucherPicture(VoucherPicture voucherPicture);

    public void insertProductPicture(ProductPicture productPicture);

    public void insertStore(Store store);
}