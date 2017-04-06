package com.mwb.controller.product;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.finance.api.ProductVoucherVO;
import com.mwb.controller.finance.api.SearchFinanceVoucherRequest;
import com.mwb.controller.finance.api.SearchFinanceVoucherResponse;
import com.mwb.controller.product.api.BaseApproveRequest;
import com.mwb.controller.product.api.CreateProductRequest;
import com.mwb.controller.product.api.CreateProductVoucherRequest;
import com.mwb.controller.product.api.GrabRequest;
import com.mwb.controller.product.api.ProductDetailsResponse;
import com.mwb.controller.product.api.ProductVO;
import com.mwb.controller.product.api.SearchProductRequest;
import com.mwb.controller.product.api.SearchProductResponse;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.bpm.Task;
import com.mwb.dao.model.bpm.Variable;
import com.mwb.dao.model.comm.Bool;
import com.mwb.dao.model.comm.Log;
import com.mwb.dao.model.comm.PagingData;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.product.Activity;
import com.mwb.dao.model.product.HireType;
import com.mwb.dao.model.product.Product;
import com.mwb.dao.model.product.ProductPicture;
import com.mwb.dao.model.product.ProductStatus;
import com.mwb.dao.model.product.ProductType;
import com.mwb.dao.model.product.Store;
import com.mwb.dao.model.product.StoreType;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;
import com.mwb.service.ParserService;
import com.mwb.service.bpm.api.IBpmService;
import com.mwb.service.dataoke.api.IDaoLaoKeService;
import com.mwb.service.product.api.IProductService;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by MengWeiBo on 2017-04-01
 */

@Controller
@RequestMapping("/product")
public class ProductController {
    private static final Log LOG = Log.getLog(ProductController.class);

    @Autowired
    private IDaoLaoKeService daoLaoKeService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IBpmService bpmService;

    @ResponseBody
    @RequestMapping(value = "/grab")
    public ServiceResponse grabProduct(@RequestBody GrabRequest request) {

        if (StringUtils.isBlank(request.getProductUrl()) || StringUtils.isBlank(request.getCouponUrl())) {
            return new ServiceResponse();
        }

        ParserService parserService = new ParserService(request.getProductUrl());
        Product product = parserService.grabProduct();
        product.setCouponUrl(request.getCouponUrl());
        daoLaoKeService.setDaTaoKeProduct(product);

        ProductDetailsResponse response = ProductDetailsResponse.toResponse(product);
        response.setCreateHistory(getCreateHistory(product.getProductId()));

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/detail")
    public ServiceResponse getProductDetail(Integer id) {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return new ProductDetailsResponse();
        }

        Product product = productService.getProductById(id);
        ProductDetailsResponse response = ProductDetailsResponse.toResponse(product);

        Integer positionId = employee.getPosition().getId();
        if (!positionId.equals(1) && !positionId.equals(5)) {
            response.setVoucher(null);
        }

        return response;

    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public ServiceResponse searchProduct(SearchProductRequest request) throws ParseException {
        SearchProductResponse response = new SearchProductResponse();

        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return response;
        }

        ProductFilter filter = new ProductFilter();

        filter.setId(request.getId());
        filter.setProductId(request.getProductId());
        filter.setName(request.getName());
        filter.setGroupId(request.getGroupId());
        filter.setEmployeeId(request.getEmployeeId());
        filter.setCreateBeginTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getCreateBeginTime()));
        filter.setCreateEndTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getCreateEndTime()));
        filter.setBeginFromTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getBeginFromTime()));
        filter.setBeginToTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getBeginToTime()));
        filter.setEndFromTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getEndFromTime()));
        filter.setEndToTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getEndFromTime()));
        filter.setReceiveMinNumber(request.getUseMinNumber());
        filter.setReceiveMaxNumber(request.getUseMaxNumber());
        filter.setSurplusMinNumber(request.getSurplusMinNumber());
        filter.setSurplusMaxNumber(request.getSurplusMaxNumber());
        filter.setStatus(ProductStatus.fromId(request.getStatusId()));
        filter.setType(ProductType.fromId(request.getTypeId()));
        filter.setPaged(true);
        filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));

        SearchResult<Product> result = productService.searchProduct(filter, employee);
        List<ProductVO> productVOs = ProductVO.toVOs(result.getResult());

        response.setProducts(productVOs);
        response.setPagingResult(result.getPagingResult());

        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/create")
    public ServiceResponse create(@RequestBody CreateProductRequest request) throws ParseException {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return new ServiceResponse();
        }

        Product product = new Product();

        product.setProductId(request.getProductId());
        product.setName(request.getName());
        product.setPictureUrl(request.getPictureUrl());
        product.setSupplementPictureUrl(request.getSupplementPictureUrl());
        product.setReservePrice(request.getReservePrice());
        product.setSales(request.getSales());
        product.setUrl(request.getUrl());
        product.setActivityTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getActivityTime()));
        product.setImmediately(Bool.fromValue(request.getImmediately()));
        product.setDiscountPrice(request.getDiscountPrice());
        product.setCouponAmount(request.getCouponAmount());
        product.setCouponUrl(request.getCouponUrl());
        if (StringUtils.isBlank(request.getCouponBeginTime())) {
            product.setCouponBeginTime(product.getActivityTime());
        } else {
            product.setCouponBeginTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getCouponBeginTime()));
        }
        product.setCouponEndTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getCouponEndTime()));
        product.setCouponReceiveNumber(request.getCouponUseNumber());
        product.setCouponSurplusNumber(request.getCouponSurplusNumber());
        product.setCondition(request.getCondition());
        product.setFeatures(request.getFeatures());
        product.setDescription(request.getDescription());
        product.setChargePrice(request.getChargePrice());
        product.setCreateTime(new Date());
        product.setRatio(request.getRatio());
        product.setPlanUrl(request.getPlanUrl());
        product.setHireType(HireType.fromId(request.getHireTypeId()));
        product.setStatus(ProductStatus.AUDIT_WAIT);
        product.setActivity(Activity.fromId(request.getActivityId()));
        product.setProductType(ProductType.fromId(request.getProductTypeId()));

        product.setEmployee(employee);
        Store store = new Store();
        product.setStore(store);
        store.setQq(request.getQq());
        store.setDescriptionScore(request.getStoreDescriptionScore());
        store.setServiceScore(request.getServiceScore());
        store.setSpeedScore(request.getSpeedScore());
        store.setType(StoreType.fromId(request.getStoreTypeId()));

        if (CollectionUtils.isNotEmpty(request.getPictures())) {
            List<ProductPicture> pictures = new ArrayList<>();
            for (String url : request.getPictures()) {
                ProductPicture picture = new ProductPicture();
                picture.setUrl(url);
                picture.setProduct(product);
            }
            product.setPictures(pictures);
        }

        productService.createProduct(product);

        //存入流程变量
        Task task = new Task();
        Variable variable = new Variable("createdById", employee.getId() + "");
        List<Variable> variables = new ArrayList<>();
        variables.add(variable);
        task.setVariables(variables);
        //创建流程
        bpmService.createTask(task);
        return new ServiceResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/voucher/create")
    public ServiceResponse createProductVoucher(
            @RequestParam("files") MultipartFile[] files,
            CreateProductVoucherRequest request) {
        Product product = productService.getProductById(request.getId());

        ProductVoucher voucher = new ProductVoucher();
        voucher.setReceiveNumber(request.getCouponReceiveNumber());
        voucher.setUseNumber(request.getCouponUseNumber());
        voucher.setPayAmount(request.getPayAmount());
        voucher.setShouldChargeAmount(request.getShouldChargeAmount());
        voucher.setActualChargeAmount(request.getActualChargeAmount());
        voucher.setCreateTime(new Date());
        voucher.setConversionRate(request.getConversionRate());
        voucher.setWithoutUrl(request.getWithoutRate());
        voucher.setProduct(product);

        List<VoucherPicture> pictures = new ArrayList<>();
        if (files != null && files.length > 0) {
            String realPath = ApplicationContextUtils.getSession().getServletContext().getRealPath("/") + "\\image\\";

            for (int i = 0; i < files.length; i++) {
                try {
                    MultipartFile file = files[i];
                    String filePath = realPath + file.getOriginalFilename();

                    VoucherPicture picture = new VoucherPicture();
                    picture.setUrl(filePath);
                    picture.setVoucher(voucher);
                    pictures.add(picture);

                    file.transferTo(new File(filePath));
                } catch (IOException e) {
                    LOG.error("createProductVoucher is err.");
                }
            }
        }

        voucher.setPictures(pictures);

        productService.createProductVoucher(voucher);

        return new ServiceResponse();

    }

    private String getCreateHistory(String productId) {
        List<Product> products = productService.getProductByProductId(productId);

        if (CollectionUtils.isNotEmpty(products)) {
            StringBuilder str = new StringBuilder();
            str.append("(历史推广记录:");
            for (Product product : products) {
                str.append(product.getEmployee().getFullName());
                str.append("(");
                str.append(product.getEmployee().getStatus().getDescription());
                str.append(")");
                str.append(DateTimeUtility.formatYYYYMMDD(product.getCreateTime()));
            }
            str.append(")");

            return str.toString();
        } else {
            return "";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/voucher/search")
    public ServiceResponse searchVoucher(SearchFinanceVoucherRequest request) throws ParseException {
        SearchFinanceVoucherResponse response = new SearchFinanceVoucherResponse();
        ProductFilter filter = new ProductFilter();
        filter.setType(ProductType.fromId(request.getProductTypeId()));
        filter.setGroupId(request.getGroupId());
        filter.setEmployeeId(request.getEmployeeId());
        filter.setOrderAsc(request.getOrderByAsc() == null ? true : request.getOrderByAsc());
        filter.setCreateBeginTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getCreateBeginTime()));
        filter.setCreateEndTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getCreateEndTime()));
        filter.setBeginFromTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getBeginFromTime()));
        filter.setBeginToTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getBeginToTime()));
        filter.setEndFromTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getEndFromTime()));
        filter.setEndToTime(DateTimeUtility.parseYYYYMMDDHHMMSS(request.getEndToTime()));
        filter.setMinChargePrice(request.getMinChargePrice());
        filter.setMaxChargePrice(request.getMaxChargePrice());
        filter.setMinDiscountPrice(request.getMinDiscountPrice());
        filter.setMaxDiscountPrice(request.getMaxDiscountPrice());
        filter.setMinPayPrice(request.getMinPayPrice());
        filter.setMaxPayPrice(request.getMaxPayPrice());
        filter.setSurplusMinNumber(request.getMinSurplusNumber());
        filter.setSurplusMaxNumber(request.getMaxSurplusNumber());
        filter.setReceiveMinNumber(request.getMinUseNumber());
        filter.setReceiveMaxNumber(request.getMaxUseNumber());
        filter.setId(request.getId());
        filter.setName(request.getName());
        filter.setProductId(request.getProductId());
        filter.setPaged(true);
        filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
        SearchResult<ProductVoucher> result = productService.searchProductVoucher(filter);

        response.setVouchers(ProductVoucherVO.toVOs(result.getResult()));
        response.setPagingResult(result.getPagingResult());

        return response;
    }

    //认领
    @ResponseBody
    @RequestMapping(value = "/approve/claim")
    public ServiceResponse claimHandler(@RequestBody BaseApproveRequest request) {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return new ServiceResponse();
        }
        Product product = new Product();
        product.setId(request.getProductId());
        //审单员
        if (employee.getPosition().getId().equals(4)) {

            product.setStatus(ProductStatus.AUDIT_RUN);
            productService.modifyProduct(product);

        } else if (employee.getPosition().getId().equals(5)) { //财务
            product.setStatus(ProductStatus.PAY_RUN);
            productService.modifyProduct(product);
        }

        return new ServiceResponse();
    }

    //一审
    @ResponseBody
    @RequestMapping(value = "/approve/check", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse checkHandler(@RequestBody BaseApproveRequest request) {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return new ServiceResponse();
        }
        Product product = new Product();
        product.setId(request.getProductId());
        ProductStatus status = ProductStatus.fromId(request.getProductStatusId());
        product.setStatus(status);
        if (status.equals(ProductStatus.REJECTED)) {
            product.setStatus(ProductStatus.AUDIT_WAIT);
            product.setUpdateTime(new Date());
        }

        productService.modifyProduct(product);

        return new ServiceResponse();
    }

    //复审
    @ResponseBody
    @RequestMapping(value = "/approve/recheck")
    public ServiceResponse recheckHandler(@RequestBody BaseApproveRequest request) {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return new ServiceResponse();
        }
        Product product = new Product();
        product.setId(request.getProductId());
        ProductStatus status = ProductStatus.fromId(request.getProductStatusId());
        product.setStatus(ProductStatus.fromId(request.getProductStatusId()));
        if (status.equals(ProductStatus.REJECTED)) {
            product.setStatus(ProductStatus.AUDIT_WAIT);
            product.setUpdateTime(new Date());
        }
        productService.modifyProduct(product);

        return new ServiceResponse();
    }
}
