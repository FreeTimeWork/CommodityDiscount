package com.mwb.controller.finance;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.finance.api.FinanceVO;
import com.mwb.controller.finance.api.SearchFinanceRequest;
import com.mwb.controller.finance.api.SearchFinanceResponse;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.filter.FinanceFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.comm.PagingData;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.EmployeeStatus;
import com.mwb.dao.model.finance.Finance;
import com.mwb.service.finance.api.IFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by MengWeiBo on 2017-04-01
 */

@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private IFinanceService financeService;

    @ResponseBody
    @RequestMapping(value = "/search", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse search(SearchFinanceRequest request) {
        SearchFinanceResponse response = new SearchFinanceResponse();
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return response;
        }

        FinanceFilter filter = new FinanceFilter();
        filter.setEmployeeId(request.getEmployeeId());
        filter.setGroupId(request.getGroupId());
        filter.setOrderByAsc(request.getOrderByAsc() == null ? true : request.getOrderByAsc());
        filter.setStatus(EmployeeStatus.fromId(request.getStatusId()));
        filter.setPaged(true);
        filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));

        SearchResult<Finance> result = financeService.searchFinance(filter, employee);
        List<FinanceVO> vos = FinanceVO.toVOs(result.getResult());

        for (FinanceVO vo : vos) {
            int rank = financeService.getCurrentFinanceRank(vo.getEmployeeId());
            vo.setRanking(rank);
        }
        response.setFinances(vos);
        response.setPagingResult(result.getPagingResult());

        return response;
    }

}
