package com.mwb.service.finance;

import com.mwb.controller.api.PagingResult;
import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.mapper.FinanceMapper;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.finance.Finance;
import com.mwb.dao.model.product.ProductStatus;
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
        return financeMapper.selectCurrentFinanceRank(employeeId) + 1;
    }

    @Override
    public void modifyFinance(Finance finance) {
        financeMapper.updateFinance(finance);
    }

    @Override
    public void modifyFinance(Integer employeeId, ProductStatus fromStatus, ProductStatus toStatus) {
        Finance finance = getFinanceByEmployeeId(employeeId);
        setStatusNumber(finance, fromStatus, toStatus);

        financeMapper.updateFinance(finance);
    }

    @Override
    public void createFinance(Finance finance) {
        financeMapper.insertFinance(finance);
    }

    private void setStatusNumber(Finance finance, ProductStatus fromStatus, ProductStatus toStatus) {
        if (fromStatus != null) {
            if (ProductStatus.TRAILER == toStatus) {
                finance.setRefuseNumber(finance.getRefuseNumber() - 1);
            } else if (ProductStatus.TWO_AUDIT == fromStatus) {
                finance.setTwoAuditNumber(finance.getTwoAuditNumber() - 1);
            } else if (ProductStatus.PROMOTE == fromStatus) {
                finance.setPromoteNumber(finance.getPromoteNumber() - 1);
            } else if (ProductStatus.END_APPROACH == fromStatus) {
                finance.setEndApproachNumber(finance.getEndApproachNumber() - 1);
            } else if (ProductStatus.END == fromStatus) {
                finance.setEndNumber(finance.getEndNumber() - 1);
            } else if (ProductStatus.PAY_WAIT == fromStatus) {
                finance.setPayWaitNumber(finance.getPayWaitNumber() - 1);
            } else if (ProductStatus.PAY_RUN == fromStatus) {
                finance.setPayRunNumber(finance.getPayRunNumber() - 1);
            } else if (ProductStatus.PAY_TRAILER == fromStatus) {
                finance.setPayTrailerNumber(finance.getPayTrailerNumber() - 1);
            } else if (ProductStatus.PAY_END == fromStatus) {
                finance.setPayEndNumber(finance.getPayEndNumber() - 1);
            }
        }

        if (toStatus != null) {
            if (ProductStatus.TRAILER == toStatus) {
                finance.setRefuseNumber(finance.getRefuseNumber() + 1);
            } else if (ProductStatus.TWO_AUDIT == toStatus) {
                finance.setTwoAuditNumber(finance.getTwoAuditNumber() + 1);
            } else if (ProductStatus.PROMOTE == toStatus) {
                finance.setPromoteNumber(finance.getPromoteNumber() + 1);
            } else if (ProductStatus.END_APPROACH == toStatus) {
                finance.setEndApproachNumber(finance.getEndApproachNumber() + 1);
            } else if (ProductStatus.END == toStatus) {
                finance.setEndNumber(finance.getEndNumber() + 1);
            } else if (ProductStatus.PAY_WAIT == toStatus) {
                finance.setPayWaitNumber(finance.getPayWaitNumber() + 1);
            } else if (ProductStatus.PAY_RUN == toStatus) {
                finance.setPayRunNumber(finance.getPayRunNumber() + 1);
            } else if (ProductStatus.PAY_TRAILER == toStatus) {
                finance.setPayTrailerNumber(finance.getPayTrailerNumber() + 1);
            } else if (ProductStatus.PAY_END == toStatus) {
                finance.setPayEndNumber(finance.getPayEndNumber() + 1);
            }
        }

        finance.setRefuseRate(finance.getRefuseNumber() * 100 / finance.getSubmitNumber());
    }
}
