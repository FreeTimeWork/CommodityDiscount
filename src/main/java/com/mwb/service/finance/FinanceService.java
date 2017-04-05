package com.mwb.service.finance;

import com.mwb.controller.api.PagingResult;
import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.FinanceMapper;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.finance.Finance;
import com.mwb.service.finance.api.IFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */
@Service("financeService")
public class FinanceService implements IFinanceService {

    @Autowired
    private FinanceMapper financeMapper;

    public SearchResult<Finance> searchFinance(FinanceFilter filter, Employee employee) {
        if (employee != null) {
            Integer positionId = employee.getPosition().getId();
            if (positionId.equals(2)) {
                filter.setEmployeeId(employee.getId());
            } else if (positionId.equals(3)) {
                filter.setGroupId(employee.getGroup().getId());
            }
        }

        SearchResult<Finance> result = new SearchResult<>();
        List<Finance> finances = financeMapper.selectFinanceByFilter(filter);

        result.setResult(finances);

        if (filter.isPaged() && filter.getPagingData() != null) {
            int recordNumber = financeMapper.countFinanceByFilter(filter);
            PagingResult pagingResult = new PagingResult(recordNumber, filter.getPagingData());
            result.setPagingResult(pagingResult);
            result.setPaged(true);
        }

        return result;
    }

    @Override
    public Finance getFinanceByEmployeeId(Integer employeeId) {
        return financeMapper.selectFinanceByEmployeeId(employeeId);
    }

    @Override
    public int getCurrentFinanceRank(Integer employeeId) {
        return financeMapper.selectCurrentFinanceRank(employeeId);
    }

    @Override
    public void modifyFinance(Finance finance) {
        financeMapper.updateFinance(finance);
    }

    @Override
    public void createFinance(Finance finance) {
        financeMapper.insertFinance(finance);
    }

}
