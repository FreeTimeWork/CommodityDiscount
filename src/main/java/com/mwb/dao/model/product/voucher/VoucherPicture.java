package com.mwb.dao.model.product.voucher;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/4 0004.
 */
public class VoucherPicture  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String url;
    private ProductVoucher voucher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ProductVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(ProductVoucher voucher) {
        this.voucher = voucher;
    }
}
