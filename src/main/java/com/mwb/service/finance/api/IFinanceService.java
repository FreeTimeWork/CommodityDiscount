package com.mwb.service.finance.api;

import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.finance.Finance;
import com.mwb.dao.model.product.ProductStatus;

/**
 * Created by MengWeiBo on 2017-04-01
 */
public interface IFinanceService {
    public SearchResult<Finance> searchFinance(FinanceFilter filter, Employee employee);

    public Finance getFinanceByEmployeeId(Integer employeeId);

    public int getCurrentFinanceRank(Integer employeeId);

    public void modifyFinance(Finance finance);

    public void modifyFinance(Integer employeeId, ProductStatus fromStatus, ProductStatus toStatus);

    public void createFinance(Finance finance);

}
