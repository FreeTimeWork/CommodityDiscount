package com.mwb.controller.finance.api;

import java.util.List;

import com.mwb.controller.api.PagingResponse;


/**
 *  Created by Administrator on 2017/4/2 0002.
 */
public class SearchFinanceResponse extends PagingResponse {
    private static final long serialVersionUID = 1L;
    private List<FinanceVO> finances;
    private FinanceVO countFinance;

    public FinanceVO getCountFinance() {
        return countFinance;
    }

    public void setCountFinance(FinanceVO countFinance) {
        this.countFinance = countFinance;
    }

    public List<FinanceVO> getFinances() {
        return finances;
    }

    public void setFinances(List<FinanceVO> finances) {
        this.finances = finances;
    }
}
