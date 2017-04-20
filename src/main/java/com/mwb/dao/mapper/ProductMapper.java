package com.mwb.dao.mapper;

import java.util.List;

import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductPicture;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.dao.model.product.Store;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;
import org.apache.ibatis.annotations.Param;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public interface ProductMapper {

    public List<Product> selectProductByFilter(@Param("filter") ProductFilter filter);

    public int countProductByFilter(@Param("filter") ProductFilter filter);

    public Product selectProductById(@Param("id") Integer id);

    public List<Product> selectProductByProductId(@Param("productId") String productId);

    public List<ProductPicture> selectProductPictureByProductId(@Param("productId") Integer productId);

    public List<Product> selectProductByStatus(
            @Param("employeeId") Integer employeeId, @Param("status") ProductStatus status);

    public void updateProduct(Product product);

    public void insertProduct(Product product);

    public List<ProductVoucher> selectProductVoucherByFilter(@Param("filter") ProductFilter filter);

    public ProductVoucher selectProductVoucherByProductId(@Param("productId") Integer productId);

    public int countProductVoucherByFilter(@Param("filter") ProductFilter filter);

    public List<VoucherPicture> selectVoucherPictureByVoucherId(@Param("voucherId") Integer voucherId);

    public void insertProductVoucher(ProductVoucher productVoucher);

    public void deleteProductVoucher(@Param("productId") Integer productId);

    public void deleteVoucherPicture(@Param("voucherId") Integer voucherId);

    public void insertVoucherPicture(VoucherPicture voucherPicture);

    public void insertProductPicture(ProductPicture productPicture);

    public void insertStore(Store store);

//  Statistics
    public List<Product> selectStatisticsProductByFilter(@Param("filter") FinanceFilter filter);

}
