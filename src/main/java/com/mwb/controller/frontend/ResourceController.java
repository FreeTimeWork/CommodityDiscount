package com.mwb.controller.frontend;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.frontend.api.DataResponse;
import com.mwb.controller.frontend.api.ResourceVO;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.EmployeeStatus;
import com.mwb.dao.model.employee.Group;
import com.mwb.dao.model.position.Position;
import com.mwb.dao.model.product.*;
import com.mwb.service.employee.api.IEmployeeService;
import com.mwb.service.position.api.IPositionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MengWeiBo on 2017-03-31
 */

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IPositionService positionService;

    @ResponseBody
    @RequestMapping(value = "/data", produces = ContentType.APPLICATION_JSON_UTF8)
    public DataResponse getData() {
        DataResponse response = new DataResponse();

        List<ResourceVO> productTypes = new ArrayList<>();
        for (ProductType productType : ProductType.values()) {
            ResourceVO vo = new ResourceVO(productType.getId(), productType.getDescription());
            productTypes.add(vo);
        }
        List<ResourceVO> productStatus = new ArrayList<>();
        for (ProductStatus status : ProductStatus.values()) {
            ResourceVO vo = new ResourceVO(status.getId(), status.getDescription());
            productStatus.add(vo);
        }
        List<ResourceVO> activities = new ArrayList<>();
        for (Activity activity : Activity.values()) {
            ResourceVO vo = new ResourceVO(activity.getId(), activity.getDescription());
            activities.add(vo);
        }
        List<ResourceVO> hireTypes = new ArrayList<>();
        for (HireType hireType : HireType.values()) {
            ResourceVO vo = new ResourceVO(hireType.getId(), hireType.getDescription());
            hireTypes.add(vo);
        }
        List<ResourceVO> storeTypes = new ArrayList<>();
        for (StoreType storeType : StoreType.values()) {
            ResourceVO vo = new ResourceVO(storeType.getId(), storeType.getDescription());
            storeTypes.add(vo);
        }
        List<ResourceVO> employeeStatus = new ArrayList<>();
        for (EmployeeStatus status : EmployeeStatus.values()) {
            ResourceVO vo = new ResourceVO(status.getId(), status.getDescription());
            employeeStatus.add(vo);
        }
        List<ResourceVO> groupVos = new ArrayList<>();
        List<Group> groups = employeeService.getAllGroup();
        if (CollectionUtils.isNotEmpty(groups)) {
            for (Group group : groups) {
                ResourceVO vo = new ResourceVO(group.getId(), group.getName());
                groupVos.add(vo);
            }
        }
        List<ResourceVO> positionVos = new ArrayList<>();
        List<Position> positions = positionService.searchAllPosition();
        if (CollectionUtils.isNotEmpty(positions)) {
            for (Position position : positions) {
                ResourceVO vo = new ResourceVO(position.getId(), position.getName());
                positionVos.add(vo);
            }
        }
        List<ResourceVO> employeeVos = new ArrayList<>();
        List<Employee> employees = employeeService.getAllEmployee();
        if (CollectionUtils.isNotEmpty(employees)) {
            for (Employee employee : employees) {
                ResourceVO vo = new ResourceVO(employee.getId(), employee.getFullName());
                employeeVos.add(vo);
            }
        }

        response.setProductTypes(productTypes);
        response.setProductStatus(productStatus);
        response.setActivities(activities);
        response.setHireTypes(hireTypes);
        response.setStoreTypes(storeTypes);
        response.setEmployeeStatus(employeeStatus);
        response.setGroups(groupVos);
        response.setPositions(positionVos);
        response.setEmployees(employeeVos);

        return new DataResponse();
    }
}
