package com.mwb.controller.product;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.product.api.*;
import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.comm.Bool;
import com.mwb.dao.model.comm.Log;
import com.mwb.dao.model.comm.PagingData;
import com.mwb.dao.model.product.*;
import com.mwb.service.ParserService;
import com.mwb.service.dataoke.api.IDaoLaoKeService;
import com.mwb.service.product.api.IProductService;
import com.mwb.util.DateTimeUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        filter.setPagingData(new PagingData(request.getPageNumber(),request.getPageSize()));

        SearchResult<Product> result = productService.searchProduct(filter);
        List<ProductVO> productVOs = ProductVO.toVOs(result.getResult());

        SearchProductResponse response = new SearchProductResponse();
        response.setProducts(productVOs);
        response.setPagingResult(result.getPagingResult());

        return new ServiceResponse();
    }

    @ResponseBody
    @RequestMapping(
            value = "/create",
            consumes = ContentType.APPLICATION_JSON_UTF8,
            produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse create(CreateProductRequest request) throws ParseException {
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
        product.setCouponUseNumber(request.getCouponUseNumber());
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

        //// TODO: 2017/4/1
        product.setEmployee(null);
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
}
