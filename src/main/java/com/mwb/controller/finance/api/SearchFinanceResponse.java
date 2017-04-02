package com.mwb.controller.finance.api;

import com.mwb.controller.api.PagingResponse;

import java.util.List;


/**
 *  Created by Administrator on 2017/4/2 0002.
 */
public class SearchFinanceResponse extends PagingResponse {
    private static final long serialVersionUID = 1L;
    private List<FinanceVO> finances;

    public List<FinanceVO> getFinances() {
        return finances;
    }

    public void setFinances(List<FinanceVO> finances) {
        this.finances = finances;
    }
}
