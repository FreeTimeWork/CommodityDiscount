package com.mwb.controller.position;

import java.util.List;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.position.api.*;
import com.mwb.dao.filter.PositionFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.comm.PagingData;
import com.mwb.dao.model.permission.Permission;
import com.mwb.dao.model.position.Position;
import com.mwb.service.position.api.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fangchen.chai on 2017/4/2.
 */
@Controller
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ResponseBody
    @RequestMapping(value = "/search", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse searchPosition(SearchPositionRequest request) {

        PositionFilter filter = new PositionFilter();

        filter.setId(request.getPositionId());
        filter.setPaged(true);
        filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));

        SearchResult<Position> result = positionService.searchPosition(filter);
        SearchPositionResponse response = new SearchPositionResponse();
        response.setPositions(PositionVO.toVOs(result.getResult()));
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/permission/allPermission", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse searchPermission() {

        List<Permission> permissions = positionService.searchAllPermission();
        SearchPermissionResponse response = new SearchPermissionResponse();
        response.setPermissions(PermissionVO.toVOs(permissions));
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/permission/byPositionId", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse getPermissionByPositionId(Integer positionId) {

        List<Permission> permissions = positionService.getPermissionsByPositionId(positionId);
        SearchPermissionResponse response = new SearchPermissionResponse();
        response.setPermissions(PermissionVO.toVOs(permissions));
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/permission/modify", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse modifyPositionPermission(ModifyPositionPermissionRequest request) {

        //删除该岗位所有权限，重新添加。
        positionService.createPositionPermission(request.getPositionId(), request.getPermissionIds());

        return new ServiceResponse();

    }
}
