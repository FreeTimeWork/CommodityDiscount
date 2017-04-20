package com.mwb.controller.product;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mwb.controller.api.ContentType;
import com.mwb.controller.api.ServiceResponse;
import com.mwb.controller.product.api.ProductVoucherVO;
import com.mwb.controller.finance.api.SearchFinanceVoucherRequest;
import com.mwb.controller.finance.api.SearchFinanceVoucherResponse;
import com.mwb.controller.frontend.api.ResourceVO;
import com.mwb.controller.product.api.BaseApproveRequest;
import com.mwb.controller.product.api.CreateProductRequest;
import com.mwb.controller.product.api.CreateProductVoucherRequest;
import com.mwb.controller.product.api.ProductDetailsResponse;
import com.mwb.controller.product.api.ProductVO;
import com.mwb.controller.product.api.SearchProductRequest;
import com.mwb.controller.product.api.SearchProductResponse;
import com.mwb.controller.util.ApplicationContextUtils;
import com.mwb.dao.filter.ProductFilter;
import com.mwb.dao.filter.SearchResult;
import com.mwb.dao.model.bpm.Task;
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
import com.mwb.dao.model.product.voucher.ProductVoucher;
import com.mwb.dao.model.product.voucher.VoucherPicture;
import com.mwb.service.ParserService;
import com.mwb.service.bpm.api.IBpmService;
import com.mwb.service.dataoke.api.IDaoLaoKeService;
import com.mwb.service.product.api.IProductService;
import com.mwb.service.taobo.api.ITaoBaoClient;
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

//    @Autowired
    private ITaoBaoClient client;

    @ResponseBody
    @RequestMapping(value = "/grab2")
    public ServiceResponse grabCoupon(String couponUrl) throws Exception {

        Product product = new Product();
        product.setCouponUrl(couponUrl);

        productService.setProduct(product);
        productService.setCoupon(product);

        ProductDetailsResponse response = ProductDetailsResponse.toResponse(product);

        if (StringUtils.isBlank(product.getCondition())) {
            response.setMessage("优惠券地址错误!");
            return response;
        }

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/grab1")
    public ServiceResponse grabProduct(String productUrl) {
        ParserService parserService = new ParserService(productUrl);
        Product product = parserService.grabProduct();

        productService.setProduct(product);

        ProductDetailsResponse response = ProductDetailsResponse.toResponse(product);

        if (StringUtils.isBlank(product.getName())) {
            response.setMessage("商品地址错误!");
            return response;
        }

        return ProductDetailsResponse.toResponse(product);
    }

    @ResponseBody
    @RequestMapping(value = "/grab")
    public ServiceResponse grabProduct(String productUrl, String couponUrl) {

        if (StringUtils.isBlank(productUrl) || StringUtils.isBlank(couponUrl)) {
            return new ServiceResponse();
        }

        ParserService parserService = new ParserService(productUrl);
        Product product = parserService.grabProduct();
        product.setCouponUrl(couponUrl);
        daoLaoKeService.setDaTaoKeProduct(product);

        ProductDetailsResponse response = ProductDetailsResponse.toResponse(product);

        if (StringUtils.isBlank(product.getName())) {
            response.setMessage("商品地址错误!");
            return response;
        }

        response.setCreateHistory(getCreateHistory(product.getProductId()));

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/detail")
    public ServiceResponse getProductDetail(Integer id) {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");

        Product product = productService.getProductById(id);
        ProductDetailsResponse response = ProductDetailsResponse.toResponse(product);

        if (product != null) {
            response.setVoucher(ProductVoucherVO.toVO(product.getVoucher(), employee));

            response.setApproveStatus(getProductApproveStatus(product, employee));
            response.setTask(product.getTask());

            if ((employee.getId().equals(product.getEmployee().getId()) || employee.getPosition().getId().equals(1))
                    && (product.getStatus() == ProductStatus.PAY_WAIT
                    || product.getStatus() == ProductStatus.PAY_TRAILER)) {
                response.setShowEdit(true);
            }
            if (product.getStatus() == ProductStatus.PAY_WAIT
                    || product.getStatus() == ProductStatus.PAY_RUN
                    || product.getStatus() == ProductStatus.PAY_RUN
                    || product.getStatus() == ProductStatus.PAY_TRAILER
                    || product.getStatus() == ProductStatus.PAY_END
                    || product.getStatus() == ProductStatus.SETTLEMENT) {
                response.setShowVoucher(true);
            }
        }

        return response;

    }

    private List<ResourceVO> getProductApproveStatus(Product product, Employee employee) {
        List<ResourceVO> vos = new ArrayList<>();
        ProductStatus status = product.getStatus();
        Integer positionId = employee.getPosition().getId();
        if (ProductStatus.AUDIT_RUN == status
                && (positionId.equals(4) || employee.getPosition().getId().equals(1))) {
            vos.add(new ResourceVO(ProductStatus.TWO_AUDIT.getDescription(), ProductStatus.TWO_AUDIT.getId()));
            vos.add(new ResourceVO(ProductStatus.REJECTED.getDescription(), ProductStatus.REJECTED.getId()));
            vos.add(new ResourceVO(ProductStatus.TRAILER.getDescription(), ProductStatus.TRAILER.getId()));
        } else if (ProductStatus.TWO_AUDIT == status
                && (positionId.equals(4) || employee.getPosition().getId().equals(1))) {
            vos.add(new ResourceVO(ProductStatus.PROMOTE.getDescription(), ProductStatus.PROMOTE.getId()));
            vos.add(new ResourceVO(ProductStatus.REJECTED.getDescription(), ProductStatus.REJECTED.getId()));
            vos.add(new ResourceVO(ProductStatus.TRAILER.getDescription(), ProductStatus.TRAILER.getId()));
        } else if (ProductStatus.PROMOTE == status
                && (product.getEmployee().getId().equals(employee.getId()) || employee.getPosition().getId().equals(1))) {
            vos.add(new ResourceVO(ProductStatus.END.getDescription(), ProductStatus.END.getId()));
            vos.add(new ResourceVO(ProductStatus.PAY_WAIT.getDescription(), ProductStatus.PAY_WAIT.getId()));
        } else if (ProductStatus.END == status
                && (product.getEmployee().getId().equals(employee.getId()) || employee.getPosition().getId().equals(1))) {
            vos.add(new ResourceVO(ProductStatus.PAY_WAIT.getDescription(), ProductStatus.PAY_WAIT.getId()));
        } else if (ProductStatus.PAY_WAIT == status
                && ((product.getEmployee().getId().equals(employee.getId())
                && positionId == 6)
                || positionId.equals(1))) {
            vos.add(new ResourceVO(ProductStatus.SETTLEMENT.getDescription(), ProductStatus.SETTLEMENT.getId()));
        } else if (ProductStatus.PAY_RUN == status
                && (positionId.equals(5) || employee.getPosition().getId().equals(1))) {
//            vos.add(new ResourceVO(ProductStatus.PAY_TRAILER.getDescription(), ProductStatus.PAY_TRAILER.getId()));
//            vos.add(new ResourceVO(ProductStatus.PAY_END.getDescription(), ProductStatus.PAY_END.getId()));
        }

        return vos;
    }

    @ResponseBody
    @RequestMapping(value = "/search")
    public ServiceResponse searchProduct(SearchProductRequest request) throws ParseException {
        SearchProductResponse response = new SearchProductResponse();

        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");

        ProductFilter filter = new ProductFilter();

        filter.setId(request.getId());
        filter.setProductId(request.getProductId());
        filter.setName(request.getName());
        filter.setGroupId(request.getGroupId());
        filter.setEmployeeId(request.getEmployeeId());
        filter.setCreateBeginTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getCreateBeginTime()));
        filter.setCreateEndTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getCreateEndTime()));
        filter.setBeginFromTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getBeginFromTime()));
        filter.setBeginToTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getBeginToTime()));
        filter.setEndFromTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getEndFromTime()));
        filter.setEndToTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getEndToTime()));
        filter.setReceiveMinNumber(request.getUseMinNumber());
        filter.setReceiveMaxNumber(request.getUseMaxNumber());
        filter.setSurplusMinNumber(request.getSurplusMinNumber());
        filter.setSurplusMaxNumber(request.getSurplusMaxNumber());
        filter.setStatus(ProductStatus.fromId(request.getStatusId()));
        filter.setType(ProductType.fromId(request.getTypeId()));
        filter.setActivity(Activity.fromId(request.getActivityId()));
        filter.setPaged(true);
        filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
        filter.setOrderAsc(request.getOrderAsc() == null ? true : request.getOrderAsc());

        SearchResult<Product> result = productService.searchProduct(filter, employee);
        List<ProductVO> productVOs = ProductVO.toVOs(result.getResult(), employee);

        response.setProducts(productVOs);
        response.setPagingResult(result.getPagingResult());

        return response;
    }


    @ResponseBody
    @RequestMapping(value = "/create")
    public ServiceResponse create(@RequestBody CreateProductRequest request) throws ParseException {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");
        ServiceResponse response = new ServiceResponse();

        ParserService parserService = new ParserService(request.getUrl());
        Product grapProduct = parserService.grabProduct();

        Product dataokeProduct = daoLaoKeService.getDaTaoKeProduct(grapProduct.getProductId());
        if (dataokeProduct == null) {
            response.setMessage("商品地址错误!");
            return response;
        }

        Product product = new Product();

        product.setTaoKeId(dataokeProduct.getTaoKeId());
        product.setProductId(dataokeProduct.getProductId());
        product.setName(request.getName());
        product.setPictureUrl(dataokeProduct.getPictureUrl());
        product.setSupplementPictureUrl(request.getSupplementPictureUrl());
        product.setReservePrice(request.getReservePrice());
        product.setSales(request.getSales());
        product.setUrl(request.getUrl());
        product.setActivityTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getActivityTime()));
        product.setImmediately(Bool.fromValue(request.getImmediately()));
        product.setDiscountPrice(request.getDiscountPrice());
        product.setCouponAmount(dataokeProduct.getCouponAmount());
        product.setCouponUrl(request.getCouponUrl());
        if (StringUtils.isBlank(request.getCouponBeginTime())) {
            product.setCouponBeginTime(product.getActivityTime());
        } else {
            product.setCouponBeginTime(DateTimeUtility.parseYYYYMMDD(request.getCouponBeginTime()));
        }
        product.setCouponEndTime(DateTimeUtility.parseYYYYMMDD(request.getCouponEndTime()));
        product.setCouponReceiveNumber(dataokeProduct.getCouponReceiveNumber());
        product.setCouponSurplusNumber(dataokeProduct.getCouponSurplusNumber());
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
        product.setUpdateStatusTime(new Date());

        product.setEmployee(employee);
        Store store = new Store();
        product.setStore(store);
        store.setQq(request.getQq());
        store.setStoreId(dataokeProduct.getStore().getStoreId());
        store.setDescriptionScore(grapProduct.getStore().getDescriptionScore());
        store.setServiceScore(grapProduct.getStore().getServiceScore());
        store.setSpeedScore(grapProduct.getStore().getSpeedScore());
        store.setType(dataokeProduct.getStore().getType());

        if (CollectionUtils.isNotEmpty(grapProduct.getPictures())) {
            List<ProductPicture> pictures = new ArrayList<>();
            for (String url : request.getPictures()) {
                ProductPicture picture = new ProductPicture();
                picture.setUrl(url);
                picture.setProduct(product);
                pictures.add(picture);
            }
            product.setPictures(pictures);
        }

        productService.createProduct(product);

        return new ServiceResponse();
    }

    //提交结账
    @ResponseBody
    @RequestMapping(value = "/voucher/create")
    public ServiceResponse createProductVoucher(
            @RequestParam("files") MultipartFile files,
            CreateProductVoucherRequest request) throws Exception {

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
        voucher.setPayTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getPayTime()));
        voucher.setProduct(product);

        List<VoucherPicture> pictures = new ArrayList<>();

        String contextPath = ApplicationContextUtils.getSession().getServletContext().getContextPath();

        if (!files.isEmpty()) {
            String filePath = ApplicationContextUtils.getSession().getServletContext().getRealPath("/")
                    + "\\image\\" + files.getOriginalFilename();
            String img = contextPath + "/image/" + files.getOriginalFilename();
            try {
                files.transferTo(new File(filePath));
                VoucherPicture picture = new VoucherPicture();
                picture.setUrl(img);
                picture.setVoucher(voucher);
                pictures.add(picture);

                files.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        voucher.setPictures(pictures);

        productService.createProductVoucher(voucher, product);

        return new ServiceResponse();

    }

    private String getCreateHistory(String productId) {
        List<Product> products = productService.getProductByProductId(productId);

        if (CollectionUtils.isNotEmpty(products)) {
            StringBuilder str = new StringBuilder();
            str.append("&nbsp&nbsp&nbsp【历史推广记录:");
            for (Product product : products) {
                str.append(product.getEmployee().getFullName());
                str.append("(");
                str.append(product.getEmployee().getStatus().getDescription());
                str.append(")");
                str.append(DateTimeUtility.formatYYYYMMDD(product.getCreateTime()));
                str.append(",&nbsp&nbsp");
            }
            str.append("】");

            return str.toString();
        } else {
            return "";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/voucher/search")
    public ServiceResponse searchVoucher(SearchFinanceVoucherRequest request) throws ParseException {
        SearchFinanceVoucherResponse response = new SearchFinanceVoucherResponse();
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");

        ProductFilter filter = new ProductFilter();
        filter.setType(ProductType.fromId(request.getProductTypeId()));
        filter.setGroupId(request.getGroupId());
        filter.setEmployeeId(request.getEmployeeId());
        filter.setOrderAsc(request.getOrderByAsc() == null ? true : request.getOrderByAsc());
        filter.setCreateBeginTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getCreateBeginTime()));
        filter.setCreateEndTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getCreateEndTime()));
        filter.setBeginFromTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getBeginFromTime()));
        filter.setBeginToTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getBeginToTime()));
        filter.setEndFromTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getEndFromTime()));
        filter.setEndToTime(DateTimeUtility.parseYYYYMMDDHHMM(request.getEndToTime()));
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
        SearchResult<ProductVoucher> result = productService.searchProductVoucher(filter, employee);

        response.setVouchers(ProductVoucherVO.toVOs(result.getResult(), null));
        response.setPagingResult(result.getPagingResult());

        return response;
    }

    //认领
    @ResponseBody
    @RequestMapping(value = "/approve/claim")
    public ServiceResponse claimHandler(@RequestBody BaseApproveRequest request) {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");

        Product product = productService.getProductById(request.getProductId());
        if (product == null) {
            return new ServiceResponse();
        }
        Task task = new Task();
        task.setEmployeeId(employee.getId());
        task.setId(product.getTask().getId());
        bpmService.modifyTask(task);
        productService.modifyProductStatus(product.getId(), product.getEmployee().getId(), product.getStatus(), ProductStatus.AUDIT_RUN);

        return new ServiceResponse();
    }

    //审核
    @ResponseBody
    @RequestMapping(value = "/approve/check", produces = ContentType.APPLICATION_JSON_UTF8)
    public ServiceResponse checkHandler(@RequestBody BaseApproveRequest request) {
        Employee employee = (Employee) ApplicationContextUtils.getSession().getAttribute("employee");

        Product product = productService.getProductById(request.getProductId());

        ProductStatus status = ProductStatus.fromId(request.getProductStatusId());
        if (product == null) {
            return new ServiceResponse();
        }

        productService.modifyProductStatus(product.getId(), product.getEmployee().getId(), product.getStatus(), status);

        return new ServiceResponse();
    }

}
