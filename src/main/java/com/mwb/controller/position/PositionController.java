package com.mwb.controller.position;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.position.api.SearchPositionRequest;
import com.mwb.dao.filter.PositionFilter;
import com.mwb.dao.model.comm.PagingData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
@Controller
@RequestMapping("/position")
public class PositionController {


    @RequestMapping(value = "/search",produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse searchPosition(SearchPositionRequest request){

        PositionFilter filter = new PositionFilter();

        filter.setId(request.getPositionId());
        filter.setPaged(true);
        filter.setPagingData(new PagingData(request.getPageNumber(),request.getPageSize()));



    }
}
