package com.mwb.controller.finance.api;

import com.mwb.controller.api.PagingResponse;
import com.mwb.controller.product.api.ProductVoucherVO;

import java.util.List;


/**
 *  Created by Administrator on 2017/4/2 0002.
 */
public class SearchFinanceVoucherResponse extends PagingResponse {
    private static final long serialVersionUID = 1L;
    private List<ProductVoucherVO> vouchers;

    public List<ProductVoucherVO> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<ProductVoucherVO> vouchers) {
        this.vouchers = vouchers;
    }
}
