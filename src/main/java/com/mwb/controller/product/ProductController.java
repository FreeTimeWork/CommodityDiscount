package com.mwb.controller.product;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.finance.api.ProductVoucherVO;
import com.mwb.controller.finance.api.SearchFinanceVoucherRequest;
import com.mwb.controller.finance.api.SearchFinanceVoucherResponse;
import com.mwb.controller.product.api.*;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.ProductVoucherFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.comm.Bool;
import com.mwb.dao.model.comm.Log;
import com.mwb.dao.model.comm.PagingData;
import com.mwb.dao.model.employee.Employee;
import com.mwb.dao.model.product.*;
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.service.ParserService;
import com.mwb.service.dataoke.api.IDaoLaoKeService;
import com.mwb.service.product.api.IProductService;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    @ResponseBody
    @RequestMapping(value = "/grab", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse grabProduct(GrabRequest request) {

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
    @RequestMapping(value = "/detail", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse getProductDetail(Integer id) {
        Product product = productService.getProductById(id);

        return ProductDetailsResponse.toResponse(product);

    }

    @ResponseBody
    @RequestMapping(value = "/search", produces = ContentType.APPLICATION_JSON_UTF8)
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
        filter.setUseMinNumber(request.getUseMinNumber());
        filter.setUseMaxNumber(request.getUseMaxNumber());
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
    @RequestMapping(value = "/create", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse create(CreateProductRequest request) throws ParseException {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        if (employee == null) {
            return new ServiceResponse();
        }

        Product product = new Product();

        product.setProductId(request.getProductId());
        product.setName(request.getName());
        product.setPictureUrl(request.getPictureUrl());
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

        return new ServiceResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/voucher/create", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse createProductVoucher(
            HttpServletRequest request, @RequestParam("files") MultipartFile files, Integer id) {
        Product product = productService.getProductById(id);
//        String contextPath=request.getSession().getServletContext().getContextPath();
//        String img=contextPath+"/static/bookimgs/" +"default.jpg";
//        if (!file.isEmpty()) {
//            String filePath = request.getSession().getServletContext().getRealPath("/")
//                    + "\\static\\bookimgs\\" +  file.getOriginalFilename();
//            //   /BookStore
//
//            img=contextPath+"/static/bookimgs/" +  file.getOriginalFilename();
//            //转存文件
//            LOGGER.info(" fileup into{}",file.getOriginalFilename());
//            LOGGER.info("filePath{}",filePath);
//            try {
//                file.transferTo(new File(filePath));
//                LOGGER.info(file.getOriginalFilename() + "上传文件成功");
//            } catch (IOException e) {
//                LOGGER.error("IOException{}",e);
//                e.printStackTrace();
//            }
//        }
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
    @RequestMapping(value = "/voucher/search", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse searchVoucher(SearchFinanceVoucherRequest request) throws ParseException {
        SearchFinanceVoucherResponse response = new SearchFinanceVoucherResponse();
        ProductVoucherFilter filter = new ProductVoucherFilter();
        filter.setProductType(ProductType.fromId(request.getProductId()));
        filter.setGroupId(request.getGroupId());
        filter.setEmployeeId(request.getEmployeeId());
        filter.setOrderByAsc(request.getOrderByAsc() == null ? true : request.getOrderByAsc());
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
        filter.setMinSurplusNumber(request.getMinSurplusNumber());
        filter.setMaxSurplusNumber(request.getMaxSurplusNumber());
        filter.setMinUseNumber(request.getMinUseNumber());
        filter.setMaxUseNumber(request.getMaxUseNumber());
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
}
