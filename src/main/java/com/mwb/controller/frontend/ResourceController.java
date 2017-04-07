package com.mwb.controller.frontend;

import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.frontend.api.DataResponse;
import com.mwb.controller.frontend.api.ProductStatusResponse;
import com.mwb.controller.frontend.api.ResourceVO;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.employee.EmployeeStatus;
import com.mwb.dao.model.employee.Group;
import com.mwb.dao.model.position.Position;
import com.mwb.dao.model.product.*;
import com.mwb.service.employee.api.IEmployeeService;
import com.mwb.service.position.api.IPositionService;
import com.mwb.service.product.api.IProductService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private IProductService productService;

    @ResponseBody
    @RequestMapping(value = "/data")
    public DataResponse getData() {
        DataResponse response = new DataResponse();

        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return response;
        }

        Integer positionId = employee.getPosition().getId();


        List<ResourceVO> productTypes = new ArrayList<>();
        productTypes.add(new ResourceVO("选择分类", null));
        for (ProductType productType : ProductType.values()) {
            ResourceVO vo = new ResourceVO(productType.getDescription(), productType.getId());
            productTypes.add(vo);
        }
        List<ResourceVO> productStatus = new ArrayList<>();
        for (ProductStatus status : ProductStatus.values()) {
            ResourceVO vo = new ResourceVO(status.getDescription(), status.getId());
            productStatus.add(vo);
        }
        List<ResourceVO> activities = new ArrayList<>();
        activities.add(new ResourceVO("选择活动类别", null));
        for (Activity activity : Activity.values()) {
            ResourceVO vo = new ResourceVO(activity.getDescription(), activity.getId());
            activities.add(vo);
        }
        List<ResourceVO> hireTypes = new ArrayList<>();
        for (HireType hireType : HireType.values()) {
            ResourceVO vo = new ResourceVO(hireType.getDescription(), hireType.getId());
            hireTypes.add(vo);
        }
        List<ResourceVO> storeTypes = new ArrayList<>();
        for (StoreType storeType : StoreType.values()) {
            ResourceVO vo = new ResourceVO(storeType.getDescription(), storeType.getId());
            storeTypes.add(vo);
        }
        List<ResourceVO> employeeStatus = new ArrayList<>();
        employeeStatus.add(new ResourceVO("全部", null));
        for (EmployeeStatus status : EmployeeStatus.values()) {
            ResourceVO vo = new ResourceVO(status.getDescription(), status.getId());
            employeeStatus.add(vo);
        }
        List<ResourceVO> groupVos = new ArrayList<>();
        groupVos.add(new ResourceVO("选择业务小组", null));
        List<Group> groups = employeeService.getAllGroup();
        if (CollectionUtils.isNotEmpty(groups)) {
            for (Group group : groups) {
                if (positionId.equals(2) || positionId.equals(3)) {
                    if (employee.getGroup() == null || !group.getId().equals(employee.getGroup().getId())) {
                        continue;
                    }
                }

                ResourceVO vo = new ResourceVO(group.getName(), group.getId());
                groupVos.add(vo);
            }
        }
        List<ResourceVO> positionVos = new ArrayList<>();
        List<Position> positions = positionService.searchAllPosition();
        if (CollectionUtils.isNotEmpty(positions)) {
            for (Position position : positions) {
                ResourceVO vo = new ResourceVO(position.getName(), position.getId());
                positionVos.add(vo);
            }
        }
        List<ResourceVO> employeeVos = new ArrayList<>();
        employeeVos.add(new ResourceVO("选择业务员", null));
        List<Employee> employees = employeeService.getAllEmployee();
        if (CollectionUtils.isNotEmpty(employees)) {
            for (Employee emp : employees) {
                if (positionId.equals(2)) {
                    if (!employee.getId().equals(emp.getId())) {
                        continue;
                    }
                } else if (positionId.equals(3)) {
                    if (emp.getGroup() == null || !emp.getGroup().getId().equals(employee.getGroup().getId())) {
                        continue;
                    }
                }
                ResourceVO vo = new ResourceVO(emp.getFullName(), emp.getId());
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

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/status")
    public ServiceResponse getProductStatus() {
        ProductStatusResponse response = new ProductStatusResponse();
        response.setStatus(getStatus());

        return response;

    }

    private List<ResourceVO> getStatus() {
        List<ResourceVO> vos = new ArrayList<>();
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return vos;
        }
        ProductFilter filter = new ProductFilter();
        filter.setPaged(false);

        SearchResult<Product> result = productService.searchProduct(filter, employee);

        return getStatus(result.getResult());
    }

    private List<ResourceVO> getStatus(List<Product> products) {
        int total = products.size();
        List<ResourceVO> vos = new ArrayList<>();
        Map<ProductStatus, Integer> map = new HashMap<>();

        if (CollectionUtils.isNotEmpty(products)) {
            for (Product product : products) {
                ProductStatus key = product.getStatus();
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }
        }

        vos.add(new ResourceVO("全部产品", total));
        return getStatusVos(vos, map);
    }

    private List<ResourceVO> getStatusVos(List<ResourceVO> vos, Map<ProductStatus, Integer> map) {
        ResourceVO auditWait = new ResourceVO(ProductStatus.AUDIT_WAIT.getDescription(),
                map.get(ProductStatus.AUDIT_WAIT) == null ? 0 : map.get(ProductStatus.AUDIT_WAIT));
        ResourceVO auditRun = new ResourceVO(ProductStatus.AUDIT_RUN.getDescription(),
                map.get(ProductStatus.AUDIT_RUN) == null ? 0 : map.get(ProductStatus.AUDIT_RUN));
        ResourceVO rejected = new ResourceVO(ProductStatus.REJECTED.getDescription(),
                map.get(ProductStatus.REJECTED) == null ? 0 : map.get(ProductStatus.REJECTED));
        ResourceVO trailer = new ResourceVO(ProductStatus.TRAILER.getDescription(),
                map.get(ProductStatus.TRAILER) == null ? 0 : map.get(ProductStatus.TRAILER));
        ResourceVO twoAudit = new ResourceVO(ProductStatus.TWO_AUDIT.getDescription(),
                map.get(ProductStatus.TWO_AUDIT) == null ? 0 : map.get(ProductStatus.TWO_AUDIT));
        ResourceVO promote = new ResourceVO(ProductStatus.PROMOTE.getDescription(),
                map.get(ProductStatus.PROMOTE) == null ? 0 : map.get(ProductStatus.PROMOTE));
        ResourceVO endApproach = new ResourceVO(ProductStatus.END_APPROACH.getDescription(),
                map.get(ProductStatus.END_APPROACH) == null ? 0 : map.get(ProductStatus.END_APPROACH));
        ResourceVO end = new ResourceVO(ProductStatus.END.getDescription(),
                map.get(ProductStatus.END) == null ? 0 : map.get(ProductStatus.END));
        ResourceVO payWait = new ResourceVO(ProductStatus.PAY_WAIT.getDescription(),
                map.get(ProductStatus.PAY_WAIT) == null ? 0 : map.get(ProductStatus.PAY_WAIT));
        ResourceVO payRun = new ResourceVO(ProductStatus.PAY_RUN.getDescription(),
                map.get(ProductStatus.PAY_RUN) == null ? 0 : map.get(ProductStatus.PAY_RUN));
        ResourceVO payTrailer = new ResourceVO(ProductStatus.PAY_TRAILER.getDescription(),
                map.get(ProductStatus.PAY_TRAILER) == null ? 0 : map.get(ProductStatus.PAY_TRAILER));
        ResourceVO payEnd = new ResourceVO(ProductStatus.PAY_END.getDescription(),
                map.get(ProductStatus.PAY_END) == null ? 0 : map.get(ProductStatus.PAY_END));
        ResourceVO settlement = new ResourceVO(ProductStatus.SETTLEMENT.getDescription(),
                map.get(ProductStatus.SETTLEMENT) == null ? 0 : map.get(ProductStatus.SETTLEMENT));

        vos.add(auditWait);
        vos.add(auditRun);
        vos.add(rejected);
        vos.add(trailer);
        vos.add(twoAudit);
        vos.add(promote);
        vos.add(endApproach);
        vos.add(end);
        vos.add(payWait);
        vos.add(payRun);
        vos.add(payTrailer);
        vos.add(payEnd);
        vos.add(settlement);

        return vos;
    }

}
