package com.mwb.controller.finance;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.finance.api.SearchFinanceRequest;
import com.mwb.controller.finance.api.SearchFinanceResponse;
import com.mwb.service.finance.api.IFinanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  Created by mwb on 2017/4/2 0002.
 */

@Controller
@RequestMapping("/finance")
public class FinanceController {

//    @Autowired
    private IFinanceService financeService;

    @ResponseBody
    @RequestMapping(value = "/search", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse search(SearchFinanceRequest request) {
        SearchFinanceResponse response = new SearchFinanceResponse();

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/report/search", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse searchReport(SearchFinanceRequest request) {
        SearchFinanceResponse response = new SearchFinanceResponse();

        return response;
    }
}
